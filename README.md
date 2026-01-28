<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/0100e876-4df3-4779-846c-bdedb92d9e10" /># Todo Application - Spring Boot Backend

A RESTful API backend for the Todo application built with Spring Boot, JPA, and H2 Database.

## Project Structure

```
todo-backend/
├── src/
│   ├── main/
│   │   ├── java/com/example/todo/
│   │   │   ├── TodoApplication.java          # Main application
│   │   │   ├── controller/
│   │   │   │   └── TodoController.java       # REST API endpoints
│   │   │   ├── service/
│   │   │   │   └── TodoService.java          # Business logic
│   │   │   ├── repository/
│   │   │   │   └── TodoRepository.java       # Data access
│   │   │   └── model/
│   │   │       └── Todo.java                 # Entity model
│   │   └── resources/
│   │       └── application.properties        # Configuration
│   └── test/
└── pom.xml                                   # Maven dependencies
```

## Prerequisites

- Java 17 or higher
- Maven 3.6+

## Setup Instructions

### 1. Navigate to the backend directory
```bash
cd todo-backend
```

### 2. Build the project
```bash
mvn clean install
```

### 3. Run the application
```bash
mvn spring-boot:run
```

The server will start on `http://localhost:8080`

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/todos` | Get all todos |
| GET | `/api/todos/{id}` | Get a single todo |
| POST | `/api/todos` | Create a new todo |
| PUT | `/api/todos/{id}` | Update a todo |
| DELETE | `/api/todos/{id}` | Delete a todo |

## Example API Requests

### Create a Todo (POST)
```bash
curl -X POST http://localhost:8080/api/todos \
  -H "Content-Type: application/json" \
  -d '{"text": "Learn Spring Boot", "completed": false}'
```

### Get All Todos (GET)
```bash
curl http://localhost:8080/api/todos
```

### Update a Todo (PUT)
```bash
curl -X PUT http://localhost:8080/api/todos/1 \
  -H "Content-Type: application/json" \
  -d '{"text": "Learn Spring Boot", "completed": true}'
```

### Delete a Todo (DELETE)
```bash
curl -X DELETE http://localhost:8080/api/todos/1
```

## Database

The application uses H2 in-memory database by default for easy development.

### H2 Console
- Access at: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:tododb`
- Username: `sa`
- Password: (leave empty)

### Switch to MySQL
1. Uncomment MySQL dependency in `pom.xml`
2. Uncomment MySQL configuration in `application.properties`
3. Update credentials and database name

## CORS Configuration

The backend is configured to accept requests from `http://localhost:3000` (React frontend).
Update in `TodoController.java` if your frontend runs on a different port.

## Testing

Run tests with:
```bash
mvn test
```

## Technologies Used

- **Spring Boot 3.2.0** - Application framework
- **Spring Data JPA** - Data access layer
- **H2 Database** - In-memory database
- **Maven** - Build tool
- **Java 17** - Programming language


output screenshot:
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/52a091d1-9e4e-4795-9bb8-58a49a7426f4" />
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/244eb560-9751-458f-b33f-075db13c9d7d" />
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/28685a3d-4566-4c55-8e3b-cd9a398d0f4b" />
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/ace42f7d-cfe4-41f2-8d57-fec10ee175d2" />
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/d13ee59c-14ab-4ce9-8d8d-64152f9a8738" />

