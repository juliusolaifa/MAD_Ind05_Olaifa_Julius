package com.jolaifa.mad_ind05_olaifa_julius

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jolaifa.mad_ind05_olaifa_julius.databinding.ActivityMainBinding
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    private lateinit var stateAdapter: StateAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        stateAdapter = StateAdapter(object : StateListener {
            override fun onClick(data: StateData) {
                val intent = Intent(applicationContext, DetailActivity::class.java)
                intent.putExtra("name", data.name)
                intent.putExtra("area", data.area)
                startActivity(intent)
                finish()
            }
        })
        binding.recyclerview.apply { adapter = stateAdapter }

        stateAdapter.addItemAndSubmit(getStatesData())
    }


    private fun getStatesData(): List<StateData> {
        val res = resources.openRawResource(R.raw.states)

        val typeToken = object : TypeToken<List<StateData>>() {}.type
        return Gson().fromJson(readTextFile(res), typeToken)
    }

    fun readTextFile(inputStream: InputStream): String? {
        val outputStream = ByteArrayOutputStream()
        val buf = ByteArray(1024)
        var len: Int
        try {
            while (inputStream.read(buf).also { len = it } != -1) {
                outputStream.write(buf, 0, len)
            }
            outputStream.close()
            inputStream.close()
        } catch (e: IOException) {
        }
        return outputStream.toString()
    }

}