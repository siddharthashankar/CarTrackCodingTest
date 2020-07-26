package com.sid.cartrackcodingchallenge

import android.app.Application
import com.sid.cartrackcodingchallenge.di.component.ApplicationComponent
import com.sid.cartrackcodingchallenge.di.component.DaggerApplicationComponent
import com.sid.cartrackcodingchallenge.di.module.ApplicationContextModule
import com.sid.cartrackcodingchallenge.di.module.RemoteConnectionModule
import com.sid.cartrackcodingchallenge.di.module.RoomModule

class MyApplication : Application() {

    var applicationComponent: ApplicationComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationContextModule(ApplicationContextModule(this))
            .roomModule(RoomModule(this))
            .remoteConnectionModule(RemoteConnectionModule())
            .build()

    }
}

