package com.example.mvp.Model;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebApi {
    @GET("/Simplycissimys/test-android-json/master/test.json")
    Call<ArrayList<DataItem>> downloadDataJson();
}
