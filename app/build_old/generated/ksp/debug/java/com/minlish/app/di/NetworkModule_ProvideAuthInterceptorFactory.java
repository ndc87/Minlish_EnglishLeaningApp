package com.minlish.app.di;

import com.minlish.app.data.local.dao.UserDao;
import com.minlish.app.data.remote.AuthInterceptor;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
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
public final class NetworkModule_ProvideAuthInterceptorFactory implements Factory<AuthInterceptor> {
  private final Provider<UserDao> userDaoProvider;

  public NetworkModule_ProvideAuthInterceptorFactory(Provider<UserDao> userDaoProvider) {
    this.userDaoProvider = userDaoProvider;
  }

  @Override
  public AuthInterceptor get() {
    return provideAuthInterceptor(userDaoProvider.get());
  }

  public static NetworkModule_ProvideAuthInterceptorFactory create(
      Provider<UserDao> userDaoProvider) {
    return new NetworkModule_ProvideAuthInterceptorFactory(userDaoProvider);
  }

  public static AuthInterceptor provideAuthInterceptor(UserDao userDao) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideAuthInterceptor(userDao));
  }
}
