package com.example.mvp.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.mvp.App;
import com.example.mvp.Presenter.IPresenter;
import com.example.mvp.Presenter.IView;
import com.example.mvp.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IView {
    @BindView(R.id.swipeLayout)
    android.support.v4.widget.SwipeRefreshLayout swipeLayout;
    @BindView(R.id.recyclerView)
    android.support.v7.widget.RecyclerView recyclerView;

    @Inject
    IPresenter presenter;

    RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.getComponent().inject(this);
        presenter.onCreate(this);
        initSwipeLayout();
        initRecyclerView();
    }

    private void initSwipeLayout() {
        swipeLayout.setOnRefreshListener(() -> presenter.swipeRefresh());
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new RecyclerAdapter(presenter);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void showProgressBar() {
        swipeLayout.setRefreshing(true);
    }

    @Override
    public void hideProgressBar() {
        swipeLayout.setRefreshing(false);
    }

    @Override
    public void refreshRecyclerView() {
        adapter.notifyDataSetChanged();
    }
}
