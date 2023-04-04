package com.example.taskapp.ui.onboard

import java.io.FileDescriptor

data class BoardModel(
    var img: Int,
    var title: String,
    var descriptor: String,
    var isLast: Boolean

):java.io.Serializable
    {
    var description: CharSequence? = null
}
