package com.tenz.login.db.dao

import androidx.room.*
import com.tenz.login.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserList(userList: MutableList<User>)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("select * from User")
    suspend fun queryUserAll(): MutableList<User>

    @Query("select * from User where account = :account and password = :password")
    suspend fun queryUser(account: String, password: String): User?

}