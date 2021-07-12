package com.example.android_graphql_mvvm_sqliteroom.Ui.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.android_graphql_mvvm_sqliteroom.R
import com.example.android_graphql_mvvm_sqliteroom.ViewModel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.android.synthetic.main.fragment_post_search.*
import kotlinx.coroutines.flow.collect

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PostSearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class PostSearchFragment : Fragment() {
    // TODO: Rename and change types of parameters

    val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_post_search, container, false)

        val bttn = view.findViewById<Button>(R.id.btn)
        var item_id = view.findViewById<TextView>(R.id.search_item_id)
        var item_Title = view.findViewById<TextView>(R.id.search_item_title)
        var item_Details = view.findViewById<TextView>(R.id.search_item_body)
        var search_Item = view.findViewById<EditText>(R.id.txt)
        bttn.setOnClickListener{
            val data = search_Item.text.toString()
            Log.d("Edittext" , "$data")
            if(data!=null){
                viewModel.searchPost(data)

                lifecycleScope.launchWhenStarted {
                    viewModel.postUiState.collect {
                        when (it) {
                            is PostViewModel.PostUiState.Success -> {

                                item_id.setText(it.data.id)
                                item_Title.setText(it.data.title)
                                item_Details.setText(it.data.body)
                                Log.d("LaunchListall", "Success hello ${it.data} ")
                            }
                            is PostViewModel.PostUiState.Error -> {

                                Log.d("LaunchListall", "Success hello Error")

                            }
                            is PostViewModel.PostUiState.Loading -> {

                                Log.d("LaunchListall", "Success hello Loading")
                            }
                            else -> Unit
                        }
                    }
                }
            }else{
                search_Item .error = "Required"
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
         * @return A new instance of fragment PostSearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PostSearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}