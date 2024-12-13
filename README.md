# VOTE - Virtual Online Transparent Elections

[![Java](https://img.shields.io/badge/Java-Programming-orange)](https://www.oracle.com/java/)  
[![SDG 16](https://img.shields.io/badge/SDG-16-blue)](https://sdgs.un.org/goals/goal16)  
[![Status](https://img.shields.io/badge/Status-Active-brightgreen)](https://github.com/Aleeiijx/sample.git)

---

## 🌐 Project Overview
The **VOTE - Virtual Online Transparent Elections** is a **Java-based application** designed to facilitate democratic participation. Through this secure, user-friendly platform, voters can register, cast their votes, and participate in elections, while administrators manage the process efficiently.

This system adheres to **Object-Oriented Programming (OOP)** principles, ensuring modularity, maintainability, and the potential for future expansions.

---

## ✨ Features

### 🗺️ For Voters:
- **Sign Up**: Register by providing your ID, name, and age (18+).
- **Sign In**: Log in to view the list of candidates and cast your vote.
- **Vote**: Securely vote for your preferred candidate.

### 🔑 For Admins:
- **Admin Login**: Access admin privileges with secure credentials.
- **Create Elections**: Set up elections and register candidates.
- **View Results**: Display real-time vote counts.
- **Election Stories**: Monitor activity logs, including voter participation and election events.

---

## 📊 OOP Principles Applied
The Voting System leverages the following OOP principles:

### 1. **Encapsulation**
   - Critical data like user credentials and election details (e.g., vote counts) are encapsulated within respective classes.
   - Access is controlled via getter and setter methods.

### 2. **Inheritance**
   - The `User` abstract class is a shared base for `Voter` and `Admin` classes, reducing code redundancy.

### 3. **Polymorphism**
   - Overridden methods like `login()` ensure tailored behavior for voters and admins.

### 4. **Abstraction**
   - Abstract classes and methods define shared properties and behaviors, leaving specific implementations to derived classes.

---

## 🌎 Integration with SDG 16
This project supports **Sustainable Development Goal (SDG) 16: Peace, Justice, and Strong Institutions** by promoting:

- **Inclusivity**: Allowing all eligible users to participate in elections.
- **Transparency**: Ensuring a fair and accountable electoral process.
- **Civic Engagement**: Empowering users to exercise their voting rights in a secure digital environment.

The system strengthens trust in democratic institutions and contributes to peaceful governance.

---

## 🖌️ Example Screenshots

### 1. **Main Menu**
The main menu offers clear options for voters and admins.  
![Main Menu](screenshots/main_menu.png)

### 2. **Voter Registration**
Voters can register with their ID, name, and age.  
![Voter Registration](screenshots/voter_registration.png)

### 3. **Voting Process**
Voters can view the list of candidates and cast their votes securely.  
![Voting Process](screenshots/voting_process.png)

### 4. **Admin Login and Menu**
Admins can manage elections, track activity logs, and view results.  
![Admin Login and Menu](screenshots/admin_login.png)

### 5. **Election Results**
Admins have access to real-time election results with detailed breakdowns.  
![Election Results](screenshots/election_results.png)

---

## 🔧 Instructions for Running the Program

### 1. Clone the Repository
Clone the repository to your local machine:
```bash
git clone https://github.com/Aleeiijx/voting-system.git
cd voting-system
```
git add screenshots/main_menu.png
git commit -m "Add Main Menu screenshot"
git push origin main


### 2. Set Up the Environment
Ensure you have **Java JDK 11+** installed. Compile the project:
```bash
javac -d bin src/**/*.java
```

### 3. Run the Application
Navigate to the compiled `bin` directory and execute:
```bash
java main.Main
```

---

## ⚙️ Future Enhancements
- **Blockchain Integration**: To further enhance transparency and security.
- **Multilingual Support**: Make the platform accessible to a diverse audience.
- **Mobile App**: Extend functionality to mobile platforms for greater accessibility.
- **Data Analytics**: Provide visual insights into voter turnout and trends.

---

## 📖 Documentation
Refer to the [User Guide](docs/user_guide.md) for detailed usage instructions.

For more information, visit the [GitHub Repository](https://github.com/Aleeiijx/sample.git).

---

© 2024 VOTE. All rights reserved.

