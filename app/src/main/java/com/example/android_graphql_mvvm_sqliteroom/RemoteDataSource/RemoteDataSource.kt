package com.example.android_graphql_mvvm_sqliteroom.RemoteDataSource

import android.provider.Settings.Global.getString
import android.util.Log
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.example.android_graphql_mvvm_sqliteroom.GetPostByIdQuery
import com.example.android_graphql_mvvm_sqliteroom.LoadAllPostsQuery
import com.example.android_graphql_mvvm_sqliteroom.Network.ApolloClientManager
import com.example.android_graphql_mvvm_sqliteroom.R
import com.example.android_graphql_mvvm_sqliteroom.model.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class   RemoteDataSource @Inject constructor() {
    suspend fun getPostRemoteData(id:String): Post? {

        try {
            val response = ApolloClientManager.apolloClient.query(GetPostByIdQuery(id = id)).await()


            val id =response.data?.post?.id.toString()
            val title = response.data?.post?.title.toString()
            val body = response.data?.post?.body.toString()
            var post =Post(id,title,body)

            return post?:null
        } catch (e: Exception) {
            Log.d("Error:"  ,"$e")
        }



        return null


        }

    suspend fun getPostListRemoteData(): Response<LoadAllPostsQuery.Data> {



            var posts : List<Post>
            var response: Response<LoadAllPostsQuery.Data>
            response = ApolloClientManager.apolloClient.query(LoadAllPostsQuery()).await()



            return response


/*
        val id =response.data?.post?.id.toString()
        val title = response.data?.post?.title.toString()
        val body = response.data?.post?.body.toString()*/




        //return post
    }
}