package com.example.async_await

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {

                //answer1,2を同時にlaunch（合計3秒）
                var answer1 = async { networkCall1() }
                var answer2 = async { networkCall2() }

                Log.d(TAG,"Answer1 is ${answer1.await()}")
                Log.d(TAG,"Answer2 is ${answer2.await()}")

            }
            Log.d(TAG, "Request took $time ms.")
        }
    }


    suspend fun networkCall1(): String{
        delay(3000L)
        return "Answer 1"
    }

    suspend fun networkCall2(): String{
        delay(3000L)
        return "Answer 2"
    }
}
