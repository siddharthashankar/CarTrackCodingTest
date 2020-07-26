package com.sid.cartrackcodingchallenge.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sid.cartrackcodingchallenge.data.LoginUser

@Database(entities = [LoginUser::class], version = 1, exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun loginUserDao(): LoginUserDao

}