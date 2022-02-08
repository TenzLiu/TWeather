package com.tenz.common.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.tenz.common.utils.LogUtil
import com.tenz.common.widget.titlebar.TitleBar
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.ParameterizedType


/**
 * BaseActivity
 * VB ViewBinding
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(),TitleBar.OnTitleBarClickListener {

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
            LogUtil.d("activity:------------${componentName.className}")
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

    /**
     * 初始化titlebar
     * @param titleBar
     * @param title
     */
    open fun initTitleBar(titleBar: TitleBar, title: String) {
        initTitleBar(titleBar, title, false)
    }

    /**
     * 初始化titlebar
     * @param titleBar
     * @param title
     */
    open fun initTitleBar(titleBar: TitleBar, title: String, isShowGone: Boolean) {
        titleBar.setMOnTitleBarClickListener(this)
        if (isShowGone) {
            titleBar.setBackGone()
        }
        titleBar.setTitle(title)
    }

    /**
     * 返回
     */
    override fun back() {
        finish()
    }

    /**
     * 更多
     */
    override fun more() {

    }

    override fun onDestroy() {
        super.onDestroy()
        ui = null
    }


}