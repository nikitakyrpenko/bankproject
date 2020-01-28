
INSERT INTO `roles` (`role_description`)
VALUES
("USER"),
("ADMIN");

INSERT INTO `charge_types`(`charge_description`)
VALUE
("DEPOSIT_ARRIVAL"),
("CREDIT_ARRIVAL");


INSERT INTO `accounts_type`(`type_description`)
VALUE
("DEPOSIT"),
("CREDIT");

INSERT INTO `users` (`firstname`,`surname`,`email`,`passwords`,`telephone`,`fk_roles_users`)
VALUES 
("Freya","Rodriguez","dolor.Donec@etmagnaPraesent.net","1","715-0987","1"),
("Fleur","Morgan","Vivamus.rhoncus.Donec@lacusEtiam.net","1","612-4806","1"),
("Kim","Butler","ante.iaculis@Donecnibhenim.ca","451-2416","1","1"),
("Amaya","Davis","Vivamus.euismod.urna@ligulaAeneangravida.edu","1","338-6234","1"),
("Kellie","Jackson","rutrum.eu.ultrices@ultricesposuerecubilia.net","1","1-310-474-8458","1"),
("Clinton","Mckay","erat.volutpat@nibhDonec.net","168-6165","1","1"),
("Wesley","Merritt","lobortis@sitametrisus.ca","136-2862","1","1"),
("Cameron","Beasley","tellus.Suspendisse@magnamalesuadavel.com","1","770-3791","1"),
("Blossom","Cole","quis@auctorvelit.org","219-8751","1","1"),
("Mollie","Macias","mauris.Morbi.non@arcu.org","1-856-583-3206","1","1");


INSERT INTO `operations` (`purpose`,`operation_date`,`transfer`) VALUES
 ("Aliquam erat volutpat.","2020-08-16 13:57:11",65305),
 ("ipsum leo","2020-11-22 16:19:32",19866),
 ("at, nisi.","2019-06-19 01:05:53",70987),
 ("eget metus. In","2019-08-12 22:10:12",57559),
 ("Vivamus molestie","2019-06-10 08:24:11",84986),
 ("sed","2020-10-23 04:56:20",98213),
 ("diam luctus lobortis.","2021-01-11 23:54:46",35457),
 ("nec, euismod in,","2019-05-31 01:49:21",64770),
 ("pretium","2020-03-12 12:12:36",3362),
 ("urna. Ut tincidunt","2020-11-07 23:19:28",45139),
 ("eu, ligula.","2020-03-17 13:44:01",42686),
 ("hendrerit","2021-01-03 16:07:56",32746),
 ("varius. Nam porttitor","2021-01-23 15:20:12",52567),
 ("ligula. Aenean","2019-07-28 09:51:20",49014),
 ("quis, pede.","2020-12-01 09:40:37",3977),
 ("a sollicitudin orci","2020-01-16 04:19:35",35720),
 ("iaculis","2020-03-03 12:06:32",66096),
 ("eu","2020-11-23 03:24:42",43977),
 ("tortor.","2019-02-12 07:28:52",80161),
 ("pellentesque,","2019-05-26 22:05:15",28397);


INSERT INTO `accounts` (`expiration_date`,`balance`,`deposit_account_rate`,`fk_users_accounts`,`fk_accounts_type_accounts`)
VALUES
("2020-09-06 13:48:20",88732,0.3,1,1),
("2020-05-13 14:10:42",55339,0.12,2,1),
("2019-07-19 16:00:38",21912,0.1,3,1),
("2019-08-19 21:35:21",18086,0.1,4,1),
("2019-03-27 15:27:37",22737,0.7,5,1),
("2019-04-01 05:36:09",12630,0.3,6,1),
("2020-03-13 10:27:05",10371,0.15,7,1);


INSERT INTO `accounts` (`expiration_date`,`balance`,`credit_limit`,`credit_rate`,`fk_users_accounts`,`fk_accounts_type_accounts`)
VALUES
("2020-09-06 13:48:20",88732,100000,0.12,8,2),
("2020-05-13 14:10:42",55339,100000,0.12,9,2),
("2019-07-19 16:00:38",21912,100000,0.12,10,2);



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


INSERT INTO `charges`
(`charge`,`fk_charge_types_charge`,`fk_account_charge`)
VALUES
(581,2,10),(967,1,1),(521,1,5),(660,1,1),(570,1,3),(933,1,4),(563,1,6),(303,2,8),(726,2,7),(444,2,9);
