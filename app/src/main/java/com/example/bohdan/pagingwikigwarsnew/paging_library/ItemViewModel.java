package com.example.bohdan.pagingwikigwarsnew.paging_library;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.arch.paging.PositionalDataSource;

import com.example.bohdan.pagingwikigwarsnew.Model;

public class ItemViewModel extends ViewModel {

    public LiveData<PagedList<Model>> modelPagedList;
    public LiveData<PositionalDataSource> positionalDataSourceLiveData;

    public ItemViewModel() {
        MyPositionalDataSourceFactory myPositionalDataSource = new MyPositionalDataSourceFactory();
        positionalDataSourceLiveData = myPositionalDataSource.getMutableLiveData();


        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(5).build();


        modelPagedList = (new LivePagedListBuilder(myPositionalDataSource, pagedListConfig))
                .build();



    }
}
