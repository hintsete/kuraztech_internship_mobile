package com.hintsete.mobile_task_manager.presentation.screens



import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hintsete.mobile_task_manager.presentation.components.TaskItem
import com.hintsete.mobile_task_manager.presentation.viewmodels.TaskViewModel

@Composable
fun TaskListScreen(
    viewModel: TaskViewModel = viewModel()
) {
    val tasks = viewModel.tasks.value // Access the State value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.showAddTaskDialog() }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add task")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(tasks) { task ->
                TaskItem(
                    task = task,
                    onCheckedChange = { isChecked ->
                        viewModel.onTaskChecked(task, isChecked)
                    },
                    onDelete = {
                        viewModel.onTaskDeleted(task)
                    }
                )
            }
        }
    }

    if (viewModel.showDialog.value) {
        AddTaskDialog(
            onDismiss = { viewModel.dismissAddTaskDialog() },
            onAddTask = { title ->
                viewModel.onAddTask(title)
                viewModel.dismissAddTaskDialog()
            }
        )
    }
}