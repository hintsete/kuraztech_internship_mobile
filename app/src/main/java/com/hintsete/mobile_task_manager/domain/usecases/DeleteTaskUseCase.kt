package com.hintsete.mobile_task_manager.domain.usecases

import com.hintsete.mobile_task_manager.data.Task
import com.hintsete.mobile_task_manager.data.TaskRepository

class DeleteTaskUseCase(private val repository: TaskRepository){
    operator fun invoke(taskId:Int) = repository.deleteTask(taskId)
}