package com.sid.cartrackcodingchallenge.data.source.remote.action

import com.sid.cartrackcodingchallenge.data.source.remote.response.UserResponse

interface OnUsersResponse : OnErrorResponse {
    fun onSuccessUsersResponse(userResponseList: List<UserResponse>)
}