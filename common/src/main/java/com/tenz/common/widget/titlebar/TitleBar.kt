package com.tenz.common.widget.titlebar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.tenz.common.R

/**
 * TitleBar
 */
class TitleBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    private var rlBack: RelativeLayout? = null
    private var tvTitle: TextView? = null
    private var tvMore: TextView? = null
    private var mOnTitleBarClickListener: OnTitleBarClickListener? = null

    init {
        init(context)
    }

    fun setMOnTitleBarClickListener(onTitleBarClickListener: OnTitleBarClickListener?) {
        this.mOnTitleBarClickListener = onTitleBarClickListener
    }

    private fun init(context: Context){
        val inflate: View =
            LayoutInflater.from(context).inflate(R.layout.view_title_bar, this, true)
        rlBack = inflate.findViewById(R.id.rl_back)
        tvTitle = inflate.findViewById(R.id.tv_title)
        tvMore = inflate.findViewById(R.id.tv_more)
        rlBack?.setOnClickListener {
            mOnTitleBarClickListener?.let {
                mOnTitleBarClickListener!!.back()
            }
        }
        tvMore?.setOnClickListener {
            mOnTitleBarClickListener?.let {
                mOnTitleBarClickListener!!.more()
            }
        }
    }

    fun setTitle(title: String?) {
        tvTitle!!.text = title
    }

    fun setBackGone() {
        tvTitle!!.visibility = GONE
    }

    fun setTitleMore(more: String?) {
        tvMore!!.visibility = VISIBLE
        tvMore!!.text = more
    }

    interface OnTitleBarClickListener {
        fun back()
        fun more()
    }

}