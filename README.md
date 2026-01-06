# Full-Stack Web Application for Sports Field Rental Management

A full-stack web application developed using Spring Boot and React, designed to demonstrate end-to-end application development, RESTful communication, and clean separation between backend and frontend layers.

---

## Project Overview

This project represents a full-stack system for managing sports field rentals.  
The backend is implemented using Spring Boot and exposes RESTful APIs, while the frontend is built with React to provide a modern, responsive user interface.

The application supports centralized data management, business logic processing on the server side, and dynamic data presentation on the client side.

---

## Core Contributions

### Backend Development (Spring Boot)
Implemented a REST-based backend responsible for:
- Handling client requests
- Processing business logic
- Managing persistence and database interaction
- Exposing structured API endpoints for frontend consumption

The backend follows layered architecture principles, separating controllers, services, and data access logic.

### Frontend Development (React)
Developed a single-page application (SPA) using React, enabling:
- Dynamic rendering of data
- Client-side routing
- Reusable UI components
- Asynchronous communication with the backend via HTTP requests

### RESTful Communication
Established clear and structured communication between frontend and backend using REST APIs and JSON payloads, ensuring decoupled and scalable system design.

### State and Data Management
Ensured consistent data flow between frontend and backend, handling:
- Fetching and displaying data
- Creating, updating, and deleting entities
- Synchronization of UI state with backend responses

### Application Architecture
Designed the project as a full-stack solution with a clear separation of concerns:
- Backend: business logic, persistence, security foundation
- Frontend: presentation layer and user interaction
- Shared contract through API definitions

---

## Tech Stack

| Layer        | Technology                    |
|-------------|--------------------------------|
| Backend     | Java, Spring Boot              |
| Frontend    | React                          |
| API Style   | REST (JSON)                    |
| Database    | Relational Database (JPA/JDBC) |
| Build Tool  | Maven                          |
| Frontend Tools | Axios, React Router        |
| Paradigm    | Object-Oriented Programming    |
| Tools       | Git, NetBeans, VS Code         |
| Runtime     | JVM, Node.js                   |

---

## How It Works

1. The backend server is started and exposes REST endpoints.
2. The React frontend is launched and connects to the backend via HTTP requests.
3. Users interact with the UI to perform application operations.
4. The frontend sends requests to the backend using Axios.
5. The backend processes requests, accesses the database, and returns responses.
6. The frontend dynamically updates the UI based on received data.

---

## Project Structure

/backend
└── Spring Boot application (controllers, services, repositories)

/frontend
└── React application (components, routing, services)
