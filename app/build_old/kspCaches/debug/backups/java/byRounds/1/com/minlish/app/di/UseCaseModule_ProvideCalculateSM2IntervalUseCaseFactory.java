package com.minlish.app.di;

import com.minlish.app.domain.usecase.CalculateSM2IntervalUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class UseCaseModule_ProvideCalculateSM2IntervalUseCaseFactory implements Factory<CalculateSM2IntervalUseCase> {
  @Override
  public CalculateSM2IntervalUseCase get() {
    return provideCalculateSM2IntervalUseCase();
  }

  public static UseCaseModule_ProvideCalculateSM2IntervalUseCaseFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CalculateSM2IntervalUseCase provideCalculateSM2IntervalUseCase() {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideCalculateSM2IntervalUseCase());
  }

  private static final class InstanceHolder {
    private static final UseCaseModule_ProvideCalculateSM2IntervalUseCaseFactory INSTANCE = new UseCaseModule_ProvideCalculateSM2IntervalUseCaseFactory();
  }
}
