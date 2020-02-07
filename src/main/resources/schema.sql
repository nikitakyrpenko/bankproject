DROP TABLE IF EXISTS accounts_type;
CREATE TABLE accounts_type
(
    account_type_id  int         NOT NULL AUTO_INCREMENT,
    type_description varchar(20) NOT NULL,
    PRIMARY KEY (account_type_id),
    UNIQUE KEY account_type_id (account_type_id)
);

INSERT INTO accounts_type (type_description)
VALUES ('DEPOSIT'),
       ('CREDIT');

DROP TABLE IF EXISTS roles;
CREATE TABLE roles
(
    role_id          int          NOT NULL AUTO_INCREMENT,
    role_description varchar(255) NOT NULL,
    PRIMARY KEY (role_id),
    UNIQUE KEY role_id (role_id)
);

INSERT INTO roles (role_description)
VALUES ('USER'),
       ('ADMIN');

DROP TABLE IF EXISTS charge_types;

CREATE TABLE charge_types
(
    charge_type_id     int         NOT NULL AUTO_INCREMENT,
    charge_description varchar(20) NOT NULL,
    PRIMARY KEY (charge_type_id),
    UNIQUE KEY charge_type_id (charge_type_id)
);

INSERT INTO charge_types (charge_description)
VALUES ('DEPOSIT_ARRIVAL'),
       ('CREDIT_ARRIVAL');

DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    user_id        int          NOT NULL AUTO_INCREMENT,
    firstname      varchar(15)  NOT NULL,
    surname        varchar(15)  NOT NULL,
    email          varchar(50)  NOT NULL,
    passwords      varchar(100) NOT NULL,
    telephone      varchar(15)  NOT NULL,
    fk_roles_users int          NOT NULL,
    PRIMARY KEY (user_id),
    UNIQUE KEY id (user_id),
    UNIQUE KEY email (email),
    UNIQUE KEY id_2 (user_id),
    FOREIGN KEY (fk_roles_users) REFERENCES roles (role_id)
);



INSERT INTO users
VALUES (1, 'Freya', 'Rodriguez', 'dolor.Donec@etmagnaPraesent.net', '1', '715-0987', 1),
       (2, 'Fleur', 'Morgan', 'Vivamus.rhoncus.Donec@lacusEtiam.net', '1', '612-4806', 1),
       (3, 'Kim', 'Butler', 'ante.iaculis@Donecnibhenim.ca', '451-2416', '1', 1),
       (4, 'Amaya', 'Davis', 'Vivamus.euismod.urna@ligulaAeneangravida.edu', '1', '338-6234', 1),
       (5, 'Kellie', 'Jackson', 'rutrum.eu.ultrices@ultricesposuerecubilia.net', '1', '1-310-474-8458', 1),
       (6, 'Clinton', 'Mckay', 'erat.volutpat@nibhDonec.net', '168-6165', '1', 1),
       (7, 'Wesley', 'Merritt', 'lobortis@sitametrisus.ca', '136-2862', '1', 1),
       (8, 'Cameron', 'Beasley', 'tellus.Suspendisse@magnamalesuadavel.com', '1', '770-3791', 1),
       (9, 'Blossom', 'Cole', 'quis@auctorvelit.org', '219-8751', '1', 1),
       (10, 'Mollie', 'Macias', 'mauris.Morbi.non@arcu.org', '1-856-583-3206', '1', 1),
       (11, 'Mykyta', 'Kyrpenko', 'nikitakyrpenko@gmail.com', '12134r56', '380508321899', 1);


DROP TABLE IF EXISTS accounts;
CREATE TABLE accounts
(
    account_id                int    NOT NULL AUTO_INCREMENT,
    expiration_date           date   NOT NULL,
    balance                   double NOT NULL,
    deposit_account_rate      double DEFAULT '0',
    credit_limit              double DEFAULT '0',
    credit_rate               double DEFAULT '0',
    charge_per_month          double DEFAULT '0',
    credit_liabilities        double DEFAULT '0',
    fk_users_accounts         int    NOT NULL,
    fk_accounts_type_accounts int    NOT NULL,
    PRIMARY KEY (account_id),
    UNIQUE KEY account_id (account_id),
    FOREIGN KEY (fk_users_accounts) REFERENCES users (user_id),
    FOREIGN KEY (fk_accounts_type_accounts) REFERENCES accounts_type (account_type_id)
);

INSERT INTO accounts
VALUES (1, '2020-09-06', 88732, 0.3, 0, 0, 0, 0, 1, 1),
       (2, '2020-05-13', 55339, 0.12, 0, 0, 0, 0, 2, 1),
       (3, '2019-07-19', 21912, 0.1, 0, 0, 0, 0, 3, 1),
       (4, '2019-08-19', 18086, 0.1, 0, 0, 0, 0, 4, 1),
       (5, '2019-03-27', 22737, 0.7, 0, 0, 0, 0, 5, 1),
       (6, '2019-04-01', 12630, 0.3, 0, 0, 0, 0, 6, 1),
       (7, '2020-03-13', 10371, 0.15, 0, 0, 0, 0, 7, 1),
       (8, '2020-09-06', 88732, 0, 100000, 0.12, 0, 0, 8, 2),
       (9, '2020-05-13', 55339, 0, 100000, 0.12, 0, 0, 9, 2),
       (10, '2019-07-19', 21912, 0, 100000, 0.12, 0, 0, 10, 2),
       (11, '2020-01-29', 32212, 0.322, 0, 0, 0, 0, 11, 1),
       (12, '2020-01-29', 32212, 0, 32212, 0.148, 1113.487272660212, 300, 11, 2);



DROP TABLE IF EXISTS operationEntities;

CREATE TABLE operationEntities
(
    operation_id   int          NOT NULL AUTO_INCREMENT,
    purpose        varchar(255) NOT NULL,
    operation_date date         NOT NULL,
    transfer       double       NOT NULL,
    PRIMARY KEY (operation_id),
    UNIQUE KEY operation_id (operation_id)
);


INSERT INTO operationEntities
VALUES (1, 'Aliquam erat volutpat.', '2020-08-16', 65305),
       (2, 'ipsum leo', '2020-11-22', 19866),
       (3, 'at, nisi.', '2019-06-19', 70987),
       (4, 'eget metus. In', '2019-08-12', 57559),
       (5, 'Vivamus molestie', '2019-06-10', 84986),
       (6, 'sed', '2020-10-23', 98213),
       (7, 'diam luctus lobortis.', '2021-01-11', 35457),
       (8, 'nec, euismod in,', '2019-05-31', 64770),
       (9, 'pretium', '2020-03-12', 3362),
       (10, 'urna. Ut tincidunt', '2020-11-07', 45139),
       (11, 'eu, ligula.', '2020-03-17', 42686),
       (12, 'hendrerit', '2021-01-03', 32746),
       (13, 'varius. Nam porttitor', '2021-01-23', 52567),
       (14, 'ligula. Aenean', '2019-07-28', 49014),
       (15, 'quis, pede.', '2020-12-01', 3977),
       (16, 'a sollicitudin orci', '2020-01-16', 35720),
       (17, 'iaculis', '2020-03-03', 66096),
       (18, 'eu', '2020-11-23', 43977),
       (19, 'tortor.', '2019-02-12', 80161),
       (20, 'pellentesque,', '2019-05-26', 28397);



DROP TABLE IF EXISTS accounts_operations;

CREATE TABLE accounts_operations
(
    account_operation_id        int NOT NULL AUTO_INCREMENT,
    fk_accounts_sender          int NOT NULL,
    fk_accounts_receiver        int NOT NULL,
    fk_operations_operations_id int NOT NULL,
    PRIMARY KEY (account_operation_id),
    UNIQUE KEY account_operation_id (account_operation_id),
    FOREIGN KEY (fk_operations_operations_id) REFERENCES operationEntities (operation_id),
    FOREIGN KEY (fk_accounts_sender) REFERENCES accounts (account_id),
    FOREIGN KEY (fk_accounts_receiver) REFERENCES accounts (account_id)
);



INSERT INTO accounts_operations
VALUES (1, 1, 2, 1),
       (2, 3, 1, 2),
       (3, 1, 3, 3),
       (4, 2, 4, 4),
       (5, 4, 1, 5),
       (6, 7, 9, 6),
       (7, 8, 3, 7),
       (8, 10, 1, 8),
       (9, 4, 9, 9),
       (10, 7, 5, 10),
       (11, 5, 9, 11),
       (12, 3, 4, 12),
       (13, 1, 2, 13),
       (14, 6, 5, 14),
       (15, 8, 9, 15),
       (16, 9, 1, 16),
       (17, 3, 9, 17),
       (18, 4, 2, 18),
       (19, 1, 10, 19),
       (20, 9, 2, 20);



DROP TABLE IF EXISTS @;

CREATE TABLE charges
(
    charge_id              int    NOT NULL AUTO_INCREMENT,
    chargeEntity                 double NOT NULL,
    fk_charge_types_charge int    NOT NULL,
    fk_account_charge      int    NOT NULL,
    PRIMARY KEY (charge_id),
    UNIQUE KEY charge_id (charge_id),
    FOREIGN KEY (fk_charge_types_charge) REFERENCES charge_types (charge_type_id),
    FOREIGN KEY (fk_account_charge) REFERENCES accounts (account_id)
);


INSERT INTO charges
VALUES (1, 581, 2, 10),
       (2, 967, 1, 1),
       (3, 521, 1, 5),
       (4, 660, 1, 1),
       (5, 570, 1, 3),
       (6, 933, 1, 4),
       (7, 563, 1, 6),
       (8, 303, 2, 8),
       (9, 726, 1, 7),
       (10, 444, 2, 9);
