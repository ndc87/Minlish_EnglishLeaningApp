package com.minlish.app.data.local;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class CsvWordProvider_Factory implements Factory<CsvWordProvider> {
  private final Provider<Context> contextProvider;

  public CsvWordProvider_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public CsvWordProvider get() {
    return newInstance(contextProvider.get());
  }

  public static CsvWordProvider_Factory create(Provider<Context> contextProvider) {
    return new CsvWordProvider_Factory(contextProvider);
  }

  public static CsvWordProvider newInstance(Context context) {
    return new CsvWordProvider(context);
  }
}
