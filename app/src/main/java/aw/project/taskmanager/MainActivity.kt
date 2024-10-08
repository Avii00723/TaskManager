package aw.project.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.*
import aw.project.taskmanager.repository.TaskRepository
import aw.project.taskmanager.data.TaskDatabase
import aw.project.taskmanager.ui.List.TaskInputScreen
import aw.project.taskmanager.ui.List.TaskListScreen

class MainActivity : ComponentActivity() {
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var themeViewModel: ThemeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val taskDatabase = TaskDatabase.getDatabase(applicationContext)
        val taskDao = taskDatabase.taskDao()
        val repository = TaskRepository(taskDao) // Corrected import reference
        taskViewModel = ViewModelProvider(this, TaskViewModelFactory(repository)).get(TaskViewModel::class.java)
        themeViewModel = ViewModelProvider(this).get(ThemeViewModel::class.java)

        setContent {
            val isDarkTheme by themeViewModel.isDarkTheme.collectAsState()
            val navController = rememberNavController()

            MaterialTheme(colorScheme = if (isDarkTheme) darkColorScheme() else lightColorScheme()) {
                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { navController.navigate("input") },
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text("+")
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        NavHost(navController = navController, startDestination = "list") {
                            composable("list") {
                                TaskListScreen(
                                    tasks = taskViewModel.tasks.collectAsState(initial = emptyList()).value,
                                    onTaskClick = { /* Handle task click if needed */ },
                                    onDeleteTask = { taskViewModel.deleteTask(it) },
                                    onTaskCheckChange = { task, isChecked ->
                                        val updatedTask = task.copy(isCompleted = isChecked)
                                        taskViewModel.updateTask(updatedTask)
                                    }
                                )
                            }

                            composable("input") {
                                TaskInputScreen(
                                    onSaveTask = { newTask ->
                                        taskViewModel.insertTask(newTask)
                                        navController.popBackStack()
                                    },
                                    onCancel = { navController.popBackStack() }
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            contentAlignment = Alignment.BottomStart
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Dark Theme")
                                Spacer(modifier = Modifier.width(8.dp))
                                Switch(
                                    checked = isDarkTheme,
                                    onCheckedChange = { themeViewModel.toggleTheme() }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
