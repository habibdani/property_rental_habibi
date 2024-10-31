-- CREATE DATABASE property_db; create manual di db

CREATE TABLE property (
    p_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    p_name VARCHAR(100) NOT NULL,
    p_location VARCHAR(255),
    p_price DECIMAL(15, 2) NOT NULL,
    p_description TEXT,
    p_status BOOLEAN,
    p_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    p_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO property (p_name, p_location, p_price, p_description, p_status) VALUES 
('Rumah Minimalis', 'Jakarta', 150000000, 'Rumah minimalis di Jakarta', true),
('Rumah Mewah', 'Bandung', 300000000, 'Rumah mewah di Bandung', true),
('Rumah Elit', 'PIK', 900000000, 'Rumah Elit di PIK', true),
('Rumah Kuno', 'Jogja', 500000000, 'Rumah Kuno di Jogja', true);

