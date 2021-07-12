package com.example.android_graphql_mvvm_sqliteroom.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
data class PostList(
    @PrimaryKey val id: String,
    val title: String,
    val body : String
)
