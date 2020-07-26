package com.sid.cartrackcodingchallenge.di.module

import android.app.Application
import androidx.room.Room
import com.sid.cartrackcodingchallenge.data.source.LoginUserManager
import com.sid.cartrackcodingchallenge.data.source.local.AppRoomDatabase
import com.sid.cartrackcodingchallenge.data.source.local.LoginUserDao
import com.sid.cartrackcodingchallenge.data.source.local.LoginUserLocalDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(application: Application) {

    private val appRoomDatabase: AppRoomDatabase =
        Room.databaseBuilder<AppRoomDatabase>(application, AppRoomDatabase::class.java, "login_user_db").build()

    @Provides
    @Singleton
    fun provideLoginUserManager(loginUserLocalDataSource: LoginUserLocalDataSource): LoginUserManager {
        return LoginUserManager(loginUserLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideLoginUserLocalDatasource(loginUserDao: LoginUserDao): LoginUserLocalDataSource {
        return LoginUserLocalDataSource(loginUserDao)
    }

    @Provides
    @Singleton
    fun provideLoginUserDao(appRoomDatabase: AppRoomDatabase): LoginUserDao {
        return appRoomDatabase.loginUserDao()
    }

    @Provides
    @Singleton
    fun provideAppRoomDatabase(): AppRoomDatabase {
        return appRoomDatabase
    }

}
