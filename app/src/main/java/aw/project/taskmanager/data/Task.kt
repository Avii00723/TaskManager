package aw.project.taskmanager.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String,
    val dueDate: String = "",
    val isCompleted: Boolean = false,
    val priorityLevel: Int = 1
)
