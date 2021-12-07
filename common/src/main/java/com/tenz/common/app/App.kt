package com.tenz.common.app

import android.app.Application
import android.content.Context

/**
 * Application
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        mContext = this
    }

    companion object {

        var mContext : Application? = null

        fun getContext(): Context {
            return mContext!!
        }

    }

}