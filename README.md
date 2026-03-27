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

<h2>📸 Screenshots</h2>

<p>
  <img src="<img alt="splash_screen" src="https://github.com/user-attachments/assets/e01f99c0-f4b0-4ac3-93ea-f8f49e9a0460" />
" width="200"/>
  <img src="<img  alt="sign_in_screen" src="https://github.com/user-attachments/assets/73f4284d-22a9-4ab2-b395-943650899360" />
" width="200"/>
  <img src="" width="200"/>
</p>

<p>
  <img src="screenshots/details.png" width="200"/>
  <img src="screenshots/profile.png" width="200"/>
</p>
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
