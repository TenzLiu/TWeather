package com.tenz.tweather

import android.content.Intent
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import com.tenz.common.base.BaseActivity
import com.tenz.tweather.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun init() {
        startAnima()
    }

    private fun startAnima() {
        val animationSet = AnimationSet(false)
        val alphaAnimation = AlphaAnimation(0.5f, 1.0f)
        alphaAnimation.duration = 2000L
        animationSet.addAnimation(alphaAnimation)
        animationSet.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }

        })
        binding!!.ivLogo.animation = animationSet
        binding!!.tvAppName.animation = animationSet
        binding!!.tvVersionName.animation = animationSet
    }

}