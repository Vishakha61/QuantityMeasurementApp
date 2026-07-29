CREATE DATABASE IF NOT EXISTS quantity_measurement;

USE quantity_measurement;

CREATE TABLE IF NOT EXISTS quantity_measurement_history (

    id INT PRIMARY KEY AUTO_INCREMENT,


    first_value DOUBLE NOT NULL,

    first_unit VARCHAR(30) NOT NULL,

    first_measurement_type VARCHAR(30) NOT NULL,

    second_value DOUBLE,

    second_unit VARCHAR(30),

    second_measurement_type VARCHAR(30),

    operation VARCHAR(30) NOT NULL,

    result VARCHAR(255),

    error_message VARCHAR(255),

    is_error BOOLEAN DEFAULT FALSE,
    user_email VARCHAR(255) NOT NULL,

    created_at TIMESTAMP
    );