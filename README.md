# 🎬 Movie App

A modern Android Movie App built using Kotlin following MVVM architecture. The app fetches real-time movie data from TMDB API and provides a smooth and dynamic user experience with authentication and offline support.

---

## 📱 App Overview

This Movie App allows users to browse trending and popular movies, view detailed information including cast, runtime, language, and trailers. It also includes a complete authentication system with Sign In, Sign Up, and Logout functionality using local database storage.

The app is designed with a clean UI inspired by modern streaming platforms and ensures a seamless experience even in offline mode using caching.

---

## 🏗️ Architecture

The app follows **MVVM (Model-View-ViewModel)** architecture:

* **Model**: Data layer (Room DB, API responses, Repository)
* **View**: UI layer (Fragments, XML layouts)
* **ViewModel**: Handles business logic and UI state

### Key Components:

* **Repository Pattern** → Manages data from API and local database
* **Room Database** → Stores movies and user data locally
* **Retrofit** → Handles API calls (TMDB)
* **LiveData + ViewModel** → Reactive UI updates
* **Hilt (Dependency Injection)** → Manages dependencies

---

## ✨ Features

* 🔐 User Authentication (Sign In / Sign Up / Logout)
* 🎞️ Browse Trending & Popular Movies
* 📄 Movie Details Screen (Cast, Duration, Language, Overview)
* 🎬 Watch Trailer (YouTube integration)
* 💾 Offline Support (Room Database caching)
* 🔄 Dynamic UI updates
* 🎨 Modern UI Design (Custom bottom navigation & cards)

---

## 📸 Screenshots

<!-- Add your screenshots here -->

| Splash Screen                    
| --------------------------------- 
| ![Splash](<img width="1080" height="2400" alt="splash_screen" src="https://github.com/user-attachments/assets/8c4da2c8-ec9f-4ffc-8509-570b0deb52c2" />) 

|Sign-In Scrren
|-----------------------------------
| ![SignIn](<img width="1080" height="2400" alt="sign_in_screen" src="https://github.com/user-attachments/assets/3b368080-f4f9-4361-8ae9-8165114838ed" />) |

|Sign-Up Scrren
|-----------------------------------
| ![SignUp](<img width="1080" height="2400" alt="sign_up_screen" src="https://github.com/user-attachments/assets/7332fcf0-f80d-40ae-b6e9-97f765293b04" />
) |


| Home Screen
|-----------------------------------
![Home](<img width="1080" height="2400" alt="Home_screen" src="https://github.com/user-attachments/assets/0fd5cf74-0eba-45a5-8f62-ce6b2723217e" />
) |

| Details Screen                      
| ----------------------------------- 
| ![Details](<img width="1080" height="2400" alt="Detail_scrren" src="https://github.com/user-attachments/assets/c048a142-25ef-4e35-86c4-29133df480f7" />
) 

---

## 🛠️ Tech Stack

* **Language**: Kotlin
* **Architecture**: MVVM
* **UI**: XML (Material Design)
* **Networking**: Retrofit
* **Database**: Room
* **Dependency Injection**: Hilt
* **Image Loading**: Glide
* **API**: TMDB API

---

## 📦 APK

Download the signed APK from below:

👉 [Download APK](app-release.apk)

---

## 🚀 How to Run

1. Clone the repository
2. Open in Android Studio
3. Add your TMDB API key
4. Run the app

---

## 📌 Note

This project is built for learning and demonstration purposes. Authentication is implemented using a local database.

---

## 👨‍💻 Author
Kamal Kanakhara
