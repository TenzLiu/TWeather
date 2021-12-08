package com.tenz.login.view

import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.tenz.common.app.Constants
import com.tenz.common.base.BaseActivity
import com.tenz.common.utils.AppUtil
import com.tenz.common.utils.SPUtil
import com.tenz.common.utils.ToastUtil
import com.tenz.login.databinding.ActivityLoginBinding
import com.tenz.login.db.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * 登录
 */
@Route(path = Constants.PATH_LOGIN)
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override fun init() {
        ui!!.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        ui!!.btnLogin.setOnClickListener {
            val account = ui!!.etAccount.text.toString()
            val password = ui!!.etPassword.text.toString()
            if(account.isEmpty() || password.isEmpty()) {
                ToastUtil.show("输入不能为空")
            } else {
                GlobalScope.launch(Dispatchers.IO) {
                    val user =
                        AppDatabase.get(AppUtil.getContext()).userDao().queryUser(account, password)
                    if(null != user){
                        GlobalScope.launch(Dispatchers.Main) {
                            SPUtil.put(Constants.SP_KEY_IS_LOGIN, true)
                            ARouter.getInstance().build(Constants.PATH_MAIN).navigation()
                            finish()
                        }
                    } else {
                        GlobalScope.launch(Dispatchers.Main) {
                            ToastUtil.show("用户不存在")
                        }
                    }
                }
            }
        }
    }

}