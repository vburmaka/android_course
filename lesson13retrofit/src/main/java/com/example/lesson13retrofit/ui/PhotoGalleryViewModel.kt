package com.example.lesson13retrofit.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.lesson13retrofit.data.FlickrFetchRepository
import com.example.lesson13retrofit.models.GalleryItem

class PhotoGalleryViewModel: ViewModel() {
    val galleryItemLiveData: LiveData<List<GalleryItem>>

    init {
        galleryItemLiveData = FlickrFetchRepository().fetchContents()
    }
}