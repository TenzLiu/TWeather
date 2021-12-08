package com.tenz.login.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User{

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var account: String = ""
    var password: String = ""

}