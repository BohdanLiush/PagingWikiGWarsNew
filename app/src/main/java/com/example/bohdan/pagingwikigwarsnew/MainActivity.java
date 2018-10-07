package com.example.bohdan.pagingwikigwarsnew;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.bohdan.pagingwikigwarsnew.paging_library.BackgroundThreadExecutor;
import com.example.bohdan.pagingwikigwarsnew.paging_library.ItemViewModel;
import com.example.bohdan.pagingwikigwarsnew.paging_library.MainThreadExecutor;
import com.example.bohdan.pagingwikigwarsnew.paging_library.ModelAdapter;
import com.example.bohdan.pagingwikigwarsnew.paging_library.MyPositionalDataSource;

import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerlist23);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getBaseContext(),LinearLayoutManager.VERTICAL,false);
        //layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        /*MyPositionalDataSource myPositionalDataSource = new MyPositionalDataSource();

        PagedList.Config config = new PagedList.Config.Builder().
                setEnablePlaceholders(false).setPageSize(5).build();

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(5).build();


        *//*PagedList<Model> pagedList = new PagedList.Builder<>(myPositionalDataSource, config)
                .setNotifyExecutor(new MainThreadExecutor()).
                        setFetchExecutor(new BackgroundThreadExecutor()).build();*//*

        PagedList<Model> pagedList = new PagedList.Builder<>(myPositionalDataSource, config)
                .setFetchExecutor(new BackgroundThreadExecutor()).
                        setNotifyExecutor(new MainThreadExecutor()).build();

        final ModelAdapter modelAdapter = new ModelAdapter();
        modelAdapter.submitList(pagedList);

        recyclerView.setAdapter(modelAdapter);*/

        ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        final ModelAdapter modelAdapter1 = new ModelAdapter();

        itemViewModel.modelPagedList.observe(this, new Observer<PagedList<Model>>() {
            @Override
            public void onChanged(@Nullable PagedList<Model> models) {
                modelAdapter1.submitList(models);
                recyclerView.setAdapter(modelAdapter1);

            }
        });
    }
}
