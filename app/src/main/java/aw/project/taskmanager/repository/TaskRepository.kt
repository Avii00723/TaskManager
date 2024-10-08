package aw.project.taskmanager.repository

import aw.project.taskmanager.data.Task
import aw.project.taskmanager.data.TaskDao
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {
    val allTasks: Flow<List<Task>> = taskDao.getAllTasks()

    suspend fun insert(task: Task) {
        taskDao.insertTask(task)
    }

    suspend fun delete(task: Task) {
        taskDao.deleteTask(task)
    }

    // Add the update function
    suspend fun update(task: Task) {
        taskDao.updateTask(task)
    }
}

