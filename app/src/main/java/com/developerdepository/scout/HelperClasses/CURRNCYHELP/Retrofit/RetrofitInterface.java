package com.developerdepository.scout.HelperClasses.CURRNCYHELP.Retrofit;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitInterface {


    @GET("v6/4f6a6f6ebccbd40d1aab051a/latest/{currency}")
    Call<JsonObject> getExchangeCurrency(@Path("currency") String currency);
}
