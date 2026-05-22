# SimpleInvest

**SimpleInvest** is a JavaFX desktop application designed to simulate investment portfolio management in a safe and educational environment.

The project allows users to create virtual portfolios, simulate trading operations, analyze performance, and experiment with market dynamics without using real money.

---

## Implemented Features

- Portfolio creation and management
- Portfolio rename and deletion
- Portfolio performance visualization
- Asset search system
- SQLite local persistence
- Modular JavaFX navigation
- Layered application architecture

---

## Project Scope

The current implementation focuses primarily on:

- UC3 — Portfolio Management
- UC5 — Asset Search

Additional functionalities are documented at analysis and design level inside the project documentation.

---

## Technologies

- Java
- JavaFX
- SQLite
- JDBC
- FXML
- MVC / Layered Architecture
- UML

---

## Architecture

The application follows a **Layered Architecture** pattern with clear separation of responsibilities:

- Presentation Layer
- Business Logic Layer
- Data Access Layer
- Storage Layer

The system also includes:

- DAO pattern
- Service layer
- Singleton pattern for database management
- Modular JavaFX controllers

---

## Documentation

Project documentation is available inside the `/docs` folder:

- [Vision Document](docs/1_Visione/vision-document.pdf)
- [Requirements Specification](docs/2_Requisiti/requirements-specification.pdf)
- [Design Document](docs/3_Progettazione/design-document.pdf)
- [Implementation Document](docs/4_Implementazione/implementation-document.pdf)

---

## Running the Project

Clone the repository:

```bash
git clone <repository-url>
```

Make sure you have installed:

- Java 17+
- Maven

Then run the project with:

```bash
mvn javafx:run
```

---

## Authors

- Simone Donati
- Gabriele Levati

---

## Academic Context

University project focused on:

- software engineering
- object-oriented design
- financial simulation
- layered architectures
- desktop application development
