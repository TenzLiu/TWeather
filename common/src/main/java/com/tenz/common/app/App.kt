package com.tenz.common.app

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.tenz.common.BuildConfig
import com.tenz.common.utils.AppUtil

/**
 * Application
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        mContext = this
        initARoute()
    }

    companion object {

        var mContext : Application? = null

        fun getContext(): Context {
            return mContext!!
        }

    }

    fun initARoute() {
        if(BuildConfig.DEBUG){
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

}