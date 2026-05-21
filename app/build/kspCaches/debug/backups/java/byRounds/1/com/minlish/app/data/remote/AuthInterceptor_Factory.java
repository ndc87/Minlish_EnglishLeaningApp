package com.minlish.app.data.remote;

import com.minlish.app.data.local.dao.UserDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class AuthInterceptor_Factory implements Factory<AuthInterceptor> {
  private final Provider<UserDao> userDaoProvider;

  public AuthInterceptor_Factory(Provider<UserDao> userDaoProvider) {
    this.userDaoProvider = userDaoProvider;
  }

  @Override
  public AuthInterceptor get() {
    return newInstance(userDaoProvider.get());
  }

  public static AuthInterceptor_Factory create(Provider<UserDao> userDaoProvider) {
    return new AuthInterceptor_Factory(userDaoProvider);
  }

  public static AuthInterceptor newInstance(UserDao userDao) {
    return new AuthInterceptor(userDao);
  }
}
