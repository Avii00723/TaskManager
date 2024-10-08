package aw.project.taskmanager.ui.List

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import aw.project.taskmanager.data.Task
import androidx.compose.ui.Alignment

@Composable
fun TaskListScreen(
    tasks: List<Task>,
    onTaskClick: (Task) -> Unit,
    onDeleteTask: (Task) -> Unit,
    onTaskCheckChange: (Task, Boolean) -> Unit
) {
    LazyColumn {
        items(tasks) { task ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = task.title, style = MaterialTheme.typography.titleMedium)
                    Text(text = task.description, style = MaterialTheme.typography.bodyMedium)
                }

                Checkbox(
                    checked = task.isCompleted,
                    onCheckedChange = { isChecked -> onTaskCheckChange(task, isChecked) }
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(onClick = { onDeleteTask(task) }) {
                    Text("Delete")
                }
            }
        }
    }
}


@Composable
fun TaskItem(task: Task, onClick: () -> Unit, onDelete: () -> Unit) {
    Card(modifier = Modifier.padding(8.dp)
        .fillMaxSize(), onClick = onClick) {
        Row (
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
        ){

            Column(modifier = Modifier.padding(16.dp)
            ) {
                Text(text = task.title)
                Text(text = task.description)
                
            }
            Text(text = task.dueDate)
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.CenterEnd

            ) {
                Button(onClick = { onDelete() }) {
                    Text("Delete")
                }
            }

        }
    }
}
