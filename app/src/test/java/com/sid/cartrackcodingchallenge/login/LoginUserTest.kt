package com.sid.cartrackcodingchallenge.login

import com.sid.cartrackcodingchallenge.data.LoginUser
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LoginUserTest {

    private lateinit var loginUser: LoginUser

    @Test
    fun isUsernameValid_UsernameProvided_ReturnsTrue() {
        loginUser = LoginUser("user123", "user123", "Singapore")
        assertTrue(loginUser.isUsernameValid)
    }

    @Test
    fun isUsernameValid_BlankUsername_ReturnsFalse() {
        loginUser = LoginUser("      ", "user123", "Singapore")
        assertFalse(loginUser.isUsernameValid)
    }

    @Test
    fun isPasswordValid_PasswordProvided_ReturnsTrue() {
        loginUser = LoginUser("user123", "user123", "Singapore")
        assertTrue(loginUser.isPasswordValid)
    }

    @Test
    fun isPasswordValid_BlankPassword_ReturnsFalse() {
        loginUser = LoginUser("user123", "      ", "Singapore")
        assertFalse(loginUser.isPasswordValid)
    }

}