package com.hintsete.mobile_task_manager.data

data class Task(
    val id:Int,
    val title:String,
    val isCompleted:Boolean=false
)