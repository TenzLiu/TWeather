package com.tenz.login.view

import com.alibaba.android.arouter.facade.annotation.Route
import com.tenz.common.app.Constants
import com.tenz.common.base.BaseActivity
import com.tenz.common.utils.AppUtil
import com.tenz.common.utils.ToastUtil
import com.tenz.login.databinding.ActivityRegisterBinding
import com.tenz.login.db.AppDatabase
import com.tenz.login.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * 注册
 */
@Route(path = Constants.PATH_REGISTER)
class RegisterActivity: BaseActivity<ActivityRegisterBinding>() {

    override fun init() {
        ui!!.btnRegister.setOnClickListener {
            val account = ui!!.etAccount.text.toString()
            val password = ui!!.etAccount.text.toString()
            if(account.isEmpty() || password.isEmpty()) {
                ToastUtil.show("输入不能为空")
            } else {
                val user = User()
                user.account = account
                user.password = password
                GlobalScope.launch(Dispatchers.IO) {
                    AppDatabase.get(AppUtil.getContext()).userDao().insertUser(user)
                    GlobalScope.launch(Dispatchers.Main) {
                        ToastUtil.show("注册成功")
                        finish()
                    }
                }
            }
        }
    }

}