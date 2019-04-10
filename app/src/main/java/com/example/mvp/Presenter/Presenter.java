package com.example.mvp.Presenter;

import com.example.mvp.App;
import com.example.mvp.Model.DataItem;
import com.example.mvp.Model.WebRepository;
import com.example.mvp.Presenter.callbacks.OnWebLoadComplete;

import java.util.Observable;

import javax.inject.Inject;

public class Presenter implements IPresenter{
    private IView view;

    @Inject
    WebRepository repository;

    private OnWebLoadCallback repositoryCallback;

    public Presenter() {
        App.getComponent().inject(this);
    }

    @Override
    public void onCreate(IView view) {
        this.view = view;
        repositoryCallback = new OnWebLoadCallback();
    }

    @Override
    public void onStart() {
        repository.registerCallback(repositoryCallback);
        //При старте View - сразу запускаем загрузку
        startDataLoad();
    }

    @Override
    public void onStop() {
        repository.unregisterCallback(repositoryCallback);
    }

    @Override
    public void swipeRefresh() {
        startDataLoad();
    }

    private void startDataLoad() {
        view.showProgressBar();
        repository.loadWebDataSet();
    }

    @Override
    public int getItemCount() {
        return repository.getItemCount();
    }

    @Override
    public DataItem getItem(int position) {
        return repository.getItem(position);
    }

    class OnWebLoadCallback implements OnWebLoadComplete {
        @Override
        public void update(Observable o, Object arg) {
            view.hideProgressBar();
            view.refreshRecyclerView();
        }
    }


}
