package com.example.android_graphql_mvvm_sqliteroom.LocalDataSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.android_graphql_mvvm_sqliteroom.model.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Insert(onConflict = REPLACE)
    fun save(post: Post)

    @Query("SELECT * FROM post WHERE id = :postId")
    fun load(postId: String): Post


    @Query("SELECT * FROM post ")
    fun loadAll(): List<Post>
}