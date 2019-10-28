package com.anas.pokedex.api;

import com.anas.pokedex.model.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET("pokemon")
    Call<PokemonResponse> getResults(@Query("limit") int limit, @Query("offset") int offset);
}
