package com.tenz.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.ParameterizedType

/**
 * BaseFragment
 * VB ViewBinding
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    var ui : VB? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        try {
            val genericSuperclass = this.javaClass.genericSuperclass
            val parameterizedType = genericSuperclass as ParameterizedType
            val vmClass = parameterizedType.actualTypeArguments[0] as Class<VB>
            val method = vmClass.getMethod("inflate", LayoutInflater::class.java)
            ui = method.invoke(null, layoutInflater) as VB
            return ui!!.root
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
        return null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        ui = null
    }

    /**
     * 初始化
     */
    abstract fun init()


}