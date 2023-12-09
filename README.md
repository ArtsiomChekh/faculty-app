# Факультет
Приложение для организации учебного процесса на факультете информационных технологий

# Текущие задачи и баги

# Используемые технологии

* Java
* Spring MVC
* Spring Boot
* Maven
* Spring Data JPA (Hibernate)
* Spring Security
* MySQL database
* Idea
* Bootstrap
* Thymeleaf
* HTML
* CSS
* Apache Tomcat
* JUnit
* Mockito

# Функционал

* Регистрация и авторизация пользователей -
* Создание и удаление записей +
* Поиск и фильтрация данных +
* Отображение данных в таблицах и списках +
* Структура базы данных +
* Роли -
* Юнит-тесты +
* Архитектура MVC + 

# Структура БД

![Image alt](https://github.com/ArtsiomChekh/faculty-app/blob/b9102fc02c85e27e123ceb751eef375075b9b655/src/main/resources/static/img/MyDB.png)

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
department_id INT,
start_time DATETIME,
end_time DATETIME,
FOREIGN KEY (subject_id) REFERENCES department (id),
FOREIGN KEY (department_id) REFERENCES department (id)
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


# Установка и запуск
* mvn spring-boot:run
* http://localhost:8080
