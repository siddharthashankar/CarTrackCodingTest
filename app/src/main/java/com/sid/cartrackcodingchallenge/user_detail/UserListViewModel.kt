package com.sid.cartrackcodingchallenge.user_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sid.cartrackcodingchallenge.data.source.UserListWrapper
import com.sid.cartrackcodingchallenge.data.source.UserRepository


class UserListViewModel : ViewModel() {

    lateinit var userRepository: UserRepository

    fun init(userRepository: UserRepository) {
        this.userRepository = userRepository
    }

    fun getUsers(): LiveData<UserListWrapper> {
        return userRepository.getUsers()
    }

}