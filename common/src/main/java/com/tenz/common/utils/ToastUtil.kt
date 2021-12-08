package com.tenz.common.utils

import android.widget.Toast

/**
 * ToastUtil
 */
object ToastUtil {

    private var time: Long = 0
    private var oldMsg: String? = null

    fun show(msg: String) {
        if (msg != oldMsg) {
            create(msg)
            time = System.currentTimeMillis()
        } else {
            if (System.currentTimeMillis() - time > 2000) {
                create(msg)
                time = System.currentTimeMillis()
            }
        }
        oldMsg = msg
    }

    private fun create(massage: String) {
        val toast = Toast.makeText(AppUtil.getContext(), massage, Toast.LENGTH_SHORT)
        toast.show()
    }
}