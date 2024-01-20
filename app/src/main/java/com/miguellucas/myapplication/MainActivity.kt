
package com.miguellucas.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory



private val retrofit = Retrofit.Builder()

    .baseUrl("https://api.chucknorris.io/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private val chuckNorrisApi = retrofit.create(ChuckNorrisApi::class.java)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button_joke)
        val textView: TextView = findViewById(R.id.facttv)

        button.setOnClickListener {
            val call = chuckNorrisApi.getRandomJoke()
            call.enqueue(object : Callback<Joke> {
                override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                    if (response.isSuccessful) {
                        val joke = response.body()
                        textView.text = joke?.value
                    } else {
                        textView.text = "Error: ${response.code()}"
                    }
                }

                override fun onFailure(call: Call<Joke>, t: Throwable) {
                    textView.text = "Error: ${t.message}"
                }
            })
        }
    }
}





