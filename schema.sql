create table if not exists roles (
  role_id int(10) not null auto_increment, 
  role_role    varchar(255) not null, 
  primary key (role_id),
  unique index (role_id));
  
create table if not exists users (
  user_id   int(10) not null unique  auto_increment, 
  fk_role   int(10) not null, 
  user_name      varchar(255) not null, 
  user_surname   varchar(255) not null, 
  user_email     varchar(255) not null unique, 
  user_telephone varchar(255) not null, 
  primary key (user_id),
  unique index (user_id),
  foreign key (fk_role) REFERENCES roles(role_id)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION);
  
create table if not exists credit_accounts (
  credit_account_id int(10) not null auto_increment, 
  credit_account_limit      decimal(10, 0) not null, 
  credit_rate               decimal(5, 0) not null, 
  charge_per_month          decimal(10, 0) default 0, 
  credit_liabilities        decimal(10, 0) default 1, 
  primary key (credit_account_id), 
  unique index (credit_account_id));
  
create table if not exists deposit_accounts (
  deposit_account_id        int(10) not null auto_increment, 
  deposit_account_rate      decimal(10, 0) not null, 
  primary key (deposit_account_id), 
  unique index (deposit_account_id));
  
create table if not exists accounts (
  account_id                 int(10) not null auto_increment, 
  expiration_date            date not null, 
  balance                    decimal(10, 0) not null, 
  fk_user_account            int(10) not null, 
  fk_account_deposit_account int(10) DEFAULT -1, 
  fk_account_credit_account  int(10) DEFAULT -1, 
  primary key (account_id),
  unique index (account_id),
  foreign key (fk_user_account)  REFERENCES users(user_id)
  on update CASCADE
  on delete CASCADE  ,
  foreign key (fk_account_credit_account) REFERENCES credit_accounts(credit_account_id)
  on update CASCADE
  on delete CASCADE,
  foreign key (fk_account_deposit_account) REFERENCES deposit_accounts(deposit_account_id)
  on update CASCADE
  on delete CASCADE,
  unique (fk_account_credit_account, fk_account_deposit_account));
  
create table if not exists operations_type(
  operation_type_id int(10) not null auto_increment,
  operation_type_description varchar(255) not null,
  primary key(operation_type_id),
  unique index (operation_type_id));
  
create table if not exists operations(
   operation_id int(10) not null auto_increment,
   operation_purpose varchar(255) not null,
   operation_date DATE not null,
   operation_transfer decimal(10,0) not null,
   fk_operation_type int(10) not null,
   primary key(operation_id),
   unique index (operation_id),
   foreign key (fk_operation_type) REFERENCES operations_type(operation_type_id)
   on update NO ACTION
   on delete NO ACTION);
   
create table if not exists accounts_operations(
    accounts_operations_id int(10) not null auto_increment,
    fk_accounts_sender int(10) not null,
    fk_accounts_receiver int(10) not null,
    fk_operations_operations_id int(10) not null,
    primary key(accounts_operations_id),
    unique index (accounts_operations_id),
    foreign key(fk_operations_operations_id) REFERENCES operations(operation_id)
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    foreign key (fk_accounts_sender) REFERENCES accounts(account_id)
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    foreign key (fk_accounts_receiver) REFERENCES accounts(account_id)
    ON UPDATE NO ACTION
    ON DELETE NO ACTION);