package com.test.deepak.ui;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.test.deepak.data.Resource;
import com.test.deepak.data.local.dao.ShadiDao;
import com.test.deepak.data.local.entity.ShadiModel;
import com.test.deepak.data.remote.api.UrlServices;
import com.test.deepak.data.repository.ShaadiRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ShaadiViewModel extends ViewModel {
    private static final String TAG = "ShaadiViewModel";
    private ShaadiRepository shaadiRepository;
    private MutableLiveData<Resource<List<ShadiModel>>> shaadimutableLiveData = new MutableLiveData<>();


    @Inject
    public ShaadiViewModel(UrlServices urlServices,ShadiDao shadiDao) {
        this.shaadiRepository = new ShaadiRepository(urlServices,shadiDao);
    }

    public MutableLiveData<Resource<List<ShadiModel>>> getShaadimutableLiveData() {
        return shaadimutableLiveData;
    }

    public void getCards() {
        shaadiRepository.fetchAllCards()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listResource ->{
                    getShaadimutableLiveData().postValue(listResource);
                });
    }




}
