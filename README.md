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

🔐 Admin Login Module

The Admin Login feature allows registered administrators to authenticate using their email and password.

User Input (Email, Password)
        ↓
Controller (BankApp)
        ↓
AdminService.login()
   - Null validation
   - Password length validation
        ↓
AdminDAO.login()
   - Executes parameterized SQL query
        ↓
MySQL Database (admin table)
   - Verifies email & password match
        ↓
Returns true / false
        ↓
Controller displays result

🧠 Validation Logic (Service Layer)
   - Email must not be null
   - Password must not be null
   - Password must be at least 6 characters
   - Business validation is handled in the Service layer, not in the DAO.

🗄 Database Authentication
The DAO executes:
SELECT * FROM admin WHERE mail = ? AND password = ?

   - Uses PreparedStatement to prevent SQL injection
   - Returns success only if matching record exists
   - Database enforces UNIQUE email constraint

▶ Sample Console Output
```
1. Login
2. Registration
3. Exit
Enter Your Option: 1

Enter Your E-mail:
mahi@gmail.com
Enter Your Password:
Mahi@123

Login Success.
```

🏦 Account Creation Module
# Feature Overview

The Account Creation feature allows an Admin to create a new user account with:
Name
Email
Initial balance
Each user is assigned a system-generated unique account number.

🔢 Account Number Generation Logic

After inserting the user record:
The database generates an auto-increment id.
Account number is calculated as:
```
accountNumber = BASE_NUMBER + id
```
🗄 Database Design
```
CREATE TABLE user (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
mail VARCHAR(50) UNIQUE NOT NULL,
amount DOUBLE NOT NULL,
accountNumber BIGINT UNIQUE
);
```

▶ Sample Console Output
```
** Welcome to Banking Application **

1. Admin
2. User
3. Exit
Enter Your Option: 1

1. Login
2. Registration
3. Exit
Enter Your Option: 1

Enter Your E-mail: 
mahi@gmail.com
Enter Your Password: 
Mahi@123
Login Success.

1. Account Creation
2. Account Deletion
3. View Users
4. Exit
Enter Your Option: 1

Enter Name: 
Mahesh
Enter E-mail: 
mahi@gmail.com
Enter Initial Balance: 
1000
Account Number: 1003441001
Account Created Succesful.
```

📋 4. View All Users (Formatted Console Table)
Displays all users in structured table format:

```
+----+------------+----------------------+----------+--------------+
| Id | Name       | E-Mail               | Amount   | Account No   |
+----+------------+----------------------+----------+--------------+
| 1  | Mahesh     | mahi@gmail.com       | 10000.00 | 1003441001   |
| 2  | Ravi       | ravi@gmail.com       | 5000.00  | 1003441002   |
| 3  | Lakshmi    | lakshmi@gmail.com    | 1200.00  | 1003441003   |
| 4  | Rani       | rani@gmail.com       | 5200.00  | 1003441004   |
| 5  | Sree       | sree@gmail.com       | 2500.00  | 1003441005   |
| 6  | Raju       | raju@gmail.com       | 3500.00  | 1003441006   |
+----+------------+----------------------+----------+--------------+
```

❌ 5. Account Deletion
   - Deletes user using account number
   - Ensures safe removal using WHERE accountNumber = ?
   - Returns success/failure status



👤 User Module

1️⃣ User Registration
   - Updates user password
   - Validates email and password length
   - Uses PreparedStatement to prevent SQL injection

2️⃣ User Login
   - Authenticates using email and password
   - Database verification using parameterized query

3️⃣ View Balance
# Displays user account balance after successful login.
```
SELECT amount FROM user WHERE mail = ?
```
# Example Output:
```
View Balance
Amount: 2500.0
```

4️⃣ Amount Transfer (Transactional Logic)
   - Implements safe money transfer between accounts.

# Transfer Flow:
```
User Login
   ↓
Enter Amount
   ↓
Enter Receiver Account Number
   ↓
Withdraw from sender
   ↓
Deposit to receiver
   ↓
Commit Transaction
```
# SQL Used
Withdraw:
```
UPDATE user SET amount = amount - ? WHERE mail = ?
```
# Deposit:
```
UPDATE user SET amount = amount + ? WHERE accountNumber = ?
```
# Important Implementation Details:
   - Used connection.setAutoCommit(false)
   - Ensures atomic transaction
   - Both withdraw and deposit must succeed
   - Prevents partial money transfer

🧠 Key Backend Concepts Practiced
✔ Layered Architecture
✔ Separation of Concerns
✔ Primary Key-Based Updates
✔ JDBC PreparedStatement
✔ Transaction Management
✔ Console Table Formatting
✔ Database Constraints

🔥 What This Project Demonstrates
# This project demonstrates practical backend development skills including:
   - Database interaction
   - Transaction handling
   - Clean architecture design
   - Error handling and validation
   - Version control with Git

# Final Output:
```
** Welcome to Banking Application **

1. Admin
2. User
3. Exit
Enter Your Option: 1

1. Login
2. Registration
3. Exit
Enter Your Option: 1

Enter Your E-mail: 
mahi@gmail.com
Enter Your Password: 
Mahi@123
Login Success.

1. Account Creation
2. Account Deletion
3. View Users
4. Exit
Enter Your Option: 1

Enter Name: 
Chinna
Enter E-mail: 
chinna@gmail.com
Enter Initial Balance: 
5000
Account Number: 1003441007
Account Created Succesful.

1. Account Creation
2. Account Deletion
3. View Users
4. Exit
Enter Your Option: 3

+----+------------+----------------------+----------+--------------+
| Id | Name       | E-Mail               | Amount   | Account No   |
+----+------------+----------------------+----------+--------------+
| 1  | Mahesh     | mahi@gmail.com       | 2000.00  | 1003441001   |
| 2  | Ravi       | ravi@gmail.com       | 1000.00  | 1003441002   |
| 3  | Lakshmi    | lakshmi@gmail.com    | 2500.00  | 1003441003   |
| 4  | Raju       | raju@gmail.com       | 3500.00  | 1003441004   |
| 5  | Sree       | sree@gmail.com       | 1500.00  | 1003441005   |
| 6  | Ramya      | ramya@gmail.com      | 1700.00  | 1003441006   |
| 7  | Chinna     | chinna@gmail.com     | 5000.00  | 1003441007   |
+----+------------+----------------------+----------+--------------+
1. Account Creation
2. Account Deletion
3. View Users
4. Exit
Enter Your Option: 4

1. Login
2. Registration
3. Exit
Enter Your Option: 3

1. Admin
2. User
3. Exit
Enter Your Option: 2

Login Section

1. Login
2. Registration
3. Exit
Enter Option: 
1
Enter E-mail: 
mahi@gmail.com
Enter Password: 
Mahi@123
Login Successful.

1. View Balance
2. Amount Transfer
3. Exit
Enter Option: 
1
View Balance

Amount: 2000.0
1. View Balance
2. Amount Transfer
3. Exit
Enter Option: 
2
Account Transfer

Enter Amount: 
500
Enter Account Number: 
1003441005
Amount Transfered Succesful.

1. View Balance
2. Amount Transfer
3. Exit
Enter Option: 
1
View Balance

Amount: 1500.0
1. View Balance
2. Amount Transfer
3. Exit
Enter Option: 
3
Thank You
```
