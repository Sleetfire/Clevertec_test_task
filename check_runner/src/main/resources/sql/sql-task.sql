--Вывести к каждому самолету класс обслуживания и количество мест этого класса
SELECT model -> 'ru' AS model, aircraft_code, fare_conditions, COUNT(seat_no) AS seats_count
FROM aircrafts_data
         JOIN seats USING (aircraft_code)
GROUP BY model, aircraft_code, fare_conditions
ORDER BY model;

--Найти 3 самых вместительных самолета (модель + кол-во мест)
SELECT model, SUM(query_1.seats_count) AS seats_sum
FROM (SELECT model -> 'ru' AS model, aircraft_code, fare_conditions, COUNT(seat_no) AS seats_count
      FROM aircrafts_data
               JOIN seats USING (aircraft_code)
      GROUP BY model, aircraft_code, fare_conditions
      ORDER BY model) query_1
GROUP BY model
ORDER BY seats_sum DESC
    LIMIT 3;

--Вывести код,модель самолета и места не эконом класса для самолета 'Аэробус A321-200' с сортировкой по местам
SELECT aircraft_code, model -> 'ru' AS model, seat_no
FROM aircrafts_data
         JOIN seats USING (aircraft_code)
WHERE model ->> 'ru' = 'Аэробус A321-200'
  and fare_conditions != 'Economy'
ORDER BY seat_no;

--Вывести города в которых больше 1 аэропорта (код аэропорта, аэропорт, город)
SELECT airport_code, airport_name -> 'ru' AS airport, city -> 'ru' AS city
FROM airports_data
WHERE city -> 'ru' IN (SELECT city -> 'ru' AS city
                       FROM airports_data
                       GROUP BY city
                       HAVING COUNT(airport_code) > 1);

-- Найти ближайший вылетающий рейс из Екатеринбурга в Москву, на который еще не завершилась регистрация
SELECT flight_id, flight_no, scheduled_departure, scheduled_arrival, departure_airport, arrival_airport, status,
       aircraft_code, actual_departure, actual_arrival
FROM bookings.flights
WHERE departure_airport IN (SELECT airport_code
                            FROM bookings.airports_data
                            WHERE city ->> 'ru' = 'Екатеринбург')
  AND arrival_airport IN (SELECT airport_code
FROM bookings.airports_data
WHERE city ->> 'ru' = 'Москва')
  AND status IN ('On Time', 'Delayed')
ORDER BY scheduled_departure ASC
    LIMIT 1;

--Вывести самый дешевый и дорогой билет и стоимость ( в одном результирующем ответе)
(SELECT ticket_no,
        flight_id,
        fare_conditions,
        amount
 FROM bookings.ticket_flights
 WHERE amount = (
     SELECT MAX(amount)
     FROM bookings.ticket_flights
 )
     LIMIT 1)
UNION
(SELECT ticket_no,
        flight_id,
        fare_conditions,
        amount
 FROM bookings.ticket_flights
 WHERE amount = (
     SELECT MIN(amount)
     FROM bookings.ticket_flights
 )
     LIMIT 1);

-- Написать DDL таблицы Customers , должны быть поля id , firstName, LastName, email, phone. Добавить ограничения на поля ( constraints) .
CREATE SCHEMA IF NOT EXISTS customers_schema;

CREATE TABLE IF NOT EXISTS customers_schema.customers
(
    customer_id bigserial PRIMARY KEY,
    firstName   character varying(20) NOT NULL,
    lastName    character varying(20) NOT NULL,
    email       character varying(20) NOT NULL UNIQUE CHECK (email LIKE '%_@__%.__%'),
    phone       character varying(13) NOT NULL UNIQUE
    );

-- Написать DDL таблицы Orders , должен быть id, customerId, quantity. Должен быть внешний ключ на таблицу customers + ограничения
CREATE TABLE IF NOT EXISTS customers_schema.orders
(
    order_id    bigserial PRIMARY KEY,
    customer_id bigint NOT NULL,
    quantity    bigint,
    CONSTRAINT customers_fk FOREIGN KEY (customer_id) REFERENCES customers_schema.customers (customer_id)
    );

-- Написать 5 insert в эти таблицы
INSERT INTO customers_schema.customers(firstName, lastName, email, phone)
VALUES ('ALEKSANDR', 'KUZNECOV', 'kuznecov@mail.ru', '+375291122033'),
       ('ANTON', 'BONDARENKO', 'bondarenko@gmail.com', '+375447788055'),
       ('TATYANA', 'MAKSIMOVA', 'maksimova@yandex.by', '+375290102099'),
       ('MARIYA', 'KONOVALOVA', 'konovalova@mail.ru', '+375445554433'),
       ('VLADIMIR', 'MIRONOV', 'mironov@gmail.com', '+375292221105');

INSERT INTO customers_schema.orders(customer_id, quantity)
VALUES (1, 42),
       (2, 10),
       (3, 99),
       (4, 7),
       (5, 100000);

-- удалить таблицы
DROP TABLE IF EXISTS customers_schema.orders;
DROP TABLE IF EXISTS customers_schema.customers;
DROP SCHEMA IF EXISTS customers_schema;

-- Написать свой кастомный запрос ( rus + sql)

--Найти покупателей с почтой gmail, у которых суммарное количество товаров превышает 100 штук.
SELECT firstName, lastName, email, phone, SUM(quantity) AS all_quantity
FROM customers_schema.customers
         JOIN customers_schema.orders USING (customer_id)
GROUP BY firstName, lastName, email, phone
HAVING email LIKE '%_@gmail.com%' AND SUM(quantity) > 100;

--Вывести модели самолетов, имена пассажиров и места, занимаемые пассажирами для самолетов, которые находятся в воздухе между
--Москвой и Петрозаводском.
SELECT aircrafts.model, tickets.passenger_name, boarding_passes.seat_no
FROM flights
         JOIN aircrafts ON flights.aircraft_code = aircrafts.aircraft_code
         JOIN ticket_flights ON flights.flight_id = ticket_flights.flight_id
         JOIN tickets ON tickets.ticket_no = ticket_flights.ticket_no
         JOIN boarding_passes ON ticket_flights.ticket_no = boarding_passes.ticket_no
WHERE departure_airport IN (SELECT airports_data.airport_code
                            FROM bookings.airports_data
                            WHERE airports_data.city ->> 'ru' = 'Москва')
  AND arrival_airport IN (SELECT airports_data.airport_code
FROM bookings.airports_data
WHERE airports_data.city ->> 'ru' = 'Петрозаводск')
  AND status IN ('Departed');