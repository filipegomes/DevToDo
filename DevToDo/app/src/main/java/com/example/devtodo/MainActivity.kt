package com.example.devtodo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        ic_logo.startAnimation(AnimationUtils.loadAnimation(this,R.anim.splash_in)) //Calling Logo Animation
        Handler().postDelayed({
            ic_logo.startAnimation(AnimationUtils.loadAnimation(this,R.anim.splash_out))//Ending Animation logo
            Handler().postDelayed({
                ic_logo.visibility = View.GONE //Calling next activiy after animation
                startActivity(Intent(this,DashboardActivity::class.java))
                finish()
            }, 500)
        },1500)
    }
}
