package com.sid.cartrackcodingchallenge.di.component

import android.content.Context
import com.sid.cartrackcodingchallenge.di.module.ApplicationContextModule
import com.sid.cartrackcodingchallenge.di.module.RemoteConnectionModule
import com.sid.cartrackcodingchallenge.di.module.RoomModule
import com.sid.cartrackcodingchallenge.login.LoginActivity
import com.sid.cartrackcodingchallenge.user_detail.UserListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationContextModule::class, RoomModule::class, RemoteConnectionModule::class])
interface ApplicationComponent {

    fun inject(loginActivity: LoginActivity)
    fun inject(userListActivity: UserListActivity)

    fun context(): Context

}

