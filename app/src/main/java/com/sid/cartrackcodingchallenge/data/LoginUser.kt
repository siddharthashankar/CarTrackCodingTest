package com.sid.cartrackcodingchallenge.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class LoginUser(@field:PrimaryKey val username: String, val password: String, val country: String) {

    val isUsernameValid: Boolean
        get() = !username.isBlank()

    val isPasswordValid: Boolean
        get() = !password.isBlank()

}