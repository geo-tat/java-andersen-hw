
CREATE TYPE ticket_type AS ENUM ('DAY', 'WEEK', 'MONTH', 'YEAR');
create TYPE user_status as enum ('ACTIVATED', 'DEACTIVATED');

CREATE TABLE user_info (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_status user_status NOT NULL
);

CREATE TABLE ticket (
    id BIGINT PRIMARY KEY,
    user_id INT REFERENCES user_info(id) ON DELETE CASCADE,
    ticket_type ticket_type NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
