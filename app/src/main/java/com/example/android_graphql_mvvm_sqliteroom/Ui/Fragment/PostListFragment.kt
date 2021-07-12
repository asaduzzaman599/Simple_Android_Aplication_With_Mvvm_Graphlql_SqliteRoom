package com.example.android_graphql_mvvm_sqliteroom.Ui.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_graphql_mvvm_sqliteroom.LocalDataSource.RecyclerViewAdapter
import com.example.android_graphql_mvvm_sqliteroom.R
import com.example.android_graphql_mvvm_sqliteroom.ViewModel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PostListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class PostListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val viewModel: PostViewModel by viewModels()
    lateinit var adapter:RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_post_list, container, false)
        val recycview = view.findViewById<RecyclerView>(R.id.recycler_postlist)
        recycview.layoutManager = LinearLayoutManager(activity)
        viewModel.getAllPost()
        lifecycleScope.launchWhenStarted {
            viewModel.postlistState.collect {

                when (it) {
                    is PostViewModel.PostUiState.ListSuccess -> {
                        adapter = RecyclerViewAdapter(it.data)
                        recycview.adapter =adapter
                        Log.d("LaunchListAllMain", "Success hello ${it.data[5].id}")

                    }
                    is PostViewModel.PostUiState.Error -> {


                    }
                    is PostViewModel.PostUiState.Loading -> {

                    }
                    else -> Unit
                }
            }
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PostListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PostListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}