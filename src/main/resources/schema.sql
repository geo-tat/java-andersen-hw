
CREATE TYPE ticket_type AS ENUM ('DAY', 'WEEK', 'MONTH', 'YEAR');

CREATE TABLE public.user (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE public.ticket (
    id VARCHAR(4) UNIQUE PRIMARY KEY,
    user_id INT REFERENCES public.user(id),
    ticket_type ticket_type NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
