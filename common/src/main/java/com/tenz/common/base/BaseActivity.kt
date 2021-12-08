package com.tenz.common.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.ParameterizedType

/**
 * BaseActivity
 * VB ViewBinding
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    var ui : VB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            val genericSuperclass = this.javaClass.genericSuperclass
            val parameterizedType = genericSuperclass as ParameterizedType
            val vmClass = parameterizedType.actualTypeArguments[0] as Class<VB>
            val method = vmClass.getMethod("inflate", LayoutInflater::class.java)
            ui = method.invoke(null, layoutInflater) as VB
            setContentView(ui!!.root)
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
        init()
    }

    /**
     * 初始化
     */
    abstract fun init()

    override fun onDestroy() {
        super.onDestroy()
        ui = null
    }


}