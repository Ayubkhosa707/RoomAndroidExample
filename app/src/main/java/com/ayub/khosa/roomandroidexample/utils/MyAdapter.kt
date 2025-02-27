package com.ayub.khosa.roomandroidexample.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ayub.khosa.roomandroidexample.R
import com.ayub.khosa.roomandroidexample.model.Users

class MyAdapter (context: Context, users: List<Users>): BaseAdapter() {
    private val context = context
    private var allusers = users
    fun setUsers(users: List<Users>) {
        allusers = users
    }
    override fun getCount(): Int {
       return allusers.size
    }

    override fun getItem(position: Int): Users {
        return allusers[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

         val item_user_view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
         val tv_name: TextView = item_user_view.findViewById(R.id.tv_name)
        val tv_email: TextView = item_user_view.findViewById(R.id.tv_email)
        val user = allusers[position]

        tv_name.text = "name :"+user.userName+" id : "+user.userId
        tv_email.text = "location :"+user.location+" email : "+user.email

        return item_user_view
    }
}