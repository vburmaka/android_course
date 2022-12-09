package com.example.lesson13retrofit.data.api

import com.example.lesson13retrofit.models.GalleryItem
import com.google.gson.annotations.SerializedName

class PhotoResponse {
    @SerializedName("photo")
    lateinit var galleryItems: List<GalleryItem>
}