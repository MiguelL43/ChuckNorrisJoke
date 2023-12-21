package com.miguellucas.myapplication

import retrofit2.Call
import retrofit2.http.GET

interface ChuckNorrisApi {
    @GET("jokes/random")
    fun getRandomJoke(): Call<Joke>
}