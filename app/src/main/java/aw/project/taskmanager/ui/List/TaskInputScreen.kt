package aw.project.taskmanager.ui.List

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import aw.project.taskmanager.data.Task
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskInputScreen(
    onSaveTask: (Task) -> Unit,
    onCancel: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") } // String to hold the selected date
    var priorityLevel by remember { mutableIntStateOf(1) }
    var isCompleted by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->

            calendar.set(year, month, dayOfMonth)
            dueDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(calendar.time)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Scaffold(
        topBar = { TopAppBar(title = { Text("Add Task") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = dueDate,
                onValueChange = {  },
                label = { Text("Due Date") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { datePickerDialog.show() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Priority Level: $priorityLevel")
            Slider(
                value = priorityLevel.toFloat(),
                onValueChange = { priorityLevel = it.toInt() },
                valueRange = 1f..5f,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Button(
                    onClick = {
                        val newTask = Task(
                            title = title,
                            description = description,
                            dueDate = dueDate, // Use selected due date
                            priorityLevel = priorityLevel,
                            isCompleted = isCompleted
                        )
                        onSaveTask(newTask)
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Save Task")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = onCancel,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Cancel")
                }
            }
        }
    }
}
