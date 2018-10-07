package com.example.bohdan.pagingwikigwarsnew.paging_library;

import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;

import com.example.bohdan.pagingwikigwarsnew.Model;
import com.example.bohdan.pagingwikigwarsnew.ModelApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyPositionalDataSource extends PositionalDataSource<Model> {

    public final String URL = "https://api.guildwars2.com/v2/";
    public List<Model> listModel = new ArrayList<>();
    public List<Model> modelList = new ArrayList<>();

    public String number = "";
    public int count = 0;
    //public String numberObj = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99";
    public int numberObj = 25;
    public Call<List<Model>> idsCall;



    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull final LoadInitialCallback<Model> callback) {

        /**-------------------------------------------------------------*/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ModelApi idsApi = retrofit.create(ModelApi.class);

        idsCall = idsApi.idsInfo(getIdsLoop(numberObj));

        try {
            listModel = idsCall.execute().body();
            System.out.println("erica");
        } catch (IOException e) {
            e.printStackTrace();
        }

        callback.onResult(listModel, params.requestedStartPosition);
//        invalidate();

       /* idsCall.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                if (response.body() != null) {
                    listModel = response.body();

                    if (listModel!=null){
                        modelList.addAll(listModel);
                    }
                    callback.onResult(listModel, 0);
                    System.out.println("hello");

                }
            }
            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                System.out.println("Sisku");
            }
        });*/
        /**-------------------------------------------------------------*/
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull final LoadRangeCallback<Model> callback) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ModelApi idsApi = retrofit.create(ModelApi.class);

        idsCall = idsApi.idsInfo(getIdsLoop(numberObj));

        try {
            listModel = idsCall.execute().body();
            System.out.println("erica");
        } catch (IOException e) {
            e.printStackTrace();
        }

        callback.onResult(listModel);

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ModelApi idsApi = retrofit.create(ModelApi.class);

        idsCall = idsApi.idsInfo(numberObj);
        idsCall.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                if (response.body()!=null){
                    listModel = response.body();
                }

                if (listModel!=null){
                    modelList.addAll(listModel);
                }
                callback.onResult(modelList);
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {

            }
        });*/

    }

        public String getIdsLoop(int n) { // якшо різниця була 20 об.
            number = "";  // обнулення строки після 100 або іншого числа, тобто строка буде іти від 100, 101 як нам і треба
            for (int i = 0; i < n+10; i++) {
                number = number + count + ","; //101 do 120 // другий прохід
                count++;  //100
            }
            System.out.println("count " + count);
            return number;
        }
}
