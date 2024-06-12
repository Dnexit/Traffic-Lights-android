package com.example.trafficlights

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import java.util.*

class MainActivity : Activity() {
    var text: TextView? = null
    var imgSemafor: ImageView? = null
    var timer: Timer? = null
    var counter: Int = 0
    var isRun = false
    var imageArray: IntArray = intArrayOf(R.drawable.semafor_red, R.drawable.semafor_yellow, R.drawable.semafor_green)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imgSemafor = findViewById(R.id.imgSemafor)
        text = findViewById((R.id.text))
    }

    fun onClickStartStop(view: View) {

        view as ImageButton
        if (!isRun) {
            startStop()
            view.setImageResource(R.drawable.button_stop)
            isRun = true
            text?.setText("Светофор горит!")
        } else {
            timer?.cancel()
            //imgSemafor?.setImageResource(R.drawable.semafor_grey)
            view.setImageResource(R.drawable.button_start)
            isRun = false
            text?.setText("Светофор приостановлен")
            //counter = 0
        }

    }

    fun startStop() {
        timer = Timer()
        timer?.schedule(object :TimerTask() {
            override fun run() {
                runOnUiThread {
                    imgSemafor?.setImageResource(imageArray[counter])
                    counter++
                    if (counter == 3) {
                        counter = 0
                    }
                }
            }

        }, 10, 500)
    }
}