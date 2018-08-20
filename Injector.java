package com.app.everz.utils.others;

import android.util.Log;


import com.app.everz.BuildConfig;
import com.app.everz.utils.interfaces.InterfaceApi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;
import static okhttp3.logging.HttpLoggingInterceptor.Level.NONE;


/**
 * Created by 123 on 25-09-2017.
 */

public class Injector {

    String BASE_URL = "your base url";

    private static Retrofit provideRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(provideHttpLoggingInterceptor())
                .addInterceptor(provideHeaderInterceptor())
                .addInterceptor(new ForbiddenInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(45, TimeUnit.SECONDS)
                .writeTimeout(45, TimeUnit.SECONDS)

                .build();
    }

    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.d("Injector", message);
                    }
                });
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? BODY : NONE);
        return httpLoggingInterceptor;
    }

    public static InterfaceApi provideApi() {
        return provideRetrofit(Injector.BASE_URL).create(InterfaceApi.class);
    }

    private static Interceptor provideHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                SharedPrefUtil helper = SharedPrefUtil.getInstance();
                Request request;
                if (!helper.getAuthToken().equals("")) {
                    request = chain.request().newBuilder()
                            .header("Accept", "application/json")
                            .build();
                } else {
                    request = chain.request().newBuilder().build();
                }
                return chain.proceed(request);
            }
        };
    }

    public static class ForbiddenInterceptor implements Interceptor {

        @Override
        public Response intercept(final Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            if (response.code() == 401) {
                SharedPrefUtil.getInstance().setLogin(false);
                App.tokenExpired();
            }
            return response;
        }
    }
}
