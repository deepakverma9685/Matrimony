package com.test.deepak.di.module;

import com.test.deepak.ui.ListActivity;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract ListActivity contributeListActivity();
}