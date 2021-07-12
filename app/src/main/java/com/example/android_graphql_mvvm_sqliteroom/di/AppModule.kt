package com.example.android_graphql_mvvm_sqliteroom.di

import android.content.Context
import androidx.room.Room
import com.example.android_graphql_mvvm_sqliteroom.LocalDataSource.PostDao
import com.example.android_graphql_mvvm_sqliteroom.LocalDataSource.PostDatabase
import com.example.android_graphql_mvvm_sqliteroom.RemoteDataSource.RemoteDataSource
import com.example.android_graphql_mvvm_sqliteroom.Repo.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context:Context):
            PostDatabase = Room.databaseBuilder(context,PostDatabase::class.java,"postDatabase").build()

    @Provides
    @Singleton
    fun provideDatadao(postDatabase: PostDatabase):PostDao=
            postDatabase.postDao()

    @Provides
    @Singleton
    fun providePostRepository(remoteDataSource: RemoteDataSource,  postDao: PostDao):PostRepository=
        PostRepository(remoteDataSource,postDao)
}