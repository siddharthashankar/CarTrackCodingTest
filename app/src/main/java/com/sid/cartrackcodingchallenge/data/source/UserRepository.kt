package com.sid.cartrackcodingchallenge.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sid.cartrackcodingchallenge.data.source.remote.UserRemoteDataSource
import com.sid.cartrackcodingchallenge.data.source.remote.action.OnUsersResponse
import com.sid.cartrackcodingchallenge.data.source.remote.response.UserResponse
import javax.inject.Inject

class UserRepository @Inject constructor(var userRemoteDataSource: UserRemoteDataSource) {

    companion object {
        private val TAG = "UserRepository"
    }

    private val mutableUserListWrapper = MutableLiveData<UserListWrapper>()

    fun getUsers(): LiveData<UserListWrapper> {
        userRemoteDataSource.getUsers(object : OnUsersResponse {
            override fun onErrorResponse(error: String) {
                mutableUserListWrapper.value = UserListWrapper(error)
            }

            override fun onSuccessUsersResponse(userResponseList: List<UserResponse>) {
                mutableUserListWrapper.value = UserListWrapper(userResponseList)
            }
        })
        return mutableUserListWrapper
    }
}
