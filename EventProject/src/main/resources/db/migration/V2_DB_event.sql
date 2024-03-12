create table users
(
    id            serial primary key ,
    name          varchar(255),
    last_Name     varchar(255),
    phone_Number  varchar(255),
    email         varchar(255),
    date_Of_Birth date,
    pass varchar,
    id_event serial
);
drop table users;
create table event
(
    id                serial primary key,
    name_Event        varchar(255),
    day_Event         date,
    time_Event        time,
    price             int,
    lasting           int,
    address           varchar(255),
    description_Event varchar(255),
    age_Limit         int,
    id_category       int,
    quantity_place    int,
    image             varchar
);
create table category
(
    id            serial,
    name_category varchar(255)
);
drop table reservation;
create table reservation(
  id_user serial,
  id_event serial,
  foreign key (id_user) references users(id),
  foreign key (id_event) references event(id)
);
drop table users;
drop table event;
drop table category;

EXPLAIN
SELECT *
FROM event;
EXPLAIN
SELECT *
FROM event
WHERE id = '1';
ANALYZE event;
SELECT COUNT(*)
FROM category;

EXPLAIN
INSERT INTO event
VALUES (1, 'Name', '2023-06-12', '12:00:00', '150', '120', 'Address', 'Description', '18', '1', '100', 'Image');
EXPLAIN
UPDATE event
SET name_Event='NAME_UPDATE'
WHERE id = '1';
EXPLAIN
DELETE
FROM event
WHERE id = '1';

EXPLAIN
SELECT *
FROM users
WHERE id = '1';
EXPLAIN
INSERT INTO users
VALUES ('1', 'Name', 'Last_name', '0994567891', 'email@gmail.com', '2002-12-12', 'Pa$sWoRd');
EXPLAIN
UPDATE users
SET pass='PA$$WORD'
WHERE id = '1';

EXPLAIN
DELETE
FROM users
WHERE id = '1';

EXPLAIN
SELECT *
FROM category;
EXPLAIN
SELECT *
FROM category
WHERE id = '1';
EXPLAIN
INSERT INTO category
VALUES ('1', 'music');
EXPLAIN
UPDATE category
SET name_category='MUSIC'
WHERE id = '1';
EXPLAIN
DELETE
FROM category
WHERE id = '1';


CREATE INDEX idx_phone_number ON users (phone_Number);

CREATE INDEX idx_lasting ON event (lasting);

CREATE INDEX idx_name_category ON category (name_category);

SELECT column_name,
       data_type,
       character_maximum_length,
       is_nullable,
       column_default,
       ordinal_position,
       numeric_precision,
       numeric_scale
FROM information_schema.columns
WHERE table_name = 'users';

SELECT indexrelname AS index_name,
       idx_scan,
       idx_tup_read
FROM pg_stat_all_indexes
WHERE relname = 'category';

/*
 Trigger
 */
CREATE OR REPLACE FUNCTION check_price_event_trigger()
    RETURNS TRIGGER AS
$$
BEGIN
    IF NEW.price > 0 THEN
        RETURN NEW;
    ELSE
        RAISE EXCEPTION 'Ціна повинна бути додатньою.';
    END IF;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_price_trigger_event
    BEFORE INSERT
    ON event
    FOR EACH ROW
EXECUTE PROCEDURE check_price_event_trigger();


INSERT INTO event VALUES (2, 'Name', '2023-06-12', '12:00:00', '150', '120', 'Address', 'Description', '18', '1', '100', 'Image');

CREATE OR REPLACE FUNCTION check_pass_users()
    RETURNS TRIGGER AS
$$
BEGIN
    IF length(NEW.pass) > 5 THEN
        RETURN NEW;
    ELSE
        RAISE EXCEPTION 'Пароль повинен бути не менше 6 символів.';
    END IF;
END;
$$ LANGUAGE plpgsql;
CREATE TRIGGER check_pass_trigger
BEFORE INSERT
ON users
FOR EACH ROW
EXECUTE PROCEDURE check_pass_users();

INSERT INTO users VALUES ('2', 'Name', 'Last_name', '0994567891', 'email@gmail.com', '2002-12-12', '123456');
INSERT INTO category VALUES ('2', 'science');

/*
 Save procedure
 */

CREATE OR REPLACE FUNCTION get_all_event_save_function()
    RETURNS SETOF event AS $$
SELECT * FROM event;
$$ LANGUAGE SQL;

SELECT * FROM get_all_event_save_function();

CREATE OR REPLACE FUNCTION get_event_id_save_function()
    RETURNS SETOF event AS $$
SELECT*FROM event WHERE id='1';
$$ LANGUAGE SQL;

SELECT * FROM get_event_id_save_function();

CREATE OR REPLACE FUNCTION get_user_id_save_function()
    RETURNS SETOF users AS $$
SELECT*FROM users WHERE id='2';
$$ LANGUAGE SQL;

SELECT * FROM get_user_id_save_function();


CREATE OR REPLACE FUNCTION get_all_category_save_function()
    RETURNS SETOF category AS $$
SELECT * FROM category;
$$ LANGUAGE SQL;

SELECT * FROM get_all_category_save_function();

CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE OR REPLACE FUNCTION encrypt_phone_number(origin_phone_number text, secret_key text)
RETURNS varchar AS
    $$BEGIN
    RETURN encode(pgp_sym_encrypt(origin_phone_number, secret_key),'base64');
END;
$$ LANGUAGE  plpgsql;

INSERT INTO users(id, name, last_Name, phone_Number, email, date_Of_Birth, pass)
VALUES ('3', 'Name', 'Last_name',encrypt_phone_number('0994567891', '123'),
        'encryptuser@gmail.com', '2002-12-12', '123456');

CREATE OR REPLACE FUNCTION decrypt_phone_number(phone_Number varchar, secret_key text)
RETURNS text AS
    $$BEGIN
    RETURN pgp_sym_decrypt(decode(phone_Number, 'base64'), secret_key);
END;
$$ LANGUAGE plpgsql;

SELECT id, last_Name, decrypt_phone_number(phone_Number,'123')
    AS decrypted_phone_num FROM users;

/*
 ROLE
 */
CREATE ROLE client_read;
GRANT SELECT ON TABLE event TO client_read;
CREATE ROLE user_all_request;
GRANT INSERT,UPDATE,DELETE,SELECT ON TABLE event TO user_all_request;
CREATE ROLE user_only_insert;
GRANT INSERT, SELECT ON TABLE event TO user_only_insert;
SELECT
    grantee AS роль,
    table_name AS таблиця,
    privilege_type AS привілей
FROM
    information_schema.table_privileges
WHERE
        grantee = 'user_only_insert';


INSERT INTO event(id,name_Event, day_Event, time_Event, price, lasting, address, description_Event, age_Limit, id_category, quantity_place, image)
VALUES (333, 'Name', '2023-06-12', '12:00:00', '150', '120', 'Address', 'Description', '18', '1', '100', 'Image');
RESET ROLE;

SET ROLE user_only_insert;
INSERT INTO event(id,name_Event, day_Event, time_Event, price, lasting, address, description_Event, age_Limit, id_category, quantity_place, image)
VALUES (334, 'Name', '2023-06-12', '12:00:00', '150', '120', 'Address', 'Description', '18', '1', '100', 'Image');
SELECT * FROM event;






