package com.example.mvp;

import com.example.mvp.Model.WebApi;
import com.example.mvp.Model.WebRepository;
import com.example.mvp.Presenter.IPresenter;
import com.example.mvp.Presenter.Presenter;
import com.google.gson.GsonBuilder;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {
    private WeakReference<App> refApplication;

    public AppModule(App refApplication) {
        this.refApplication = new WeakReference<>(refApplication);
    }

    @Provides
    @Singleton
    WeakReference<App> applicationWeakReference() {
        return refApplication;
    }

    @Provides
    @Singleton
    IPresenter getPresenter() {
        return new Presenter();
    }

    @Provides
    @Singleton
    WebRepository getRepository(WebApi webApi) {
        return new WebRepository(webApi);
    }

    @Provides
    @Singleton
    WebApi getWebApi(Retrofit retrofit) {
        return retrofit.create(WebApi.class);
    }

    @Provides
    @Singleton
    Retrofit getRetrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(App.BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient()
                .newBuilder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }

        return builder.build();
    }

    @Provides
    @Singleton
    GsonConverterFactory getGsonConverterFactory() {
        return GsonConverterFactory.create(new GsonBuilder().create());
    }
}
