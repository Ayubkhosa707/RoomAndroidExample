package com.ayub.khosa.roomandroidexample

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ayub.khosa.roomandroidexample.model.Users
import com.ayub.khosa.roomandroidexample.repository.UserRepository
import com.ayub.khosa.roomandroidexample.utils.MyAdapter
import com.ayub.khosa.roomandroidexample.utils.PrintLogs

class MainActivity : AppCompatActivity() {

    val repo: UserRepository by lazy {
        UserRepository(this)
    }

    lateinit var allUsers: List<Users>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val et_save = findViewById<EditText>(R.id.et_save)
        val tv_save = findViewById<TextView>(R.id.tv_save)
        val tv_load = findViewById<TextView>(R.id.tv_load)
        val listview = findViewById<ListView>(R.id.listview)
        tv_save.setOnClickListener(View.OnClickListener {
            if (et_save.text.isNotEmpty()) {
                val user = Users(
                    userName = et_save.text.toString(),
                    location = et_save.text.toString(),
                    email = et_save.text.toString()
                )
                repo.insertUser(user)
                Toast.makeText(this, " data saved", Toast.LENGTH_SHORT).show()
                tv_load.callOnClick()
            } else {
                Toast.makeText(this, "Empty data", Toast.LENGTH_SHORT).show()
            }
        })
        tv_load.setOnClickListener(View.OnClickListener {
            allUsers = repo.getAllUsers()
            if (allUsers.isEmpty()) {
                Toast.makeText(this, "Users is Empty ", Toast.LENGTH_SHORT).show()
                PrintLogs.printD("Users is Empty ")
                listview.visibility = View.GONE
            } else {
                listview.visibility = View.VISIBLE
                val myAdapter: MyAdapter  = MyAdapter(this.applicationContext, allUsers)
                listview.adapter = myAdapter
                allUsers.forEach {

                    PrintLogs.printD("email : " + it.email)
                    PrintLogs.printD("name : " + it.userName)
                    PrintLogs.printD("location : " + it.location)
                    PrintLogs.printD("id in db :" + it.userId.toString())
                }
            }
        })


        listview.setOnItemClickListener { parent, view, position, id ->
            val myAdapter: MyAdapter  = MyAdapter(this.applicationContext, allUsers)
            val element = myAdapter.getItem(position)
            repo.deleteUser(element)
            Toast.makeText(this, "User is Deleted ", Toast.LENGTH_SHORT).show()

            allUsers = repo.getAllUsers()
            if (allUsers.isEmpty()) {
              //  Toast.makeText(this, "List is Empty Now ", Toast.LENGTH_SHORT).show()
                PrintLogs.printD("Users is Empty ")
                listview.visibility = View.GONE
            } else {
                listview.visibility = View.VISIBLE
                    myAdapter.setUsers(allUsers)
                 listview.adapter = myAdapter
            }
         }


    }
}