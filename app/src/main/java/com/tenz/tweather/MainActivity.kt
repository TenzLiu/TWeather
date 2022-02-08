package com.tenz.tweather

import com.alibaba.android.arouter.facade.annotation.Route
import com.tenz.common.app.Constants
import com.tenz.common.base.BaseActivity
import com.tenz.tweather.databinding.ActivityMainBinding

@Route(path = Constants.PATH_MAIN)
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun init() {
    }

}