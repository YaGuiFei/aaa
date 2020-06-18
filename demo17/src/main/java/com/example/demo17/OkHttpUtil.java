package com.example.demo17;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtil {
    static OkHttpClient okHttpClient = new OkHttpClient();
    //创建一个线程池
    static ExecutorService threadpool = Executors.newFixedThreadPool(30);
    public static String getRequest(final String url) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                //创建Request对象
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                //获取服务器返回的数据
                Response response = okHttpClient.newCall(request).execute();
                if (response.isSuccessful()&&response.body()!=null){
                    return response.body().string();
                }else {
                    return null; } }});
        //把futureTask放到线程池中去执行
        threadpool.execute(futureTask);
        return futureTask.get().toString();
    }
    public static String postRequest(final String url, final Map<String,String> map) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                FormBody.Builder builder = new FormBody.Builder();
                map.forEach(builder::add);
                FormBody body = builder.build();

                //创建Request对象
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                //获取服务器返回的数据
                Response response = okHttpClient.newCall(request).execute();
                if (response.isSuccessful()&&response.body()!=null){
                    return response.body().string();
                }else {
                    return null; } }});
        //把futureTask放到线程池中去执行
        threadpool.execute(futureTask);
        return futureTask.get().toString();
    }
}
