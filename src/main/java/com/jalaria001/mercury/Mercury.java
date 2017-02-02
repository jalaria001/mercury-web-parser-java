package com.jalaria001.mercury;

import com.google.gson.Gson;
import com.jalaria001.mercury.services.ParseService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JonAnder on 02/02/2017.
 */
public class Mercury {

    public static final String API_HOST = "mercury.postlight.com";
    public static final String API_URL = "https://" + API_HOST + "/";

    /**
     * API key query parameter name.
     */
    public static final String PARAM_API_KEY = "api_key";

    private OkHttpClient okHttpClient;
    private Retrofit retrofit;

    private String apiKey;

    /**
     * Create a new manager instance.
     *
     * @param apiKey Your Mercury Web Parser API key.
     */
    public Mercury(String apiKey) {
        this.apiKey = apiKey;
    }

    public void apiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String apiKey() {
        return apiKey;
    }

    /**
     * Creates a {@link Retrofit.Builder} that sets the base URL, adds a Gson converter and sets {@link #okHttpClient()}
     * as its client.
     *
     * @see #okHttpClient()
     */
    protected Retrofit.Builder retrofitBuilder() {
        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient());
    }

    /**
     * Returns the default OkHttp client instance. It is strongly recommended to override this and use your app
     * instance.
     *
     * @see #setOkHttpClientDefaults(OkHttpClient.Builder)
     */
    protected synchronized OkHttpClient okHttpClient() {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            setOkHttpClientDefaults(builder);
            okHttpClient = builder.build();
        }
        return okHttpClient;
    }

    /**
     * Adds an interceptor to add the api key query parameter and to log requests.
     */
    protected void setOkHttpClientDefaults(OkHttpClient.Builder builder) {
        builder.addInterceptor(new MercuryInterceptor(this));
    }

    /**
     * Return the current {@link Retrofit} instance. If none exists (first call, auth changed), builds a new one.
     * <p>When building, sets the base url and a custom client with an {@link Interceptor} which supplies authentication
     * data.
     */
    protected Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = retrofitBuilder().build();
        }
        return retrofit;
    }

    public ParseService parseService() {
        return getRetrofit().create(ParseService.class);
    }
}
