package com.hintsete.mobile_task_manager.domain.usecases

import com.hintsete.mobile_task_manager.data.Task
import com.hintsete.mobile_task_manager.data.TaskRepository

class AddTaskUseCase(private val repository: TaskRepository){
    operator fun invoke(title:String): Task = repository.addTasks(title)
}