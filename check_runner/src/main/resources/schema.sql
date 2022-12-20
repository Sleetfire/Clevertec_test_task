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