
CREATE KEYSPACE IF NOT EXISTS car_management
    WITH REPLICATION = {
        'class': 'SimpleStrategy',
        'replication_factor': 1
    };

USE car_management;

CREATE TABLE car (
    car_id uuid,
    year int,
    make text,
    model text,
    price decimal,
    PRIMARY KEY (car_id)
    );

CREATE INDEX on car(year);

-- Sample Data 10 Cars
INSERT INTO car (car_id, year, make, model, price)
VALUES (8557f426-1cc9-41bf-a581-fadb101cd289, 2011, 'BMW', '335D', 29450.00);

INSERT INTO car (car_id, year, make, model, price)
VALUES (75a474ff-68d2-41d4-864b-8b8ae0ba87a5, 2017, 'Honda', 'Civic', 22950.00);

INSERT INTO car (car_id, year, make, model, price)
VALUES (79e283da-7ae7-4c66-9600-48a8f73d552e, 2014, 'Infiniti', 'QX60', 52500.00);

INSERT INTO car (car_id, year, make, model, price)
VALUES (850251db-5d0e-4b95-ac25-1222351de627, 2014, 'BMW', 'X5', 55150.00);

INSERT INTO car (car_id, year, make, model, price)
VALUES (2130d7ea-518a-4e31-af43-6b8aacab3469, 2016, 'Acura', 'MDX', 38750.00);

INSERT INTO car (car_id, year, make, model, price)
VALUES (10e37334-58ca-4d3a-ac65-2bfcf9e440f9, 2007, 'Cheverolet', 'Corvette', 59800.00);

INSERT INTO car (car_id, year, make, model, price)
VALUES (c55ab657-7747-44ec-9f5b-c0ae77023b2d, 2018, 'Mini', 'Cooper', 28800.00);

INSERT INTO car (car_id, year, make, model, price)
VALUES (63e60e7d-590c-4a11-a4e3-c1934a6ad60c, 2015, 'Lexus', 'IS350', 28456.00);

INSERT INTO car (car_id, year, make, model, price)
VALUES (b0c482c1-68bc-4b4a-b771-298b54c592c6, 2012, 'Nissan', '370Z', 18460.00);

INSERT INTO car (car_id, year, make, model, price)
VALUES (c0c482c1-68bc-4b4a-b771-298b54c592c6, 2009, 'Dodge', 'Viper', 36570.00);