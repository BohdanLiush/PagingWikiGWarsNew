package com.example.bohdan.pagingwikigwarsnew.paging_library;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BackgroundThreadExecutor implements Executor {
    private ExecutorService executorService =
            Executors.newFixedThreadPool(5);

    @Override public void execute(@NonNull Runnable command) {
        executorService.execute(command);
    }
}