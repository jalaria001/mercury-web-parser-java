package com.jalaria001.mercury;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by JonAnder on 02/02/2017.
 */
public class MercuryInterceptor implements Interceptor{

    private Mercury mercury;

    public MercuryInterceptor(Mercury mercury) {
        this.mercury = mercury;
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        return handleIntercept(chain, mercury.apiKey());
    }

    /**
     * If the host matches {@link Mercury#API_HOST} adds a query parameter with the API key.
     */
    public static Response handleIntercept(Interceptor.Chain chain, String apiKey) throws IOException {
        Request original = chain.request();

        Request request = original.newBuilder()
                .header("Content-Type", "application/json")
                .header("x-api-key", apiKey)
                .method(original.method(), original.body())
                .build();

        return chain.proceed(request);
    }

}
