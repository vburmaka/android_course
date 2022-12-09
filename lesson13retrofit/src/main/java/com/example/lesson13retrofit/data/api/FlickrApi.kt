package com.example.lesson13retrofit.data.api

import retrofit2.Call
import retrofit2.http.GET

interface FlickrApi {
    @GET("services/rest/?method=flickr.interestingness.getList&api_key=d34e7694d0f97383d440aa0232089cb0&format=json&nojsoncallback=1&extras=url_s")
    fun fetchContents(): Call<FlickrResponse>
}