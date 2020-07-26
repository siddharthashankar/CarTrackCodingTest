package com.sid.cartrackcodingchallenge.data.source

import com.sid.cartrackcodingchallenge.data.source.remote.response.UserResponse

class UserListWrapper {

    var userResponseList: List<UserResponse>? = null
    var error: String? = null

    constructor(error: String) {
        this.error = error
    }

    constructor(userResponseList: List<UserResponse>) {
        this.userResponseList = userResponseList
    }
}