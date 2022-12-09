package com.example.lesson13retrofit.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson13retrofit.R

private const val TAG = "PhotoGalleryFragment"
class PhotoGalleryFragment : Fragment() {

    private val photoGalleryViewModel: PhotoGalleryViewModel by viewModels<PhotoGalleryViewModel>()
    private var photoRecyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_photo_gallery, container, false)
        photoRecyclerView = view.findViewById<RecyclerView>(R.id.photo_recycler_view)
        photoRecyclerView?.layoutManager = GridLayoutManager(context, 3)

        photoGalleryViewModel.galleryItemLiveData.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "Response received: $it")
            photoRecyclerView?.adapter = PhotoAdapter(requireContext(), it)
        }
        )
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() = PhotoGalleryFragment()
    }
}