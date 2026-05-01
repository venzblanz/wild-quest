package com.android.finalproject.data.users

import java.io.Serializable

data class Users(
     var username: String = "",
     @Transient var password: String = "",
     var email: String = ""
) : Serializable