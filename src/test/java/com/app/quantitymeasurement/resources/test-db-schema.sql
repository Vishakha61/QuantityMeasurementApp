DROP TABLE IF EXISTS quantity_measurement_history;

CREATE TABLE quantity_measurement_history (

                                              id INT AUTO_INCREMENT PRIMARY KEY,

                                              first_value DOUBLE,

                                              first_unit VARCHAR(50),

                                              first_measurement_type VARCHAR(50),

                                              second_value DOUBLE,

                                              second_unit VARCHAR(50),

                                              second_measurement_type VARCHAR(50),

                                              operation VARCHAR(50),

                                              result VARCHAR(100),

                                              error_message VARCHAR(255),

                                              is_error BOOLEAN
);