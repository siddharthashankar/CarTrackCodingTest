package com.sid.cartrackcodingchallenge.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.sid.cartrackcodingchallenge.BaseActivity
import com.sid.cartrackcodingchallenge.MyApplication
import com.sid.cartrackcodingchallenge.R
import com.sid.cartrackcodingchallenge.data.LoginUser
import com.sid.cartrackcodingchallenge.data.source.LoginUserManager
import com.sid.cartrackcodingchallenge.user_detail.UserListActivity
import com.sid.cartrackcodingchallenge.utils.CustomAnimations
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginActivity : BaseActivity() {

    @Inject
    lateinit var loginUserManager: LoginUserManager

    companion object {
        private const val TAG = "LoginActivity"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.sid.cartrackcodingchallenge.R.layout.activity_login)

        (application as MyApplication).applicationComponent?.inject(this)

        val loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        loginViewModel.init(loginUserManager)

        loginViewModel.insertNewLoginUser(LoginUser("sid123", "sid123", "Singapore"))

        btn_login.setOnClickListener {

            GlobalScope.launch(Dispatchers.Main) {

                var isInputValidationError = false

                var username = et_username.text.toString().trim()
                var password = et_password.text.toString().trim()
                var country = country_picker.selectedCountryEnglishName.toString().trim()

                var loginUser = LoginUser(username, password, country)

                if (!loginUser.isUsernameValid) {
                    tv_username_error.text = getString(R.string.username_error_username_cannot_be_empty)
                    isInputValidationError = true
                } else
                    tv_username_error.text = ""


                if (!loginUser.isPasswordValid) {
                    tv_password_error.text = getString(R.string.password_error_password_cannot_be_empty)
                    isInputValidationError = true
                } else
                    tv_password_error.text = ""


                if (!isInputValidationError) {
                    clearInputValidationErrors()
                    if (loginViewModel.authenticateUser(loginUser)) {

                        val intent = Intent(this@LoginActivity, UserListActivity::class.java)
                        startActivity(intent)

                    } else {
                        showSnackBar(cl_parent, "Login Failed, Incorrect user credentials!")
                        CustomAnimations.shake(applicationContext, ll_login_container)
                    }
                } else {
                    showSnackBar(cl_parent, "User Input Validation Failed!")
                    CustomAnimations.shake(applicationContext, ll_login_container)
                }
            }
        }

        et_username.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                hideKeyboard(this@LoginActivity, view)
            } else
                tv_username_error.text = ""
        }

        et_password.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                hideKeyboard(this@LoginActivity, view)
            } else
                tv_password_error.text = ""
        }

    }

    private fun clearInputValidationErrors() {
        tv_username_error.text = ""
        tv_password_error.text = ""
    }

}
