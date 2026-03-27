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
  <img src="https://github.com/user-attachments/assets/bfc960e5-8fb5-4db3-afbf-5e5739d217f0" width="200"/>
  
  <img src="https://github.com/user-attachments/assets/ea73ecd8-d656-407a-b1fe-d6a0ba8ca587" width="200"/>
  
  <img src="https://github.com/user-attachments/assets/d1ad0992-0c28-4596-beb1-c203df344787" width="200"/>

</p>

<p>
  <img src="https://github.com/user-attachments/assets/0558d2c9-97ec-4e14-bdae-c30d3866d199" width="200"/>
  
  <img src="https://github.com/user-attachments/assets/4b15b6e4-b645-4092-97bb-f3a78e5bf922"  width="200"/>
</p>
---

## 🎨 UI Design Reference

The UI of this application is inspired and designed based on the following Figma design:
👉 https://www.figma.com/design/Rh3TjYknqGIPwfQwbkEe0c/Movie-App--Community-?node-id=1-5&t=WqZC9Zyc6iQhmIHG-0
The design was adapted and implemented using Android XML layouts while maintaining a modern and user-friendly interface.

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

👉 [Download APK](app-release.apk)


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
