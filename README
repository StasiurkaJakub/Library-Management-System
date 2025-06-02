# 📖 Library Management System

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-2.7.9-brightgreen)
![Spring Security](https://img.shields.io/badge/Spring_Security-5.7-yellowgreen)
![WebSocket](https://img.shields.io/badge/WebSocket-STOMP-lightgrey)
![Database](https://img.shields.io/badge/Database-H2-orange)
![Build](https://img.shields.io/badge/Build-Maven-blueviolet)

A **full-featured library management system** with real-time notifications, user authentication, and administrative controls — built with Spring Boot and Thymeleaf.

---

## 🚀 Features

### 📚 Core Functionality

- 🔐 User authentication (Register / Login)
- 🛡️ Role-based access control: `USER` / `ADMIN`
- 📘 Book catalog management (CRUD)
- 🔄 Book borrowing and returning system
- 🔍 Book search functionality

### ⚙️ Admin Panel

- 👥 User management (view / delete)
- 📊 Monitor borrow/return transactions
- 📈 System statistics dashboard
- 🩺 Spring Actuator monitoring panel

### 🌐 Advanced Capabilities

- 📡 REST API for books and borrow records
- 🔔 Real-time WebSocket notifications:

  - 📗 New book alerts
  - 📤 Book return notifications

- 💻 Responsive UI with Thymeleaf & Bootstrap 5

---

## 🛠️ Technology Stack

### 🔧 Backend

- **Java 17**
- **Spring Boot 2.7.9**
- **Spring Security 5.7**
- **Spring WebSocket (STOMP)**
- **Spring Data JPA**

### 🎨 Frontend

- **Thymeleaf templates**
- **Bootstrap 5**
- **SockJS + STOMP.js**

### 🗃️ Database

- **H2 (In-memory)**

### ⚙️ Build Tool

- **Maven**

---

## 📦 Key Dependencies

```xml
<dependencies>
    <!-- Spring Boot Starters -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-websocket</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>

    <!-- Thymeleaf + Security Integration -->
    <dependency>
        <groupId>org.thymeleaf.extras</groupId>
        <artifactId>thymeleaf-extras-springsecurity5</artifactId>
        <version>3.0.4.RELEASE</version>
    </dependency>

    <!-- H2 Database -->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

## 🧪 How to Run

```bash
# Clone the repository
git clone https://github.com/StasiurkaJakub/Library-Management-System
cd library-management-system

# Run the application
./mvnw spring-boot:run
```

> App will be available at: `http://localhost:8443`

---

## 📬 WebSocket Endpoints

| Event                 | Topic                 |
| --------------------- | --------------------- |
| New Book Notification | `/topic/newBook`      |
| Book Return Alert     | `/topic/bookReturned` |

---

## 👤 Roles

| Role  | Access Level                             |
| ----- | ---------------------------------------- |
| USER  | View books, borrow/return, notifications |
| ADMIN | Full access: users, books, stats, etc.   |

---
