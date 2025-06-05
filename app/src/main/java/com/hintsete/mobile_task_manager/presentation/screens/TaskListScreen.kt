package com.hintsete.mobile_task_manager.presentation.screens



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hintsete.mobile_task_manager.presentation.components.TaskItem
import com.hintsete.mobile_task_manager.presentation.viewmodels.TaskViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    viewModel: TaskViewModel = viewModel()
) {
    val tasks by viewModel.tasks
    val showDialog by viewModel.showDialog
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("My Tasks") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.showAddTaskDialog() }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add task")
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (tasks.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No tasks yet. Tap '+' to add one!",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(tasks, key = { it.id }) { task ->
                        TaskItem(
                            task = task,
                            onCheckedChange = { isChecked ->
                                viewModel.onTaskChecked(task, isChecked)
                            },
                            onDelete = {
                                viewModel.onTaskDeleted(task)
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Task deleted")
                                }
                            }
                        )
                    }
                }
            }

            if (showDialog) {
                AddTaskDialog(
                    onDismiss = { viewModel.dismissAddTaskDialog() },
                    onAddTask = { title ->
                        if (title.isNotBlank()) {
                            viewModel.onAddTask(title)
                            viewModel.dismissAddTaskDialog()
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Task added")
                            }
                        } else {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Task title can't be empty")
                            }
                        }
                    }
                )
            }
        }
    }
}
