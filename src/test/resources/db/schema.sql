DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100)
);

DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100)
);