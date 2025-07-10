# Gratitude Journal [![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)](https://opensource.org/licenses/)

## 📝 Overview

The **Gratitude Journal** is a RESTful API built with Spring Boot, designed to support a gratitude journaling application. It allows users to create, read, update, and delete gratitude entries efficiently and securely.

The backend leverages Google Firebase Admin SDK with Firestore as its NoSQL database, uses MapStruct for streamlined DTO-to-entity mapping, and integrates Swagger UI for automatic API documentation.

## 🎯 Features
- ✅ CRUD REST API for gratitude entries (GET, POST, PUT, DELETE)
- ✅ JSON request/response format
- ✅ Persistent storage with Firestore via Firebase Admin SDK
- ✅ Object mapping with MapStruct to simplify code and reduce boilerplate
- ✅ API documentation powered by SpringDoc OpenAPI (Swagger UI)
- ✅ Validation and error handling using Spring Boot Validation
- ✅ Uses Lombok to reduce boilerplate code for model classes

## 🧰 Technologies

-  **Java 21**
- **Spring Boot 3.5.0**
- **Firebase Admin SDK (Firestore)**
- **MapStruct 1.6.3** 
- **Lombok 1.18.36** 
- **SpringDoc OpenAPI Starter WebMVC UI (Swagger)** 
- **Maven** for dependency and build management


# 🚀 Getting Started

---

## 📦 Prerequisites

- Java 21 or higher installed
- Maven 3.6+
- Firebase project with Firestore enabled
- Firebase service account JSON key file

## ⚙️ Setup

### 1. Clone the repository:

```bash
git clone https://github.com/timi15/gratitude-journal-backend.git
cd gratitude-journal-backend 
```

### 2. Firebase Service Account Setup

To connect this backend to your Firebase Firestore database, you need to create a Firebase service account key and add it to the project.

**Steps to create the Firebase service account key:**

1. Go to the [Firebase Console](https://console.firebase.google.com/).
2. Select your Firebase project (or create a new one if you don't have one yet).
3. Navigate to **Project Settings > Service Accounts**
4. Click on **Generate new private key**.
5. Confirm the download of the JSON file containing your service account credentials.
6. Rename the downloaded file to `serviceAccountKey.json`.
7. Place this file into your project directory at:  
   `src/main/resources/config/serviceAccountKey.json`

## Build & Run

### 1. Build the project

```bash
./mvnw clean package
```

### 2. Build the Docker image

```bash
docker build -t gratitude-journal .
```

### 3. Run the Docker container

```bash
docker run -p 8080:8080 gratitude-journal
```

**You can now access the application at:**

```
http://localhost:8080/
```

## 📘 API Documentation & Endpoints

### 🔍 Swagger UI

This project uses **Swagger** for API documentation and testing.

After running the application, you can access the Swagger UI in your browser at:

```
http://localhost:8080/swagger-ui/index.html#/
```

This interactive UI allows you to explore and test all available REST endpoints.

---

### 📡 Key API Endpoints

Here are some of the main REST API endpoints available in the backend:

| HTTP Method | Endpoint                              | Description                       |
|-------------|---------------------------------------|-----------------------------------|
| `GET`       | `/v1/gratitudejournal/gratitudes`     | Retrieve all gratitude entries    |
| `GET`       | `/v1/gratitudejournal/gratitude/{id}` | Retrieve a single entry by ID     |
| `POST`      | `/v1/gratitudejournal/save`           | Create a new gratitude entry      |
| `PUT`       | `/v1/gratitudejournal/gratitude/{id}` | Update an existing entry          |
| `DELETE`    | `/v1/gratitudejournal/gratitude/{id}` | Delete an entry by ID             |

---

### 🧾 Notes

- All endpoints expect and respond with JSON.
- For full API details, use the Swagger UI.


## 👤 Author

**Name:** Tímea Varga  
**GitHub:** [@timi15](https://github.com/timi15)  

---
