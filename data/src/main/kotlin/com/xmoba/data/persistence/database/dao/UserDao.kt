package com.xmoba.data.persistence.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.xmoba.data.model.user.UserEntity

/**
 * Created by david on 8/8/18.
 */
@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    fun insert(user: UserEntity)

    @Insert(onConflict = REPLACE)
    fun insert(vararg users: UserEntity)

    @Query("SELECT * FROM user WHERE email = :email")
    fun getUserByEmail(email: String): UserEntity

    @Query("SELECT * FROM user ORDER BY `order` LIMIT :pageSize OFFSET :offset")
    fun getUsers(offset: Int, pageSize: Int): List<UserEntity>
}