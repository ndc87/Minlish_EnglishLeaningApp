package com.minlish.app;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.hilt.work.HiltWorkerFactory;
import androidx.hilt.work.WorkerAssistedFactory;
import androidx.hilt.work.WorkerFactoryModule_ProvideFactoryFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import com.minlish.app.data.local.AppDatabase;
import com.minlish.app.data.local.CsvWordProvider;
import com.minlish.app.data.local.DatabaseSeeder;
import com.minlish.app.data.local.dao.CardDao;
import com.minlish.app.data.local.dao.LearningLogDao;
import com.minlish.app.data.local.dao.ReviewDao;
import com.minlish.app.data.local.dao.TopicDao;
import com.minlish.app.data.local.dao.UserDao;
import com.minlish.app.data.local.dao.UserStatsDao;
import com.minlish.app.data.local.dao.WordDao;
import com.minlish.app.data.local.storage.EncryptedAuthStorage;
import com.minlish.app.data.remote.AuthInterceptor;
import com.minlish.app.data.remote.api.VocabularyApiService;
import com.minlish.app.data.repository.AuthRepositoryImpl;
import com.minlish.app.data.repository.VocabularyRepositoryImpl;
import com.minlish.app.data.worker.DailyReminderWorker;
import com.minlish.app.data.worker.DailyReminderWorker_AssistedFactory;
import com.minlish.app.data.worker.StudyReminderWorker;
import com.minlish.app.data.worker.StudyReminderWorker_AssistedFactory;
import com.minlish.app.di.AppModule_ProvideEncryptedSharedPreferencesFactory;
import com.minlish.app.di.DatabaseModule_ProvideAppDatabaseFactory;
import com.minlish.app.di.DatabaseModule_ProvideCardDaoFactory;
import com.minlish.app.di.DatabaseModule_ProvideLearningLogDaoFactory;
import com.minlish.app.di.DatabaseModule_ProvideReviewDaoFactory;
import com.minlish.app.di.DatabaseModule_ProvideTopicDaoFactory;
import com.minlish.app.di.DatabaseModule_ProvideUserDaoFactory;
import com.minlish.app.di.DatabaseModule_ProvideUserStatsDaoFactory;
import com.minlish.app.di.DatabaseModule_ProvideWordDaoFactory;
import com.minlish.app.di.NetworkModule_ProvideAuthInterceptorFactory;
import com.minlish.app.di.NetworkModule_ProvideOkHttpClientFactory;
import com.minlish.app.di.NetworkModule_ProvideRetrofitFactory;
import com.minlish.app.di.NetworkModule_ProvideVocabularyApiServiceFactory;
import com.minlish.app.di.UseCaseModule_ProvideCalculateSM2IntervalUseCaseFactory;
import com.minlish.app.domain.repository.VocabularyRepository;
import com.minlish.app.domain.usecase.AddNewWordUseCase;
import com.minlish.app.domain.usecase.CalculateSM2IntervalUseCase;
import com.minlish.app.domain.usecase.CalculateStreakUseCase;
import com.minlish.app.domain.usecase.ExportImportUseCase;
import com.minlish.app.domain.usecase.GetAnalyticsUseCase;
import com.minlish.app.domain.usecase.GetDueWordsUseCase;
import com.minlish.app.domain.usecase.UpdateSrsLogicUseCase;
import com.minlish.app.ui.auth.AuthViewModel;
import com.minlish.app.ui.auth.AuthViewModel_HiltModules_KeyModule_ProvideFactory;
import com.minlish.app.ui.dashboard.DashboardViewModel;
import com.minlish.app.ui.dashboard.DashboardViewModel_HiltModules_KeyModule_ProvideFactory;
import com.minlish.app.ui.flashcard.FlashcardViewModel;
import com.minlish.app.ui.flashcard.FlashcardViewModel_HiltModules_KeyModule_ProvideFactory;
import com.minlish.app.ui.flashcard.TopicSelectionViewModel;
import com.minlish.app.ui.flashcard.TopicSelectionViewModel_HiltModules_KeyModule_ProvideFactory;
import com.minlish.app.ui.learning.LearningViewModel;
import com.minlish.app.ui.learning.LearningViewModel_HiltModules_KeyModule_ProvideFactory;
import com.minlish.app.ui.library.LibraryViewModel;
import com.minlish.app.ui.library.LibraryViewModel_HiltModules_KeyModule_ProvideFactory;
import com.minlish.app.ui.practice.PracticeViewModel;
import com.minlish.app.ui.practice.PracticeViewModel_HiltModules_KeyModule_ProvideFactory;
import com.minlish.app.ui.profile.ProfileViewModel;
import com.minlish.app.ui.profile.ProfileViewModel_HiltModules_KeyModule_ProvideFactory;
import com.minlish.app.ui.splash.SplashViewModel;
import com.minlish.app.ui.splash.SplashViewModel_HiltModules_KeyModule_ProvideFactory;
import com.minlish.app.ui.vocabulary.AddWordViewModel;
import com.minlish.app.ui.vocabulary.AddWordViewModel_HiltModules_KeyModule_ProvideFactory;
import com.minlish.app.ui.vocabulary.VocabularyViewModel;
import com.minlish.app.ui.vocabulary.VocabularyViewModel_HiltModules_KeyModule_ProvideFactory;
import com.minlish.app.util.NotificationHelper;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.SetBuilder;
import dagger.internal.SingleCheck;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class DaggerMinLishApp_HiltComponents_SingletonC {
  private DaggerMinLishApp_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public MinLishApp_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements MinLishApp_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public MinLishApp_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements MinLishApp_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public MinLishApp_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements MinLishApp_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public MinLishApp_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements MinLishApp_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public MinLishApp_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements MinLishApp_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public MinLishApp_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements MinLishApp_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public MinLishApp_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements MinLishApp_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public MinLishApp_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends MinLishApp_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends MinLishApp_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends MinLishApp_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends MinLishApp_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
      injectMainActivity2(mainActivity);
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Set<String> getViewModelKeys() {
      return SetBuilder.<String>newSetBuilder(11).add(AddWordViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(AuthViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(DashboardViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(FlashcardViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(LearningViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(LibraryViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(PracticeViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(ProfileViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(SplashViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(TopicSelectionViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(VocabularyViewModel_HiltModules_KeyModule_ProvideFactory.provide()).build();
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    private MainActivity injectMainActivity2(MainActivity instance) {
      MainActivity_MembersInjector.injectDatabaseSeeder(instance, singletonCImpl.databaseSeederProvider.get());
      return instance;
    }
  }

  private static final class ViewModelCImpl extends MinLishApp_HiltComponents.ViewModelC {
    private final SavedStateHandle savedStateHandle;

    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AddWordViewModel> addWordViewModelProvider;

    private Provider<AuthViewModel> authViewModelProvider;

    private Provider<DashboardViewModel> dashboardViewModelProvider;

    private Provider<FlashcardViewModel> flashcardViewModelProvider;

    private Provider<LearningViewModel> learningViewModelProvider;

    private Provider<LibraryViewModel> libraryViewModelProvider;

    private Provider<PracticeViewModel> practiceViewModelProvider;

    private Provider<ProfileViewModel> profileViewModelProvider;

    private Provider<SplashViewModel> splashViewModelProvider;

    private Provider<TopicSelectionViewModel> topicSelectionViewModelProvider;

    private Provider<VocabularyViewModel> vocabularyViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.savedStateHandle = savedStateHandleParam;
      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    private AddNewWordUseCase addNewWordUseCase() {
      return new AddNewWordUseCase(singletonCImpl.cardDao(), singletonCImpl.reviewDao());
    }

    private GetAnalyticsUseCase getAnalyticsUseCase() {
      return new GetAnalyticsUseCase(singletonCImpl.learningLogDao(), singletonCImpl.reviewDao());
    }

    private CalculateStreakUseCase calculateStreakUseCase() {
      return new CalculateStreakUseCase(singletonCImpl.learningLogDao(), singletonCImpl.userStatsDao());
    }

    private GetDueWordsUseCase getDueWordsUseCase() {
      return new GetDueWordsUseCase(singletonCImpl.bindVocabularyRepositoryProvider.get());
    }

    private UpdateSrsLogicUseCase updateSrsLogicUseCase() {
      return new UpdateSrsLogicUseCase(singletonCImpl.bindVocabularyRepositoryProvider.get());
    }

    private ExportImportUseCase exportImportUseCase() {
      return new ExportImportUseCase(singletonCImpl.cardDao(), ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.addWordViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.authViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.dashboardViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.flashcardViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.learningViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.libraryViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.practiceViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
      this.profileViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 7);
      this.splashViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 8);
      this.topicSelectionViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 9);
      this.vocabularyViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 10);
    }

    @Override
    public Map<String, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(11).put("com.minlish.app.ui.vocabulary.AddWordViewModel", ((Provider) addWordViewModelProvider)).put("com.minlish.app.ui.auth.AuthViewModel", ((Provider) authViewModelProvider)).put("com.minlish.app.ui.dashboard.DashboardViewModel", ((Provider) dashboardViewModelProvider)).put("com.minlish.app.ui.flashcard.FlashcardViewModel", ((Provider) flashcardViewModelProvider)).put("com.minlish.app.ui.learning.LearningViewModel", ((Provider) learningViewModelProvider)).put("com.minlish.app.ui.library.LibraryViewModel", ((Provider) libraryViewModelProvider)).put("com.minlish.app.ui.practice.PracticeViewModel", ((Provider) practiceViewModelProvider)).put("com.minlish.app.ui.profile.ProfileViewModel", ((Provider) profileViewModelProvider)).put("com.minlish.app.ui.splash.SplashViewModel", ((Provider) splashViewModelProvider)).put("com.minlish.app.ui.flashcard.TopicSelectionViewModel", ((Provider) topicSelectionViewModelProvider)).put("com.minlish.app.ui.vocabulary.VocabularyViewModel", ((Provider) vocabularyViewModelProvider)).build();
    }

    @Override
    public Map<String, Object> getHiltViewModelAssistedMap() {
      return Collections.<String, Object>emptyMap();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.minlish.app.ui.vocabulary.AddWordViewModel 
          return (T) new AddWordViewModel(viewModelCImpl.addNewWordUseCase());

          case 1: // com.minlish.app.ui.auth.AuthViewModel 
          return (T) new AuthViewModel(singletonCImpl.authRepositoryImplProvider.get());

          case 2: // com.minlish.app.ui.dashboard.DashboardViewModel 
          return (T) new DashboardViewModel(singletonCImpl.userStatsDao(), singletonCImpl.learningLogDao(), singletonCImpl.databaseSeederProvider.get(), viewModelCImpl.getAnalyticsUseCase(), viewModelCImpl.calculateStreakUseCase());

          case 3: // com.minlish.app.ui.flashcard.FlashcardViewModel 
          return (T) new FlashcardViewModel(singletonCImpl.cardDao(), singletonCImpl.reviewDao(), singletonCImpl.learningLogDao(), singletonCImpl.userStatsDao(), singletonCImpl.provideCalculateSM2IntervalUseCaseProvider.get(), viewModelCImpl.savedStateHandle);

          case 4: // com.minlish.app.ui.learning.LearningViewModel 
          return (T) new LearningViewModel(viewModelCImpl.getDueWordsUseCase(), viewModelCImpl.updateSrsLogicUseCase(), singletonCImpl.bindVocabularyRepositoryProvider.get());

          case 5: // com.minlish.app.ui.library.LibraryViewModel 
          return (T) new LibraryViewModel(singletonCImpl.bindVocabularyRepositoryProvider.get());

          case 6: // com.minlish.app.ui.practice.PracticeViewModel 
          return (T) new PracticeViewModel(singletonCImpl.cardDao(), singletonCImpl.reviewDao());

          case 7: // com.minlish.app.ui.profile.ProfileViewModel 
          return (T) new ProfileViewModel(singletonCImpl.userDao());

          case 8: // com.minlish.app.ui.splash.SplashViewModel 
          return (T) new SplashViewModel(singletonCImpl.databaseSeederProvider.get());

          case 9: // com.minlish.app.ui.flashcard.TopicSelectionViewModel 
          return (T) new TopicSelectionViewModel(singletonCImpl.cardDao());

          case 10: // com.minlish.app.ui.vocabulary.VocabularyViewModel 
          return (T) new VocabularyViewModel(singletonCImpl.cardDao(), singletonCImpl.reviewDao(), viewModelCImpl.exportImportUseCase());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends MinLishApp_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends MinLishApp_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends MinLishApp_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<AppDatabase> provideAppDatabaseProvider;

    private Provider<AuthInterceptor> provideAuthInterceptorProvider;

    private Provider<OkHttpClient> provideOkHttpClientProvider;

    private Provider<Retrofit> provideRetrofitProvider;

    private Provider<VocabularyApiService> provideVocabularyApiServiceProvider;

    private Provider<VocabularyRepositoryImpl> vocabularyRepositoryImplProvider;

    private Provider<VocabularyRepository> bindVocabularyRepositoryProvider;

    private Provider<DailyReminderWorker_AssistedFactory> dailyReminderWorker_AssistedFactoryProvider;

    private Provider<NotificationHelper> notificationHelperProvider;

    private Provider<StudyReminderWorker_AssistedFactory> studyReminderWorker_AssistedFactoryProvider;

    private Provider<SharedPreferences> provideEncryptedSharedPreferencesProvider;

    private Provider<EncryptedAuthStorage> encryptedAuthStorageProvider;

    private Provider<CsvWordProvider> csvWordProvider;

    private Provider<DatabaseSeeder> databaseSeederProvider;

    private Provider<AuthRepositoryImpl> authRepositoryImplProvider;

    private Provider<CalculateSM2IntervalUseCase> provideCalculateSM2IntervalUseCaseProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    private TopicDao topicDao() {
      return DatabaseModule_ProvideTopicDaoFactory.provideTopicDao(provideAppDatabaseProvider.get());
    }

    private WordDao wordDao() {
      return DatabaseModule_ProvideWordDaoFactory.provideWordDao(provideAppDatabaseProvider.get());
    }

    private UserDao userDao() {
      return DatabaseModule_ProvideUserDaoFactory.provideUserDao(provideAppDatabaseProvider.get());
    }

    private ReviewDao reviewDao() {
      return DatabaseModule_ProvideReviewDaoFactory.provideReviewDao(provideAppDatabaseProvider.get());
    }

    private Map<String, javax.inject.Provider<WorkerAssistedFactory<? extends ListenableWorker>>> mapOfStringAndProviderOfWorkerAssistedFactoryOf(
        ) {
      return MapBuilder.<String, javax.inject.Provider<WorkerAssistedFactory<? extends ListenableWorker>>>newMapBuilder(2).put("com.minlish.app.data.worker.DailyReminderWorker", ((Provider) dailyReminderWorker_AssistedFactoryProvider)).put("com.minlish.app.data.worker.StudyReminderWorker", ((Provider) studyReminderWorker_AssistedFactoryProvider)).build();
    }

    private HiltWorkerFactory hiltWorkerFactory() {
      return WorkerFactoryModule_ProvideFactoryFactory.provideFactory(mapOfStringAndProviderOfWorkerAssistedFactoryOf());
    }

    private CardDao cardDao() {
      return DatabaseModule_ProvideCardDaoFactory.provideCardDao(provideAppDatabaseProvider.get());
    }

    private LearningLogDao learningLogDao() {
      return DatabaseModule_ProvideLearningLogDaoFactory.provideLearningLogDao(provideAppDatabaseProvider.get());
    }

    private UserStatsDao userStatsDao() {
      return DatabaseModule_ProvideUserStatsDaoFactory.provideUserStatsDao(provideAppDatabaseProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideAppDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<AppDatabase>(singletonCImpl, 2));
      this.provideAuthInterceptorProvider = DoubleCheck.provider(new SwitchingProvider<AuthInterceptor>(singletonCImpl, 6));
      this.provideOkHttpClientProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 5));
      this.provideRetrofitProvider = DoubleCheck.provider(new SwitchingProvider<Retrofit>(singletonCImpl, 4));
      this.provideVocabularyApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<VocabularyApiService>(singletonCImpl, 3));
      this.vocabularyRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 1);
      this.bindVocabularyRepositoryProvider = DoubleCheck.provider((Provider) vocabularyRepositoryImplProvider);
      this.dailyReminderWorker_AssistedFactoryProvider = SingleCheck.provider(new SwitchingProvider<DailyReminderWorker_AssistedFactory>(singletonCImpl, 0));
      this.notificationHelperProvider = DoubleCheck.provider(new SwitchingProvider<NotificationHelper>(singletonCImpl, 8));
      this.studyReminderWorker_AssistedFactoryProvider = SingleCheck.provider(new SwitchingProvider<StudyReminderWorker_AssistedFactory>(singletonCImpl, 7));
      this.provideEncryptedSharedPreferencesProvider = DoubleCheck.provider(new SwitchingProvider<SharedPreferences>(singletonCImpl, 11));
      this.encryptedAuthStorageProvider = DoubleCheck.provider(new SwitchingProvider<EncryptedAuthStorage>(singletonCImpl, 10));
      this.csvWordProvider = DoubleCheck.provider(new SwitchingProvider<CsvWordProvider>(singletonCImpl, 12));
      this.databaseSeederProvider = DoubleCheck.provider(new SwitchingProvider<DatabaseSeeder>(singletonCImpl, 9));
      this.authRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<AuthRepositoryImpl>(singletonCImpl, 13));
      this.provideCalculateSM2IntervalUseCaseProvider = DoubleCheck.provider(new SwitchingProvider<CalculateSM2IntervalUseCase>(singletonCImpl, 14));
    }

    @Override
    public void injectMinLishApp(MinLishApp minLishApp) {
      injectMinLishApp2(minLishApp);
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private MinLishApp injectMinLishApp2(MinLishApp instance) {
      MinLishApp_MembersInjector.injectWorkerFactory(instance, hiltWorkerFactory());
      return instance;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.minlish.app.data.worker.DailyReminderWorker_AssistedFactory 
          return (T) new DailyReminderWorker_AssistedFactory() {
            @Override
            public DailyReminderWorker create(Context appContext, WorkerParameters workerParams) {
              return new DailyReminderWorker(appContext, workerParams, singletonCImpl.bindVocabularyRepositoryProvider.get());
            }
          };

          case 1: // com.minlish.app.data.repository.VocabularyRepositoryImpl 
          return (T) new VocabularyRepositoryImpl(singletonCImpl.topicDao(), singletonCImpl.wordDao(), singletonCImpl.provideVocabularyApiServiceProvider.get());

          case 2: // com.minlish.app.data.local.AppDatabase 
          return (T) DatabaseModule_ProvideAppDatabaseFactory.provideAppDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 3: // com.minlish.app.data.remote.api.VocabularyApiService 
          return (T) NetworkModule_ProvideVocabularyApiServiceFactory.provideVocabularyApiService(singletonCImpl.provideRetrofitProvider.get());

          case 4: // retrofit2.Retrofit 
          return (T) NetworkModule_ProvideRetrofitFactory.provideRetrofit(singletonCImpl.provideOkHttpClientProvider.get());

          case 5: // okhttp3.OkHttpClient 
          return (T) NetworkModule_ProvideOkHttpClientFactory.provideOkHttpClient(singletonCImpl.provideAuthInterceptorProvider.get());

          case 6: // com.minlish.app.data.remote.AuthInterceptor 
          return (T) NetworkModule_ProvideAuthInterceptorFactory.provideAuthInterceptor(singletonCImpl.userDao());

          case 7: // com.minlish.app.data.worker.StudyReminderWorker_AssistedFactory 
          return (T) new StudyReminderWorker_AssistedFactory() {
            @Override
            public StudyReminderWorker create(Context context, WorkerParameters workerParams2) {
              return new StudyReminderWorker(context, workerParams2, singletonCImpl.reviewDao(), singletonCImpl.notificationHelperProvider.get());
            }
          };

          case 8: // com.minlish.app.util.NotificationHelper 
          return (T) new NotificationHelper(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 9: // com.minlish.app.data.local.DatabaseSeeder 
          return (T) new DatabaseSeeder(singletonCImpl.cardDao(), singletonCImpl.reviewDao(), singletonCImpl.learningLogDao(), singletonCImpl.userStatsDao(), singletonCImpl.encryptedAuthStorageProvider.get(), singletonCImpl.csvWordProvider.get());

          case 10: // com.minlish.app.data.local.storage.EncryptedAuthStorage 
          return (T) new EncryptedAuthStorage(singletonCImpl.provideEncryptedSharedPreferencesProvider.get());

          case 11: // @javax.inject.Named("encrypted_prefs") android.content.SharedPreferences 
          return (T) AppModule_ProvideEncryptedSharedPreferencesFactory.provideEncryptedSharedPreferences(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 12: // com.minlish.app.data.local.CsvWordProvider 
          return (T) new CsvWordProvider(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 13: // com.minlish.app.data.repository.AuthRepositoryImpl 
          return (T) new AuthRepositoryImpl(singletonCImpl.encryptedAuthStorageProvider.get());

          case 14: // com.minlish.app.domain.usecase.CalculateSM2IntervalUseCase 
          return (T) UseCaseModule_ProvideCalculateSM2IntervalUseCaseFactory.provideCalculateSM2IntervalUseCase();

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
