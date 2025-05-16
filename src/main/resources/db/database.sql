CREATE TABLE person (
    id INT PRIMARY KEY AUTO_INCREMENT,
    last_name VARCHAR(100),
    first_name VARCHAR(100),
    gender VARCHAR(10),
    birth_date DATE,
    birth_city VARCHAR(100),
    social_security_number VARCHAR(20) UNIQUE
);

CREATE TABLE address (
    id INT PRIMARY KEY AUTO_INCREMENT,
    person_id INT,
    address_text TEXT,
    FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE CASCADE
);

CREATE TABLE room (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    gender_restriction VARCHAR(10),
    min_age INT,
    max_age INT
);

CREATE TABLE bed (
    id INT PRIMARY KEY AUTO_INCREMENT,
    label VARCHAR(100),
    capacity INT CHECK (capacity >= 1 AND capacity <= 2),
    room_id INT,
    FOREIGN KEY (room_id) REFERENCES room(id) ON DELETE CASCADE
);

CREATE TABLE occupation (
    id INT PRIMARY KEY AUTO_INCREMENT,
    person_id INT,
    bed_id INT,
    start_date DATE,
    end_date DATE,
    has_left BOOLEAN,
    FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE RESTRICT,
    FOREIGN KEY (bed_id) REFERENCES bed(id) ON DELETE RESTRICT
);
