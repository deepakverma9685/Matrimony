package com.test.deepak.data.remote.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Deepak Kumar Verma on 06-02-2021.
 * Company Shantiinfotech.
 */
public class ResultsModel {

    @SerializedName("results")
    private List<VoidDataModel> voidDataModels;

    public List<VoidDataModel> getVoidDataModels() {
        return voidDataModels;
    }

    public void setVoidDataModels(List<VoidDataModel> voidDataModels) {
        this.voidDataModels = voidDataModels;
    }
}
