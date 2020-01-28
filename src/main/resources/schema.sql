DROP TABLE IF EXISTS `roles`;
create table if not exists roles (
  id                  int(10) not null auto_increment,
  role_description    varchar(255) not null,
  primary key (id),
  unique index (id));

DROP TABLE IF EXISTS `users`;
create table if not exists users (
  id               int(10)     not null unique  auto_increment,
  firstname        varchar(15) not null,
  surname          varchar(15) not null,
  email            varchar(50) not null unique,
  passwords        varchar(100)not null,
  telephone        varchar(15) not null,
  fk_roles_users   int(10)     not null,
  primary key (id),
  unique index (id),
  foreign key (fk_roles_users) REFERENCES roles(id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION);


 DROP TABLE IF EXISTS `account_types`;
 create table if not exists accounts_type(
	id               int(10) not null auto_increment,
    type_description varchar(20) not null,
    primary key (id),
    unique index (id)
 );

     DROP TABLE IF EXISTS `accounts`;
 create table if not exists accounts (
  id                        int(10) not null auto_increment,
  expiration_date           date not null,
  balance                   double not null,
  deposit_account_rate      double default 0.0,
  credit_limit      		double default 0.0,
  credit_rate               double default 0.0,
  charge_per_month          double default 0.0,
  credit_liabilities        double default 0.0,
  fk_users_accounts         int(10) not null,
  fk_accounts_type_accounts int(10) not null,
  primary key (id),
  unique index (id),
  foreign key (fk_users_accounts)  REFERENCES users(id)
  on update NO ACTION
  on delete NO ACTION,
  foreign key (fk_accounts_type_accounts)  REFERENCES accounts_type(id)
  on update NO ACTION
  on delete NO ACTION);

 DROP TABLE IF EXISTS `charge_types`;
 CREATE TABLE IF NOT EXISTS charge_types(
	id                 int(10) not null auto_increment,
	charge_description varchar(20) not null,
	primary key (id),
    unique index (id)
 );


 DROP TABLE IF EXISTS `charges`;
 CREATE TABLE IF NOT EXISTS charges(
	id                     int(10) not null auto_increment,
	charge                 double not null,
	fk_charge_types_charge int(10) not null,
	fk_account_charge      int(10) not null,
	PRIMARY KEY (id),
    UNIQUE  INDEX (id),
	foreign key (fk_charge_types_charge)  REFERENCES charge_types(id)
	on update NO ACTION
    on delete NO ACTION,
	foreign key (fk_account_charge)  REFERENCES accounts(id)
	on update NO ACTION
    on delete NO ACTION);


DROP TABLE IF EXISTS `operations`;
create table if not exists operations(
   id                            int(10) not null auto_increment,
   purpose                       varchar(255) not null,
   operation_date                DATE not null,
   transfer                      double not null,
   primary key(id),
   unique index (id));


DROP TABLE IF EXISTS `accounts_operations`;
create table if not exists accounts_operations(
    id                          int(10) not null auto_increment,
    fk_accounts_sender          int(10) not null,
    fk_accounts_receiver        int(10) not null,
    fk_operations_operations_id int(10) not null,
    primary key(id),
    unique index (id),
    foreign key(fk_operations_operations_id) REFERENCES operations(id)
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    foreign key (fk_accounts_sender) REFERENCES accounts(id)
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    foreign key (fk_accounts_receiver) REFERENCES accounts(id)
    ON UPDATE NO ACTION
    ON DELETE NO ACTION);

