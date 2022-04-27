package com.jolaifa.mad_ind05_olaifa_julius

import android.content.Intent
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.jolaifa.mad_ind05_olaifa_julius.databinding.ActivityDetailBinding
import com.jolaifa.mad_ind05_olaifa_julius.databinding.ActivityMainBinding
import java.io.InputStream
import java.lang.Exception

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        val name = intent.getStringExtra("name")
        val area = intent.getStringExtra("area")

        binding.name.text = name
        binding.area.text = area
        binding.flag.setImageBitmap(getImageFromAsset(name!!, "flag"))
        binding.map.setImageBitmap(getImageFromAsset(name!!, "map"))

    }

    private fun getImageFromAsset(name: String, type: String): Bitmap? {
        val filename = getFileName(name)
        val assetManager: AssetManager = applicationContext.assets

        try {
            val inputStream: InputStream = assetManager.open("$type/${filename}_${type}.png")
            val bitmap = BitmapFactory.decodeStream(inputStream)
            return bitmap
        } catch (e: Exception) {
            return null
        }
    }

    private fun getFileName(name: String): String {
        return name.split(" ")[0].toLowerCase()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }


}