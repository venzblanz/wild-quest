package com.android.finalproject.data.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val userId          : Int = 0,

    val username           : String,
    val email           : String,
    val password        : String,


    val fullName        : String? = null,
    val profileImage    : String? = null,
    val birthday        : String? = null,
    val gender          : String? = null
)
