package com.hintsete.mobile_task_manager.presentation.viewmodels

import com.hintsete.mobile_task_manager.data.Task



import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class TaskViewModel : ViewModel() {
    private val _tasks = mutableStateListOf<Task>()
    val tasks = mutableStateOf(_tasks.toList())

    private val _showDialog = mutableStateOf(false)
    val showDialog = _showDialog

    fun showAddTaskDialog() {
        _showDialog.value = true
    }

    fun dismissAddTaskDialog() {
        _showDialog.value = false
    }

    fun onAddTask(title: String) {
        if (title.isNotBlank()) {
            _tasks.add(Task(_tasks.size + 1, title))
            tasks.value = _tasks.toList()
        }
    }

    fun onTaskChecked(task: Task, isChecked: Boolean) {
        val index = _tasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            _tasks[index] = task.copy(isCompleted = isChecked)
            tasks.value = _tasks.toList()
        }
    }

    fun onTaskDeleted(task: Task) {
        _tasks.remove(task)
        tasks.value = _tasks.toList()
    }
}