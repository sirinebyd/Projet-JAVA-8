CREATE TABLE IF NOT EXISTS person (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    last_name VARCHAR(100),
    first_name VARCHAR(100),
    gender VARCHAR(10),
    birth_date DATE,
    birth_city VARCHAR(100),
    social_security_number VARCHAR(20) UNIQUE
);

CREATE TABLE IF NOT EXISTS address (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    person_id INTEGER,
    address_text TEXT,
    FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS room (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(100),
    gender_restriction VARCHAR(10),
    min_age INTEGER,
    max_age INTEGER
);

CREATE TABLE IF NOT EXISTS bed (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    label VARCHAR(100),
    capacity INTEGER CHECK (capacity >= 1 AND capacity <= 2),
    room_id INTEGER,
    FOREIGN KEY (room_id) REFERENCES room(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS occupation (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    person_id INTEGER,
    bed_id INTEGER,
    start_date DATE,
    end_date DATE,
    has_left BOOLEAN,
    FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE RESTRICT,
    FOREIGN KEY (bed_id) REFERENCES bed(id) ON DELETE RESTRICT
);