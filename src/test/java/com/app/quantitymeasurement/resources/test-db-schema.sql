DROP TABLE IF EXISTS quantity_measurement;

CREATE TABLE quantity_measurement (

                                      id INT AUTO_INCREMENT PRIMARY KEY,

                                      operation VARCHAR(50) NOT NULL,

                                      measurement_type VARCHAR(50) NOT NULL,

                                      quantity1_value DOUBLE,

                                      quantity1_unit VARCHAR(50),

                                      quantity2_value DOUBLE,

                                      quantity2_unit VARCHAR(50),

                                      result VARCHAR(100),

                                      error_message VARCHAR(255)
);