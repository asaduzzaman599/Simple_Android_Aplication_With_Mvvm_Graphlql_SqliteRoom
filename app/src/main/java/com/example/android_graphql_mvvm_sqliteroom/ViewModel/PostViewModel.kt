package com.example.android_graphql_mvvm_sqliteroom.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_graphql_mvvm_sqliteroom.Repo.PostRepository
import com.example.android_graphql_mvvm_sqliteroom.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
  private val  repo: PostRepository
): ViewModel() {
    public val _postUiState = MutableStateFlow<PostUiState>(PostUiState.Empty)
    val postUiState: StateFlow<PostUiState> = _postUiState

    public val _postlistState = MutableStateFlow<PostUiState>(PostUiState.Empty)
    val postlistState: StateFlow<PostUiState> = _postlistState


    fun searchPost(id:String) = viewModelScope.launch {

        _postUiState.value = PostUiState.Loading
        repo.getRepoPost(id).collect { data ->
            _postUiState.value=PostUiState.Success(data)
        }

    }


    fun getAllPost() = viewModelScope.launch {
        _postlistState.value = PostUiState.Loading
        repo.getAllRepoPost().collect { data ->
            _postlistState.value=PostUiState.ListSuccess(data)

            Log.d("LaunchList", "Success viewmodel")
        }

    }

    sealed class PostUiState {
        class Success(val data: Post) : PostUiState()
        class ListSuccess(val data: List<Post>) : PostUiState()
        data class Error(val message: String) : PostUiState()
        object Loading : PostUiState()
        object Empty : PostUiState()
    }
}