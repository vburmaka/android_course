package com.example.lesson12camera

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var btnCapture: Button? = null
    private var imgCapture: ImageView? = null
    private val Image_Capture_Code = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnCapture = findViewById<View>(R.id.btnTakePicture) as Button
        imgCapture = findViewById<View>(R.id.capturedImage) as ImageView
        btnCapture?.setOnClickListener {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, Image_Capture_Code)
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Image_Capture_Code) {
            if (resultCode == RESULT_OK) {
                val bp = data?.extras!!["data"] as Bitmap?
                imgCapture?.setImageBitmap(bp)
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            }
        }
    }
}