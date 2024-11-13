CREATE TABLE user_info (
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_status VARCHAR NOT NULL
);

CREATE TABLE ticket (
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    user_id INT REFERENCES user_info(id) ON DELETE CASCADE,
    ticket_type VARCHAR NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
