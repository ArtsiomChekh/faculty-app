# Faculty

An application for the organisation of the learning process in the Faculty of Information Technology.

# Used Technologies

* Java
* Spring MVC
* Spring Boot
* Maven
* Hibernate
* Spring Data JPA
* Spring Security
* MySQL database
* IntelliJ IDEA
* Thymeleaf
* HTML
* CSS
* Apache Tomcat
* JUnit
* Mockito

# Functionality

* User registration and authentication -
* Creation and deletion of records +
* Data search and filtering +
* Displaying data in tables and lists +
* Database structure +
* Roles -
* Unit tests +
* MVC architecture +

# Database Structure

![Image alt](https://github.com/ArtsiomChekh/faculty-app/blob/b9102fc02c85e27e123ceb751eef375075b9b655/src/main/resources/static/img/MyDB.png)

# Set up DB

Simple option:

* create a schema
* add the information to the application.properties file:
  spring.datasource.url=jdbc:mysql://localhost:3306/YOURSCHEMANAME?useSSL=false&allowPublicKeyRetrieval=true
  spring.datasource.username=YOURUSERNAME
  spring.datasource.password=YOURPASSWORD

Secure option:

* create a schema
* add the database.properties file
* add the database.properties file to the .gitignore
* add the information to the database.properties file:
  spring.datasource.url=jdbc:mysql://localhost:3306/YOURSCHEMANAME?useSSL=false&allowPublicKeyRetrieval=true
  spring.datasource.username=YOURUSERNAME
  spring.datasource.password=YOURPASSWORD

Creating tables:

CREATE TABLE department (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255),
description VARCHAR(2000)
);

CREATE TABLE student (
id INT AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(255),
last_name VARCHAR(255)
);

CREATE TABLE subject (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255),
department_id INT,
FOREIGN KEY (department_id) REFERENCES department (id)
);

CREATE TABLE teacher (
id INT AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(255),
last_name VARCHAR(255),
department_id INT,
FOREIGN KEY (department_id) REFERENCES department (id)
);

CREATE TABLE schedule (
id INT AUTO_INCREMENT PRIMARY KEY,
subject_id INT,
teacher_id INT,
week_day INT,
start_time TIME,
end_time TIME,
FOREIGN KEY (subject_id) REFERENCES subject (id),
FOREIGN KEY (teacher_id) REFERENCES teacher (id)
);

CREATE TABLE department_student (
department_id INT,
student_id INT,
PRIMARY KEY (department_id, student_id),
FOREIGN KEY (department_id) REFERENCES department (id),
FOREIGN KEY (student_id) REFERENCES student (id)
);

CREATE TABLE student_subject (
student_id INT,
subject_id INT,
PRIMARY KEY (student_id, subject_id),
FOREIGN KEY (student_id) REFERENCES student (id),
FOREIGN KEY (subject_id) REFERENCES subject (id)
);

CREATE TABLE teacher_subject (
teacher_id INT,
subject_id INT,
PRIMARY KEY (teacher_id, subject_id),
FOREIGN KEY (teacher_id) REFERENCES teacher (id),
FOREIGN KEY (subject_id) REFERENCES subject (id)
);

# Start application

* mvn spring-boot:run
* http://localhost:8080

OR

* run FacultyAppApplication.java
