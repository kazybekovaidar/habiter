CREATE SCHEMA IF NOT EXISTS habiter;
CREATE TABLE IF NOT EXISTS habiter.user
(
    user_id SERIAL NOT NULL PRIMARY KEY,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(250) NOT NULL,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS habiter.role
(
    role_id SERIAL NOT NULL
    CONSTRAINT role_role_id_pk
    PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS habiter.user_role
(
    user_role_id SERIAL NOT NULL
    CONSTRAINT user_role_user_role_id_pk
    PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES habiter.user(user_id) ON DELETE CASCADE,
    role_id INTEGER NOT NULL REFERENCES habiter.role(role_id) ON DELETE CASCADE
    );

INSERT INTO habiter.role(role_id, name) VALUES(DEFAULT, 'ROLE_USER'), (DEFAULT, 'ROLE_ADMIN');