package com.sid.cartrackcodingchallenge.data.source.local

import com.sid.cartrackcodingchallenge.data.LoginUser
import kotlinx.coroutines.*
import javax.inject.Inject

class LoginUserLocalDataSource @Inject constructor(var loginUserDao: LoginUserDao) {

    suspend fun getLoginUserByUsername(username: String): LoginUser? {
        return withContext(Dispatchers.Default) {
            loginUserDao.getLoginUserByUsername(username)
        }
    }

    fun insertNewLoginUser(loginUser: LoginUser): Job {
        return GlobalScope.launch {
            loginUserDao.insertLoginUser(loginUser)
        }
    }

}