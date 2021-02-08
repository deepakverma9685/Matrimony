package com.test.deepak.data.repository;

import android.support.annotation.NonNull;

import com.test.deepak.data.NetworkBoundResource;
import com.test.deepak.data.Resource;
import com.test.deepak.data.local.dao.ShadiDao;
import com.test.deepak.data.local.entity.ShadiModel;
import com.test.deepak.data.remote.api.UrlServices;
import com.test.deepak.data.remote.models.ResultsModel;
import com.test.deepak.data.remote.models.VoidDataModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Observable;


@Singleton
public class ShaadiRepository {
    private static final String TAG = "MyDataRepository";
    private UrlServices urlServices;
    private ShadiDao shadiDao;

    public ShaadiRepository(UrlServices urlServices, ShadiDao shadiDao) {
        this.urlServices = urlServices;
        this.shadiDao = shadiDao;
    }


    public Observable<Resource<List<ShadiModel>>> fetchAllCards() {
        return new NetworkBoundResource<List<ShadiModel>, ResultsModel>() {
            @Override
            protected void saveCallResult(@NonNull ResultsModel item) {
                List<ShadiModel> shadiModelList = new ArrayList<>();

                for(VoidDataModel voidDataModel : item.getVoidDataModels()) {
                    ShadiModel shadiModel = new ShadiModel();
                    String fullname = voidDataModel.getName().getFirst()+" "+voidDataModel.getName().getLast();
                    shadiModel.setFullname(fullname);
                    shadiModel.setDob(voidDataModel.getDob().getAge()+"");
                    shadiModel.setImage(voidDataModel.getPicture().getLarge());
                    shadiModel.setStatus("");
                    shadiModelList.add(shadiModel);
                }
                shadiDao.insertMovies(shadiModelList);
            }

            @Override
            protected boolean shouldFetch() {
                return true;
            }

            @NonNull
            @Override
            protected Flowable<List<ShadiModel>> loadFromDb() {
                List<ShadiModel> movieEntities = shadiDao.getAllCards();
                if(movieEntities == null || movieEntities.isEmpty()) {
                    return Flowable.empty();
                }
                return Flowable.just(movieEntities);
            }

            @NonNull
            @Override
            protected Observable<Resource<ResultsModel>> createCall() {
                return urlServices.getAllCards().flatMap(res-> Observable.just(res == null
                        ? Resource.error("", null)
                        : Resource.success(res)));
            }
        }.getAsObservable();
    }



}
