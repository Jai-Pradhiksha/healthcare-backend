# Smart Healthcare Management System - Backend

This is the backend of the **Smart Healthcare Management System**, built using **Spring Boot**. The backend handles data management, user authentication, appointment scheduling, and integrates with the frontend for a seamless user experience.

## Features

- **User Authentication**: Secure login and registration for patients, doctors, and admins.
- **Role-Based Access Control**: Different access levels for patients, doctors, and admins to manage their respective functionalities.
- **Appointment Management**: APIs for creating, updating, and retrieving appointment details.
- **Data Security**: Uses best practices to ensure secure storage and transmission of sensitive patient information.
- **Integration with Machine Learning**: AI-driven features for treatment recommendations based on patient data.
- **RESTful API**: Exposes a set of RESTful endpoints for the frontend to interact with.

## Technologies Used

- **Spring Boot**: Framework for building the backend services.
- **PostgreSQL**: Database for storing user and appointment data.
- **Spring Security**: For implementing security and authentication features.
- **Hibernate**: ORM for database interactions.
- **Gradle**: Build and dependency management.

## Folder Structure

```bash
/src
  ├── main/
  │   ├── java/
  │   │   └── com/
  │   │       └── vts/
  │   │           └── healthcare/
  │   │               ├── config/            # Configuration classes
  │   │               ├── controller/        # REST controllers
  │   │               ├── model/             # Entity classes
  │   │               ├── repository/         # JPA repositories
  │   │               └── service/            # Business logic
  │   └── resources/
  │       ├── application.properties          # Application configuration
  │       └── static/                         # Static resources
  └── test/                                   # Unit and integration tests
```

## API Endpoints

### Authentication

- `POST /api/v1/healthcare/register` - Register a new user.
- `POST /api/v1/healthcare/login` - Log in a user.

### Users

- `GET /api/v1/healthcare/` - Retrieve all users.
- `POST /api/v1/healthcare/` - Create a new user.
- `PUT /api/v1/healthcare/{id}` - Update an existing user.
- `DELETE /api/v1/healthcare/{id}` - Delete an existing user.
## Upcoming Features

- **Telemedicine Integration**: Implement video consultations for patients.
- **AI-Driven Treatment Suggestions**: Provide personalized treatment options based on user data.
- **Blockchain-Based Data Security**: Enhance security for patient data using blockchain technology.
