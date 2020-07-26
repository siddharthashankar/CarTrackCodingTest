package com.sid.cartrackcodingchallenge.login

import androidx.lifecycle.ViewModel
import com.sid.cartrackcodingchallenge.data.LoginUser
import com.sid.cartrackcodingchallenge.data.source.LoginUserManager

class LoginViewModel : ViewModel() {

    lateinit var loginUserManager: LoginUserManager

    fun init(loginUserManager: LoginUserManager) {
        this.loginUserManager = loginUserManager
    }

    suspend fun authenticateUser(loginUser: LoginUser): Boolean {
        return loginUserManager.isUserAuthenticated(loginUser)
    }

    fun insertNewLoginUser(loginUser: LoginUser) {
        loginUserManager.insertNewLoginUser(loginUser)
    }

}
