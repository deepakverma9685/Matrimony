package com.test.deepak.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.test.deepak.factory.ViewModelFactory;
import com.test.deepak.ui.ShaadiViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

     @Binds
    @IntoMap
    @ViewModelKey(ShaadiViewModel.class)
    protected abstract ViewModel shaadiViewModel(ShaadiViewModel myListViewModel);
}