Task Manager Application
Project Overview
The Task Manager Application is a productivity app designed to help users manage their tasks effectively. Built using Kotlin with Jetpack Compose for UI, this app provides an intuitive interface to create, update, and manage tasks. The app also includes a dark/light theme toggle, prioritization options, and the ability to track task completion status.
The core features of the app include:
•	Task creation and deletion.
•	Date picker for due date selection.
•	Priority levels with a slider.
•	A toggle switch for dark/light theme.
•	Persistent storage using an SQLite database through the Room library.
•	State management using the MVVM (Model-View-ViewModel) pattern.
Setup Instructions
Follow these steps to set up and run the Task Manager Application locally:
Prerequisites
•	Android Studio (latest version recommended)
•	JDK 11 or above
•	An Android device or emulator running API level 21 (Lollipop) or higher
Clone the Repository
bash
Copy code
git clone https://github.com/yourusername/task-manager-app.git
cd task-manager-app
Open the Project in Android Studio
1.	Open Android Studio.
2.	Click on File > Open....
3.	Select the root folder of the cloned project and click OK.
Build and Run the Project
1.	Wait for Gradle to sync and build the project. If you encounter any dependency issues, try rebuilding the project or clearing the cache as described in the troubleshooting section.
2.	Connect an Android device or start an emulator.
3.	Click on the Run button or press Shift + F10.
Troubleshooting
•	If the project does not build successfully, ensure that all necessary dependencies are installed and the build.gradle files are up-to-date.
•	In case of issues with the database or ViewModel classes, try invalidating the cache and restarting Android Studio: File > Invalidate Caches / Restart.
Key Design Decisions and Features
1. MVVM Architecture
The app is structured using the MVVM pattern to separate the UI components (Views) from the business logic (ViewModels) and the data handling (Repositories). This separation ensures a clean codebase and allows for better scalability and maintainability.
•	Model: Handles data representation using Task data class and Room TaskDao.
•	ViewModel: Manages UI-related data and business logic. TaskViewModel is used to manage tasks, while ThemeViewModel manages theme state.
•	View: Composed of TaskListScreen and TaskInputScreen, which handle the display and input of tasks.
2. Dark/Light Theme Toggle
The app provides a toggle switch located at the bottom-left corner to switch between dark and light themes. This feature is managed using ThemeViewModel and persists across sessions using shared preferences.
3. Task Management
Tasks can be added, updated, and deleted using the UI. Key features include:
•	A date picker for setting task deadlines.
•	A slider to prioritize tasks on a scale of 1 to 5.
•	A checkbox to mark tasks as completed.
•	Task list is displayed with a checkbox to toggle completion status and a delete button to remove tasks.
4. Persistent Storage
The app uses the Room persistence library to handle local storage. All task data is stored in an SQLite database and retrieved asynchronously using Kotlin coroutines.
5. Date Picker Integration
The app uses a date picker dialog for selecting the due date, which is then converted to a readable string format and displayed in the text field.
6. Dependency Injection
Dependency injection is managed using ViewModel Providers and ViewModelFactory to supply the necessary dependencies (like TaskRepository) to the ViewModels.
Screenshots
1. Task List Screen
 ![Task List Screen](https://github.com/Avii00723/TaskManager/blob/master/app/src/main/res/images/listscreen.jpg)
2. Task Input Screen
 ![Task Input Screen](https://github.com/Avii00723/TaskManager/blob/master/app/src/main/res/images/taskinputscreen.jpg)
3. Dark Theme Mode
   ![Dark Theme Screen](https://github.com/Avii00723/TaskManager/blob/master/app/src/main/res/images/darkscreen.jpg)
 
