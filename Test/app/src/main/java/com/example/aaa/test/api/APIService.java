package com.example.aaa.test.api;

import com.example.aaa.test.model.Fund;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by vanirut on 14-Jan-16.
 */
public interface APIService {
    @GET("ltf")
    Call<List<Fund>> getLTFFund();

    @GET("rmf")
    Call<List<Fund>> getRMFFund();

    @GET("ltf/byRisk")
    Call<List<Fund>> getLTFbyRisk(@Query("risk") String risk);

    @GET("rmf/byRisk")
    Call<List<Fund>> getRMFbyRisk(@Query("risk") String risk);

}
