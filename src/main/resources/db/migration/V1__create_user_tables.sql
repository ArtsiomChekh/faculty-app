CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled TINYINT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    UNIQUE KEY username (username)
);

CREATE TABLE user_roles (
    user_id INT NOT NULL,
    role VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO users (username, password, enabled) VALUES ('admin', '$2a$10$P7l5I9xhBXp4b9twuSGM0u5mRjvQDdWjT0mJL8g3hGzgk', 1);

INSERT INTO user_roles (user_id, role) VALUES (1, 'DEAN');