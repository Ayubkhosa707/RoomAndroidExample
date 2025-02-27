package com.ayub.khosa.roomandroidexample.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ayub.khosa.roomandroidexample.model.Users

@Dao
interface UserDao {

    @Insert
    fun insertUser(users: Users)

    @Query("Select * from user")
    fun gelAllUsers(): List<Users>

    @Update
    fun updateUser(users: Users)

    @Delete
    fun deleteUser(users: Users)

}