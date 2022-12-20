CREATE USER "check-runner_user" WITH PASSWORD '12378';
CREATE DATABASE "check_runner" WITH OWNER = "check-runner_user";
\c "check_runner"

CREATE SCHEMA check_runner;

CREATE TABLE check_runner.product
(
    id          serial                NOT NULL,
    description character varying(20) NOT NULL,
    price       numeric               NOT NULL,
    is_discount boolean               NOT NULL,
    CONSTRAINT product_pk PRIMARY KEY (id)
);

CREATE TABLE check_runner.discount_card
(
    id serial NOT NULL,
    discount_percent integer NOT NULL,
    CONSTRAINT discount_card_pk PRIMARY KEY (id)
);

INSERT INTO check_runner.product
VALUES
(1, 'Apple', 1, true),
(2, 'Pear', 0.8, false),
(3, 'Orange', 1.5, false),
(4, 'Lemon', 3, false),
(5, 'Banana', 2.1, false),
(6, 'Cherry', 12.3, true),
(7, 'Strawberry', 8.7, false),
(8, 'Peach', 3.9, true),
(9, 'Tangerine', 4, true),
(10, 'Grape', 6.2, false);

INSERT INTO check_runner.discount_card
VALUES
(1111, 10),
(2222, 15),
(3333, 20),
(4444, 25),
(5555, 30);