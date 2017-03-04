package com.ragdroid.mockstar.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by garimajain on 05/03/17.
 */

public interface PokemonService {

    @Headers("Content-Type: application/json")
    @GET("pokemon/{id}")
    public Observable<Pokemon> getPokemon(@Path("id") String id);

}
