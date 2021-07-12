package com.example.android_graphql_mvvm_sqliteroom.LocalDataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android_graphql_mvvm_sqliteroom.model.Post

@Database(entities = [Post::class], version = 1)
abstract class PostDatabase: RoomDatabase(){
    abstract fun postDao(): PostDao
}