-- Create Student table
CREATE TABLE Student (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    dob DATE NOT NULL,
    address VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(20) NOT NULL
);

-- Create Course table
CREATE TABLE Course (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) UNIQUE NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL
);

-- Create LogEntry table
CREATE TABLE LogEntry (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_id BIGINT,
    FOREIGN KEY (student_id) REFERENCES Student(id),
    date DATE NOT NULL,
    category VARCHAR(50) NOT NULL,
    description VARCHAR(255) NOT NULL,
    time_spent TIMESTAMP NOT NULL
);
