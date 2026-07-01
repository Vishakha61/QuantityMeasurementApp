CREATE TABLE IF NOT EXISTS quantity_measurement (

    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    measurement_type VARCHAR(100),

    operation_type VARCHAR(100),

    value1 DOUBLE,

    unit1 VARCHAR(50),

    value2 DOUBLE,

    unit2 VARCHAR(50),

    result VARCHAR(200),

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);