package com.example.mvp.Model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebRepository extends Observable {
    private ArrayList<DataItem> data = new ArrayList<>();
    private WebApi webApi;
    private Call<ArrayList<DataItem>> webCall;

    public WebRepository(WebApi webApi) {
        this.webApi = webApi;
    }

    public void registerCallback(Observer callback) {
        addObserver(callback);
    }

    public void unregisterCallback(Observer callback) {
        deleteObserver(callback);
    }

    public void loadWebDataSet() {
        webCall = webApi.downloadDataJson();
        webCall.enqueue(new Callback<ArrayList<DataItem>>() {
            @Override
            public void onResponse(Call<ArrayList<DataItem>> call, Response<ArrayList<DataItem>> response) {
                if (response != null && response.body() != null) {
                    data.clear();
                    data.addAll(response.body());
                }
                setChanged();
                notifyObservers();
            }

            @Override
            public void onFailure(Call<ArrayList<DataItem>> call, Throwable t) {
                t.printStackTrace();
                setChanged();
                notifyObservers();
            }
        });
    }

    public int getItemCount() {
        return data.size();
    }

    public DataItem getItem(int position) {
        return data.get(position);
    }
}
