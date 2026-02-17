This is a console-based Banking Management Application built using Java, JDBC, and MySQL. 
The project follows layered architecture (Controller, Service, DAO, Model, Utility).

Controller  → Handles user interaction
Service     → Handles business logic & validation
DAO         → Handles SQL execution
Model       → Represents data (Admin object)
Utility     → Manages DB connection
Database    → Stores data with constraints

# Features Implemented
✔ Admin Registration
   - Input validation in Service layer
   - Email format validation
   - Password length validation
   - Unique email constraint handled at database level
   - Data persistence using JDBC and PreparedStatement

# Database Design
CREATE TABLE admin (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
mail VARCHAR(50) UNIQUE NOT NULL,
password VARCHAR(50) NOT NULL
);

## ▶ Sample Console Output

```
** Welcome to Banking Application **

1. Admin
2. User
3. Exit
Enter Your Option: 1

1. Login
2. Registration
3. Exit
Enter Your Option: 2

Enter Your Name:
Mahesh
Enter Your E-mail:
mahi@gmail.com
Enter Your Password:
Mahi@123

Registration Successful.
```


