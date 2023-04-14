package com.example.taskapp.ui.onboard

data class BoardModel(
    var img: Int,
    var title: String,
    var descriptor: String,
    var isLast: Boolean

):java.io.Serializable{
    var description: CharSequence? = null
}
