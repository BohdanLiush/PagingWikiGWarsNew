package com.example.bohdan.pagingwikigwarsnew.paging_library;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PositionalDataSource;

public class MyPositionalDataSourceFactory extends DataSource.Factory {

    public MutableLiveData<PositionalDataSource> mutableLiveData = new MutableLiveData();


    @Override
    public DataSource create() {

        MyPositionalDataSource myPositionalDataSource = new MyPositionalDataSource();
        mutableLiveData.postValue(myPositionalDataSource);


        return myPositionalDataSource;
    }

    public MutableLiveData getMutableLiveData() {
        return mutableLiveData;
    }
}
