package com.example.mvp.Presenter;

import com.example.mvp.Model.DataItem;

public interface IPresenter {
    void onCreate(IView view);
    void onStop();
    void onStart();

    void swipeRefresh();

    int getItemCount();
    DataItem getItem(int position);
}
