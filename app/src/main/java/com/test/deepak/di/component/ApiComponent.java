package com.test.deepak.di.component;


import android.app.Application;

import com.test.deepak.AppController;
import com.test.deepak.di.module.ActivityModule;
import com.test.deepak.di.module.ApiModule;
import com.test.deepak.di.module.DbModule;
import com.test.deepak.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {ApiModule.class, ViewModelModule.class, DbModule.class,
        AndroidSupportInjectionModule.class, ActivityModule.class})
public interface ApiComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        @BindsInstance
        Builder apiModule(ApiModule apiModule);

        @BindsInstance
        Builder dbModule(DbModule dbModule);

        ApiComponent build();
    }

    void inject(AppController appController);
}
