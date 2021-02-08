package com.test.deepak.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.test.deepak.R;
import com.test.deepak.data.local.AppDatabase;
import com.test.deepak.data.local.entity.ShadiModel;
import com.test.deepak.databinding.ActivityListBinding;
import com.test.deepak.factory.ViewModelFactory;
import com.test.deepak.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class ListActivity extends BaseActivity {
    private static final String TAG = "ListActivity";
    @Inject
    ViewModelFactory viewModelFactory;
    ShaadiViewModel shaadiViewModel;

    private ActivityListBinding binding;
    private DataItemAdapter dataItemAdapter;
    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        shaadiViewModel = ViewModelProviders.of(this,viewModelFactory).get(ShaadiViewModel.class);
         appDatabase = AppDatabase.getInstance(this);


        setupREcy();

        shaadiViewModel.getCards();
        shaadiViewModel.getShaadimutableLiveData().observe(this, listResource -> {
            assert listResource != null;
            Log.e(TAG, "onChanged: "+listResource.data.toString());
            dataItemAdapter.setDataItemList(listResource.data);

        });

    }

    private void setupREcy() {
        List<ShadiModel> dataItemList = new ArrayList<>();
        binding.recydata.setLayoutManager(new LinearLayoutManager(this));
        dataItemAdapter = new DataItemAdapter(this, dataItemList);
        binding.recydata.setAdapter(dataItemAdapter);
        dataItemAdapter.notifyDataSetChanged();

        dataItemAdapter.setOnItemClickListener(new DataItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ShadiModel shadiModel = dataItemAdapter.getDataItem().get(position);
                if (view.getId() == R.id.acceptBtn){
                    shadiModel.setStatus("Member Accepted");
                    Toast.makeText(ListActivity.this, "Member Accepted", Toast.LENGTH_SHORT).show();

                }else if (view.getId() == R.id.rejectBtn){
                    shadiModel.setStatus("Member Declined");
                    Toast.makeText(ListActivity.this, "Member Declined", Toast.LENGTH_SHORT).show();
                }

                appDatabase.shadiDao().updateMovie(shadiModel);
            }
        });
    }

}