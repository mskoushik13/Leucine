CREATE TYPE Role AS ENUM ('STUDENT', 'FACULTY_MEMBER', 'ADMINISTRATOR');
-- User Table
CREATE TABLE User (
id SERIAL PRIMARY KEY,
username VARCHAR(50) UNIQUE NOT NULL,
password VARCHAR(255) NOT NULL,
role Role NOT NULL,
name VARCHAR(100) NOT NULL,
email VARCHAR(100) UNIQUE NOT NULL,
phone VARCHAR(15)
);
-- Department Table
CREATE TABLE Department (
id SERIAL PRIMARY KEY,
name VARCHAR(100) NOT NULL,
description TEXT
);
-- StudentProfile Table
CREATE TABLE StudentProfile (
user_id INTEGER PRIMARY KEY,
photo VARCHAR(255),
department_id INTEGER NOT NULL,
year VARCHAR(50),
FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE,
FOREIGN KEY (department_id) REFERENCES Department(id) ON DELETE CASCADE
);
-- FacultyProfile Table
CREATE TABLE FacultyProfile (
user_id INTEGER PRIMARY KEY,
photo VARCHAR(255),
department_id INTEGER NOT NULL,
office_hours VARCHAR(255),
FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE,
FOREIGN KEY (department_id) REFERENCES Department(id) ON DELETE CASCADE
);
-- AdministratorProfile Table
CREATE TABLE AdministratorProfile (
user_id INTEGER PRIMARY KEY,
photo VARCHAR(255),
department_id INTEGER NOT NULL,
FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE,
FOREIGN KEY (department_id) REFERENCES Department(id) ON DELETE CASCADE
);
-- Course Table
CREATE TABLE Course (
id SERIAL PRIMARY KEY,
title VARCHAR(100) NOT NULL,
description TEXT,
department_id INTEGER NOT NULL,
faculty_id INTEGER NOT NULL,
FOREIGN KEY (department_id) REFERENCES Department(id) ON DELETE
CASCADE,
FOREIGN KEY (faculty_id) REFERENCES FacultyProfile(user_id) ON DELETE
CASCADE
);
-- Enrollment Table
CREATE TABLE Enrollment (
id SERIAL PRIMARY KEY,
student_id INTEGER NOT NULL,
course_id INTEGER NOT NULL,
FOREIGN KEY (student_id) REFERENCES StudentProfile(user_id) ON DELETE
CASCADE,
FOREIGN KEY (course_id) REFERENCES Course(id) ON DELETE CASCADE
);
-- Insert some data into Department
INSERT INTO Department (name, description) VALUES
('Computer Science', 'Department of Computer Science'),
('Mathematics', 'Department of Mathematics'),
('Physics', 'Department of Physics'),
('Chemistry', 'Department of Chemistry')