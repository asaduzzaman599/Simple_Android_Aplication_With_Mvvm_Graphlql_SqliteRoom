package com.example.android_graphql_mvvm_sqliteroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ActionMenuView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.android_graphql_mvvm_sqliteroom.Ui.Fragment.PostListFragment
import com.example.android_graphql_mvvm_sqliteroom.Ui.Fragment.PostSearchFragment
import com.example.android_graphql_mvvm_sqliteroom.ViewModel.PostViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val postListFragment = PostListFragment()
        val postSearhFragment = PostSearchFragment()

        makeCurrentFragment(postListFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_post -> makeCurrentFragment(postListFragment)
                R.id.nav_search -> makeCurrentFragment(postSearhFragment)
            }
            true
        }



    }

    override fun onDestroy() {
        super.onDestroy()

    }

   fun makeCurrentFragment(fragment: Fragment)=
       supportFragmentManager.beginTransaction().apply{
        replace(R.id.fragment_view,fragment)
           commit()
    }

}
