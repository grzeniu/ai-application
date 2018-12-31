CREATE TABLE CURRENCY (
    id integer primary key,
    from_currency varchar(4) NOT NULL,
    to_currency varchar(4) NOT NULL,
    rate decimal(6,3) default 0
);
CREATE TABLE CATEGORY (
    id integer primary key,
    name varchar(255) NOT NULL
);
INSERT INTO CURRENCY (id, to_currency,from_currency) values (1,'PLN','USD');
INSERT INTO CURRENCY (id, to_currency,from_currency) values (2,'USD','PLN');
INSERT INTO CURRENCY (id, to_currency,from_currency) values (3,'USD','GBP');
INSERT INTO CURRENCY (id, to_currency,from_currency) values (4,'GBP','USD');
INSERT INTO CURRENCY (id, to_currency,from_currency) values (5,'GBP','PLN');
INSERT INTO CURRENCY (id, to_currency,from_currency) values (6,'PLN','GBP');
INSERT INTO CATEGORY (id, name) values (1,'TODO');
