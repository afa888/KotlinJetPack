package com.example.kotlinjetpack.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinjetpack.bean.ObUser
import com.example.kotlinjetpack.databinding.ItemLvBinding


class RAdapter(context: Fragment, users: MutableList<ObUser>) : RecyclerView.Adapter<RAdapter.MyHolder>() {

    private var users: MutableList<ObUser> = arrayListOf()
    val url = "https://cdn.cnn.com/cnnnext/dam/assets/191009234452-01-donald-trump-october-9-2019-medium-tease.jpg"
    val aContext = context

    init {
        this.users = users;
    }

    fun setData(users: MutableList<ObUser>){
        this.users = users
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLvBinding.inflate(inflater)
        return MyHolder(binding)
    }

    //kotlin中，return的方式，可以简写
    override fun getItemCount() = users.size


    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        //java 写法可以setVariable
        holder.binding.user = users[position]
        Glide.with(aContext).load(users[position].name).into(holder.binding.iv)
        holder.binding.executePendingBindings()
    }

    //在构造函数中声明binding变量，这样上面的holder才能引用到，如果不加val/var，就引用不到，就需要在class的{}内写get函数
    class MyHolder(val binding: ItemLvBinding) : RecyclerView.ViewHolder(binding.root)
}