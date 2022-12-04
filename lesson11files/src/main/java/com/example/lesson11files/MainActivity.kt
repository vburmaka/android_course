package com.example.lesson11files

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.*


private const val REQUEST_EXTERNAL_STORAGE = 1
private val PERMISSIONS_STORAGE = arrayOf<String>(
    Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.WRITE_EXTERNAL_STORAGE
)
class MainActivity : AppCompatActivity(), OnClickListener{

    val TAG = "MainActivity"

    val FILENAME = "file"

    val DIR_SD = "MyFiles"
    val FILENAME_SD = "fileSD"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonWrite = findViewById<Button>(R.id.btnWrite)
        val buttonRead = findViewById<Button>(R.id.btnRead)
        val buttonWriteSD = findViewById<Button>(R.id.btnWriteSD)
        val buttonReadSD = findViewById<Button>(R.id.btnReadSD)

        buttonWrite.setOnClickListener(this)
        buttonRead.setOnClickListener(this)
        buttonWriteSD.setOnClickListener(this)
        buttonReadSD.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnWrite -> writeFile()
            R.id.btnRead -> readFile()
            R.id.btnWriteSD -> writeFileSD()
            R.id.btnReadSD -> readFileSD()
        }
    }

    private fun writeFile() {
        try {
            val bw = BufferedWriter(
                OutputStreamWriter(
                    openFileOutput(FILENAME, MODE_PRIVATE)
                )
            )
            bw.write("Custom file content")
            bw.close()
            Log.d(TAG, "The file has been written")
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun readFile() {
        try {
            val br = BufferedReader(
                InputStreamReader(
                    openFileInput(FILENAME)
                )
            )
            var str: String? = ""
            while (br.readLine().also { str = it } != null) {
                str?.let {
                    Log.d(TAG, it)
                }
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun writeFileSD() {
        if (!verifyStoragePermissions()){
            Log.d(TAG, "SD access error " + Environment.getExternalStorageState())
            return
        }
        if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) {
            Log.d(TAG, "SD not mounted " + Environment.getExternalStorageState())
            return
        }
        if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED_READ_ONLY) {
            Log.d(TAG, "SD mounted read only." + Environment.getExternalStorageState())
            return
        }

        var sdPath: File = Environment.getExternalStorageDirectory()
        sdPath = File(sdPath.absolutePath + "/" + DIR_SD)
        sdPath.mkdirs()

        Log.d(TAG, "sdPath.canWrite(): " + sdPath.canWrite())
        Log.d(TAG, "Created: " + sdPath.absolutePath +": isExist = ${sdPath.exists()}")

        val sdFile = File(sdPath, FILENAME_SD)

        try {
            val bw = BufferedWriter(FileWriter(sdFile))
            bw.write("File content on SD")
            bw.close()
            Log.d(TAG, "File content has been written to SD: " + sdFile.absolutePath)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun readFileSD() {
        if (!verifyStoragePermissions()){
            Log.d(TAG, "SD access error " + Environment.getExternalStorageState())
            return
        }
        if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) {
            Log.d(TAG, "SD card access error " + Environment.getExternalStorageState())
            return
        }

        var sdPath: File = Environment.getExternalStorageDirectory()
        sdPath = File(sdPath.absolutePath + "/" + DIR_SD)
        val sdFile = File(sdPath, FILENAME_SD)
        try {
            val br = BufferedReader(FileReader(sdFile))
            var str: String? = ""
            while (br.readLine().also { str = it } != null) {
                str?.let {
                    Log.d(TAG, it)
                }
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun verifyStoragePermissions(): Boolean {
        val permission: Int =
            ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE
            )
            return false
        }
        return true
    }
}