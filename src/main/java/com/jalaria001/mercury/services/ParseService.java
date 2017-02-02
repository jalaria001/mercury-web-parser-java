package com.jalaria001.mercury.services;

import com.jalaria001.mercury.entities.Article;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by JonAnder on 02/02/2017.
 */
public interface ParseService {

    @GET("parser")
    Call<Article> getArticle(
            @Query("url") String url
    );
}
