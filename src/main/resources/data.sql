
INSERT INTO `roles` (`role_role`) VALUES ("USER");
INSERT INTO `roles` (`role_role`) VALUES ("ADMIN");


INSERT INTO `users` (`user_name`,`user_surname`,`user_email`,`user_telephone`,`fk_role`) 
VALUES 
("Freya","Rodriguez","dolor.Donec@etmagnaPraesent.net","715-0987","1"),
("Fleur","Morgan","Vivamus.rhoncus.Donec@lacusEtiam.net","612-4806","2"),
("Kim","Butler","ante.iaculis@Donecnibhenim.ca","451-2416","1"),
("Amaya","Davis","Vivamus.euismod.urna@ligulaAeneangravida.edu","338-6234","2"),
("Kellie","Jackson","rutrum.eu.ultrices@ultricesposuerecubilia.net","1-310-474-8458","1"),
("Clinton","Mckay","erat.volutpat@nibhDonec.net","168-6165","2"),
("Wesley","Merritt","lobortis@sitametrisus.ca","136-2862","1"),
("Cameron","Beasley","tellus.Suspendisse@magnamalesuadavel.com","770-3791","2"),
("Blossom","Cole","quis@auctorvelit.org","219-8751","1"),
("Mollie","Macias","mauris.Morbi.non@arcu.org","1-856-583-3206","2");

INSERT INTO `deposit_accounts` (`deposit_account_rate`) VALUES 
(3),
(14),
(15),
(6),
(14),
(16),
(20),
(12),
(18),
(14);


INSERT INTO `credit_accounts` (`credit_account_limit`,`credit_rate`) VALUES 
(7156,12),
(2955,2),
(4422,11);


INSERT INTO `accounts` (`expiration_date`,`balance`,`fk_user_account`,`fk_account_deposit_account`,`fk_account_credit_account`) 
VALUES 
("2020-12-28 20:43:41",1788,1,1,null),
("2020-06-01 09:17:23",64959,2,2,null),
("2019-10-25 05:24:21",89242,3,null,1),
("2019-03-13 20:32:11",3797,4,null,2),
("2020-06-01 09:50:35",80925,5,5,null),
("2019-04-03 20:35:57",66227,6,6,null),
("2019-09-04 21:42:47",15487,7,7,null),
("2019-10-30 03:14:42",66663,8,8,null),
("2020-05-18 10:59:51",75516,9,9,null),
("2020-10-28 22:12:15",77593,10,null,3);

INSERT INTO `operations_type` (`operation_type_description`) VALUES 
("WITHDRAWAL"),
("ARRIVAL"),
("DEPOSIT_ARRIVAL"),
("CREDIT_ARRIVAL");

INSERT INTO `operations` (`operation_purpose`,`operation_date`,`operation_transfer`,`fk_operation_type`) VALUES 
 ("Aliquam erat volutpat.","2020-08-16 13:57:11",65305,4),
 ("ipsum leo","2020-11-22 16:19:32",19866,2),
 ("at, nisi.","2019-06-19 01:05:53",70987,2),
 ("eget metus. In","2019-08-12 22:10:12",57559,2),
 ("Vivamus molestie","2019-06-10 08:24:11",84986,3),
 ("sed","2020-10-23 04:56:20",98213,4),
 ("diam luctus lobortis.","2021-01-11 23:54:46",35457,1),
 ("nec, euismod in,","2019-05-31 01:49:21",64770,1),
 ("pretium","2020-03-12 12:12:36",3362,3),
 ("urna. Ut tincidunt","2020-11-07 23:19:28",45139,3),
 ("eu, ligula.","2020-03-17 13:44:01",42686,3),
 ("hendrerit","2021-01-03 16:07:56",32746,4),
 ("varius. Nam porttitor","2021-01-23 15:20:12",52567,2),
 ("ligula. Aenean","2019-07-28 09:51:20",49014,3),
 ("quis, pede.","2020-12-01 09:40:37",3977,4),
 ("a sollicitudin orci","2020-01-16 04:19:35",35720,2),
 ("iaculis","2020-03-03 12:06:32",66096,3),
 ("eu","2020-11-23 03:24:42",43977,1),
 ("tortor.","2019-02-12 07:28:52",80161,2),
 ("pellentesque,","2019-05-26 22:05:15",28397,3);


INSERT INTO `bank_database`.`accounts_operations` (`fk_accounts_sender`, `fk_accounts_receiver`, `fk_operations_operations_id`) VALUES ('1', '2', '1');
INSERT INTO `bank_database`.`accounts_operations` (`fk_accounts_sender`, `fk_accounts_receiver`, `fk_operations_operations_id`) VALUES ('3', '1', '2');
INSERT INTO `bank_database`.`accounts_operations` (`fk_accounts_sender`, `fk_accounts_receiver`, `fk_operations_operations_id`) VALUES ('1', '3', '3');
INSERT INTO `bank_database`.`accounts_operations` (`fk_accounts_sender`, `fk_accounts_receiver`, `fk_operations_operations_id`) VALUES ('2', '4', '4');
INSERT INTO `bank_database`.`accounts_operations` (`fk_accounts_sender`, `fk_accounts_receiver`, `fk_operations_operations_id`) VALUES ('4', '1', '5');
INSERT INTO `bank_database`.`accounts_operations` (`fk_accounts_sender`, `fk_accounts_receiver`, `fk_operations_operations_id`) VALUES ('7', '9', '6');
INSERT INTO `bank_database`.`accounts_operations` (`fk_accounts_sender`, `fk_accounts_receiver`, `fk_operations_operations_id`) VALUES ('8', '3', '7');
INSERT INTO `bank_database`.`accounts_operations` (`fk_accounts_sender`, `fk_accounts_receiver`, `fk_operations_operations_id`) VALUES ('10', '1', '8');
INSERT INTO `bank_database`.`accounts_operations` (`fk_accounts_sender`, `fk_accounts_receiver`, `fk_operations_operations_id`) VALUES ('4', '9', '9');
INSERT INTO `bank_database`.`accounts_operations` (`fk_accounts_sender`, `fk_accounts_receiver`, `fk_operations_operations_id`) VALUES ('7', '5', '10');
INSERT INTO `bank_database`.`accounts_operations` (`fk_accounts_sender`, `fk_accounts_receiver`, `fk_operations_operations_id`) VALUES ('5', '9', '11');
INSERT INTO `bank_database`.`accounts_operations` (`fk_accounts_sender`, `fk_accounts_receiver`, `fk_operations_operations_id`) VALUES ('3', '4', '12');
INSERT INTO `bank_database`.`accounts_operations` (`fk_accounts_sender`, `fk_accounts_receiver`, `fk_operations_operations_id`) VALUES ('1', '2', '13');
INSERT INTO `bank_database`.`accounts_operations` (`fk_accounts_sender`, `fk_accounts_receiver`, `fk_operations_operations_id`) VALUES ('6', '5', '14');
INSERT INTO `bank_database`.`accounts_operations` (`fk_accounts_sender`, `fk_accounts_receiver`, `fk_operations_operations_id`) VALUES ('8', '9', '15');
INSERT INTO `bank_database`.`accounts_operations` (`fk_accounts_sender`, `fk_accounts_receiver`, `fk_operations_operations_id`) VALUES ('9', '1', '16');
INSERT INTO `bank_database`.`accounts_operations` (`fk_accounts_sender`, `fk_accounts_receiver`, `fk_operations_operations_id`) VALUES ('3', '9', '17');
INSERT INTO `bank_database`.`accounts_operations` (`fk_accounts_sender`, `fk_accounts_receiver`, `fk_operations_operations_id`) VALUES ('4', '2', '18');
INSERT INTO `bank_database`.`accounts_operations` (`fk_accounts_sender`, `fk_accounts_receiver`, `fk_operations_operations_id`) VALUES ('1', '10', '19');
INSERT INTO `bank_database`.`accounts_operations` (`fk_accounts_sender`, `fk_accounts_receiver`, `fk_operations_operations_id`) VALUES ('9', '2', '20');
