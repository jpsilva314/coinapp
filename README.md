# 📱 Android Test Coin App

An Android application built using **MVVM (Model-View-ViewModel)** architecture, demonstrating clean architecture principles, separation of concerns, and modern Android development best practices.

## 🧰 Tech Stack

- **Kotlin** - Modern, concise, safe programming language for Android
- **MVVM Architecture** - Model-View-ViewModel for structured code and testability
- **Jetpack Components**
    - Jetpack compose
    - ViewModel
    - Room (for local persistence)
- **Retrofit** - Type-safe HTTP client for network requests
- **Coroutines** - Asynchronous programming with structured concurrency
- **Glide** - Glide is a fast and efficient open source media management and image loading framework for Android that wraps media decoding, memory and disk caching
- **Material Components** - UI and design best practices

## 📂 Project Structure

```plaintext
com.example.mycoinapp
├── data
│   ├── model        # Data models (e.g. DTOs, Room entities)
│   ├── network      # Retrofit API interfaces
│   └── repository   # Data handling and logic
├── ui
│   ├── screens      # Composable Screens
│   └── viewmodel    # ViewModels
└── MainActivity     # Main Activity
```

## 🏁 Getting Started
# Prerequisites
* Android Studio Electric Eel or newer
* Gradle 8.0+
* Android SDK 34+

# Installation
* Clone this repository: https://github.com/jpsilva314/coinapp.git
* Sync Gradle and build the project.
* Run the app on an emulator or physical device.

## 🚀 Features
* Clean and scalable MVVM architecture
* Network operations using Retrofit and Coroutines
* Local persistence with Room
* Compose and ViewModel for clean UI

## 🧪 Testing
* Unit testing with JUnit

## 💡 Best Practices Followed
* Clear separation of concerns
* ViewModels as the source of UI data
* Repositories abstracting data sources
* Lifecycle-aware components
* Minimal logic in Activities/Fragments

