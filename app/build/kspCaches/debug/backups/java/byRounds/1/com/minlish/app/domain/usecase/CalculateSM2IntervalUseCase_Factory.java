package com.minlish.app.domain.usecase;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class CalculateSM2IntervalUseCase_Factory implements Factory<CalculateSM2IntervalUseCase> {
  @Override
  public CalculateSM2IntervalUseCase get() {
    return newInstance();
  }

  public static CalculateSM2IntervalUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CalculateSM2IntervalUseCase newInstance() {
    return new CalculateSM2IntervalUseCase();
  }

  private static final class InstanceHolder {
    private static final CalculateSM2IntervalUseCase_Factory INSTANCE = new CalculateSM2IntervalUseCase_Factory();
  }
}
