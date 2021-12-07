package com.tenz.common.utils

import android.content.Context
import android.os.Build
import com.tenz.common.app.App

object AppUtil {

    /**
     * 获取全局context
     */
    fun getContext(): Context {
        return App.getContext()
    }

    /**
     * 获取versionCode
     */
    fun getAppVersionCode(context: Context): Long {
        var versionCode = 0L
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(context.packageName, 0)
            versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                packageInfo.longVersionCode
            } else {
                packageInfo.versionCode.toLong()
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
        return versionCode
    }

    /**
     * 获取versionName
     */
    fun getAppVersionName(context: Context): String {
        var versionName = ""
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(context.packageName, 0)
            versionName = packageInfo.versionName
        }catch (e: Exception){
            e.printStackTrace()
        }
        return versionName
    }


}