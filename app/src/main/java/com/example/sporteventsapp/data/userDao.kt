package com.example.sporteventsapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUSer(user:User)

    @Query("SELECT *FROM user_data ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>
}