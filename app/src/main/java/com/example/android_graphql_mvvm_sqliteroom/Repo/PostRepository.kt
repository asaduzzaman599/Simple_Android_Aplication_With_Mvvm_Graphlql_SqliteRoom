package com.example.android_graphql_mvvm_sqliteroom.Repo

import android.util.Log
import android.widget.Toast
import com.example.android_graphql_mvvm_sqliteroom.AndroidGMSAplication
import com.example.android_graphql_mvvm_sqliteroom.LocalDataSource.PostDao
import com.example.android_graphql_mvvm_sqliteroom.RemoteDataSource.RemoteDataSource
import com.example.android_graphql_mvvm_sqliteroom.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PostRepository  @Inject constructor(
    public val remoteDataSource: RemoteDataSource,
    public val postDao: PostDao

){

    @Inject
    lateinit var app: AndroidGMSAplication

    fun getRepoPost(id:String): Flow<Post> = flow {

        try {

            val response = remoteDataSource.getPostRemoteData(id)

                if (response != null) {
                    refresbPost(response)
                }

        }catch (e: Exception){

            Log.d("Error", "Success hello $e ")

        }
        emit(postDao.load(id)?:Post("","",""))

    }.flowOn(Dispatchers.IO)


   fun getAllRepoPost() : Flow<List<Post>> = flow {


       try {

       val posts = remoteDataSource.getPostListRemoteData()


           if (posts != null) {
               posts.data?.posts?.data?.forEach {
                   val post=Post(it?.id .toString(),it?.title.toString() ,it?.body.toString())
                   refresbPost(post)

                   Log.d("LaunchListAll", "Success $post")


               }
           }

        Log.d("LaunchListAll", "Success")
       }catch (e: Exception){

           Log.d("LaunchList", "Success hello $e ")

       }
try {
    emit(postDao.loadAll())
}catch (e: Exception){

    Log.d("LaunchList", "Success hello $e ")

}

    }.flowOn(Dispatchers.IO)


    private suspend fun refresbPost(response:Post) {
        // Check if user data was fetched recently.

            // Refreshes the data.

        postDao.save(response)

            // Check for errors here.

            // Updates the database. Since `userDao.load()` returns an object of
            // `Flow<User>`, a new `User` object is emitted every time there's a
            // change in the `User`  table.

    }

    companion object {
        val FRESH_TIMEOUT = TimeUnit.DAYS.toMillis(1)
    }
}