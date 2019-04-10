package com.example.mvp;

import com.example.mvp.Presenter.Presenter;
import com.example.mvp.View.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(Presenter presenter);
}
