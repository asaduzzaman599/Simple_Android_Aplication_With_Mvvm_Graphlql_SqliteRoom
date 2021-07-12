package com.example.android_graphql_mvvm_sqliteroom.LocalDataSource

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_graphql_mvvm_sqliteroom.R
import com.example.android_graphql_mvvm_sqliteroom.model.Post

class RecyclerViewAdapter(val listData:List<Post>) : RecyclerView.Adapter<RecyclerViewAdapter.MyviewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RecyclerViewAdapter.MyviewHolder{
       val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent,false )

        return MyviewHolder(view)
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        holder.id_txt.text=listData.get(position).id
        holder.title_txt.text=listData.get(position).title
        holder.body_txt.text=listData.get(position).body
    }

    override fun getItemCount(): Int {
       return listData.size
    }

    class MyviewHolder(view:View):RecyclerView.ViewHolder(view){
        var id_txt: TextView
        var title_txt: TextView
        var body_txt: TextView
        init {
            id_txt=view.findViewById(R.id.post_id)
            title_txt=view.findViewById(R.id.posttitle)
             body_txt=view.findViewById(R.id.postDetails)
        }
    }
}