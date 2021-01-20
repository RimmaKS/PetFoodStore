CREATE DATABASE zooproject CHARACTER SET utf8;
CREATE USER 'zooadmin'@'localhost' IDENTIFIED BY '2020epam';
GRANT ALL PRIVILEGES ON zooproject.* TO 'zooadmin'@'localhost';
USE zooproject;


CREATE TABLE `zooproject`.`user` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`firstName` VARCHAR(50) NULL DEFAULT NULL,
`lastName` VARCHAR(50) NULL DEFAULT NULL,
`mobile` VARCHAR(15) NOT NULL,
`email` VARCHAR(50) NOT NULL,
`passwordHash` VARCHAR(32) NOT NULL,
`isAdmin` TINYINT(1) NOT NULL DEFAULT 0,
`isActive` TINYINT(1) NOT NULL DEFAULT 1,
`registeredAt` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (`id`),
UNIQUE INDEX `uq_mobile` (`mobile` ASC),
UNIQUE INDEX `uq_email` (`email` ASC) );


INSERT INTO `zooproject`.`user` (firstName,lastName, mobile, email, passwordHash, isAdmin, isActive, registeredAt) 
values 
('Doe', 'Jane', '+77779999999', 'janedoe@gmail.com', 'dffa4096ab53ab7b410bdc5327c91a9a', 0, 1, current_date());

INSERT INTO `zooproject`.`user` (firstName,lastName, mobile, email, passwordHash, isAdmin, isActive, registeredAt) 
values 
('Doe', 'John', '+77779999900', 'admin@gmail.com', 'dffa4096ab53ab7b410bdc5327c91a9a', 1, 1, current_date());

INSERT INTO `zooproject`.`user` (firstName,lastName, mobile, email, passwordHash, isAdmin, isActive, registeredAt) 
values 
('Иванов', 'Иван', '+77779999978', 'ivan@gmail.com', 'dffa4096ab53ab7b410bdc5327c91a9a', 0, 1, current_date());


CREATE TABLE `zooproject`.`user_address` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`user` BIGINT NOT NULL DEFAULT 0,
`country` VARCHAR(100) NULL DEFAULT "Unknown",
`region` VARCHAR(100) NULL DEFAULT "Unknown",
`city` VARCHAR(100) NULL DEFAULT "Unknown",
`street` VARCHAR(100) NULL DEFAULT "Unknown",
`building` VARCHAR(100) NULL DEFAULT "Unknown",
`apartment` VARCHAR(100) NULL DEFAULT "Unknown",
`zip_code` VARCHAR(100) NULL DEFAULT "Unknown",
UNIQUE INDEX `uq_user` (`user` ASC),
PRIMARY KEY (`id`),
FOREIGN KEY (`user`)
REFERENCES `zooproject`.`user` (`id`)
   ON DELETE NO ACTION ON UPDATE NO ACTION
);


CREATE TABLE `zooproject`.`weight` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`weight` DOUBLE NOT NULL,
`measure` VARCHAR(15) NOT NULL,
PRIMARY KEY (`id`));

INSERT INTO `zooproject`.`weight` (weight, measure) 
values 
(2.5, 'lb'), (3, 'lb'), (5.5, 'lb'), (6, 'lb'), (7, 'lb'), (14, 'lb'), (15, 'lb'), (3, 'oz'), (5.8, 'oz'), (12, 'oz'), (0.4, 'kg'), (1.5, 'kg'), (10, 'lb'), (17, 'lb'), (30, 'lb');


CREATE TABLE `zooproject`.`brand` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`brand` VARCHAR(15) NOT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `uq_brand` (`brand` ASC) );

INSERT INTO `zooproject`.`brand` (brand) 
values 
('No brand'),('Royal Canin'),('Pro Plan'),('Monge');


CREATE TABLE `zooproject`.`form` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`form` VARCHAR(15) NOT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `uq_form` (`form` ASC) );

INSERT INTO `zooproject`.`form` (form) 
values 
('dry'),('wet');


CREATE TABLE `zooproject`.`age_rate` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`age_rate` VARCHAR(15) NOT NULL,
`description` VARCHAR(30),
PRIMARY KEY (`id`),
UNIQUE INDEX `uq_age` (`age_rate` ASC) );

INSERT INTO `zooproject`.`age_rate` (age_rate,description) 
values 
('mature','7-12 years'),('senior','12 years +'),('kitten','0-1 year'),('adult','1-7 years');


CREATE TABLE `zooproject`.`type_of_animal` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`type` VARCHAR(50) NOT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `uq_type` (`type` ASC) );

INSERT INTO `zooproject`.`type_of_animal` (type) 
values 
('any'),('cat'),('dog');

CREATE TABLE `zooproject`.`breed` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`breed` VARCHAR(50) NOT NULL,
`type` BIGINT NOT NULL DEFAULT 1,
PRIMARY KEY (`id`),
UNIQUE INDEX `uq_breed` (`breed` ASC),
FOREIGN KEY (`type`) REFERENCES `zooproject`.`type_of_animal`(`id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION); 

INSERT INTO `zooproject`.`breed` (breed, type) 
values 
('All', 2),('Abyssinian', 2),('Bengal', 2),('Burmese', 2),('Burmilla', 2),('Egyptian Mau', 2),('German Rex', 2),('Havana', 2),('Oriental cat', 2),('Persian', 2),('Siamese', 2),('Maine Coon', 2),('Norwegian Forest Cat', 2),('Somali', 2),('Turkish Angora', 2),('Turkish Van', 2),('Siberian', 2), ('Golden Retriever', 3),('Chihuahua', 3),('Dachshund', 3),('Poodle', 3),('Yorkshire Terrier', 3);


CREATE TABLE `zooproject`.`product` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`title` VARCHAR(75) NOT NULL,
`brand` BIGINT NOT NULL,
`form` BIGINT NOT NULL DEFAULT 0,
`breed` BIGINT NOT NULL DEFAULT 0,
`age` BIGINT NOT NULL DEFAULT 0,
`weight` BIGINT NOT NULL DEFAULT 0,
`type_of_animal` BIGINT NOT NULL DEFAULT 0,
`summary` TINYTEXT NULL,
`price` FLOAT NOT NULL DEFAULT 0,
`discount` FLOAT NOT NULL DEFAULT 0,
`quantity` SMALLINT(6) NOT NULL DEFAULT 0,
PRIMARY KEY (`id`),
FOREIGN KEY (`brand`) 
REFERENCES `zooproject`.`brand`(`id`)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION,
FOREIGN KEY (`form`) 
REFERENCES `zooproject`.`form`(`id`)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION, 
FOREIGN KEY (`breed`) 
REFERENCES `zooproject`.`breed`(`id`)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION,
FOREIGN KEY (`age`) 
REFERENCES `zooproject`.`age_rate`(`id`)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION, 
FOREIGN KEY (`weight`) 
REFERENCES `zooproject`.`weight`(`id`)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION,
FOREIGN KEY (`type_of_animal`) 
REFERENCES `zooproject`.`type_of_animal`(`id`)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION);
    
INSERT INTO `zooproject`.`product` (title,brand,form,breed,age,weight,type_of_animal,summary,price,discount,quantity) 
values 
('Kitten Dry',2,1,1,1,2,2,'Kitten Dry Food is precisely formulated for kittens up to 12 months',11.5,0,20),
('Kitten Dry',2,1,1,1,5,2,'Kitten Dry Food is precisely formulated for kittens up to 12 months',19.99,0,20),
('Kitten Dry',2,1,1,1,5,2,'Kitten Dry Food is precisely formulated for kittens up to 12 months',26.09,0,30),
('Maine Coon Kitten Dry',2,1,12,1,2,2,'For Maine Coon kittens - Up to 15 months old',46.79,0,35),
('Kitten Thin Slices in Gravy Canned',2,2,1,1,8,2,'Royal Canin Kitten Thin Slices in Gravy canned wet food is specifically formulated for kittens up to 12 months old',19.99,0,15),
('Persian Kitten Dry',2,1,10,1,2,2,'Royal Canin Persian breed-specific dry kitten food is formulated to meet the nutritional needs of a growing kitten up to 12 months old',11.5,0,25),
('Fit And Active Dry',2,1,1,1,2,2,'Royal Canin Adult Fit & Active dry adult cat food is tailored nutrition for adult cats with outdoor access that are 1 year and older',19.99,0,36),
('Fit And Active Dry',2,1,1,1,5,2,'Royal Canin Adult Fit & Active dry adult cat food is tailored nutrition for adult cats with outdoor access that are 1 year and older',26.09,0,78),
('Fit And Active Dry',2,1,1,1,7,2,'Royal Canin Adult Fit & Active dry adult cat food is tailored nutrition for adult cats with outdoor access that are 1 year and older',46.79,0,25),
('Maine Coon Adult Dry',2,1,12,1,4,2,'Royal Canin Maine Coon breed-specific dry cat food is formulated to meet the nutritional needs of this purebred cat 15 months and older',19.99,0,36),
('Maine Coon Adult Dry',2,1,12,1,6,2,'Royal Canin Maine Coon breed-specific dry cat food is formulated to meet the nutritional needs of this purebred cat 15 months and older',11.5,0,42),
('Bengal Adult Dry',2,1,3,1,5,2,'Royal Canin Bengal breed-specific dry cat food is formulated to meet the nutritional needs of this purebred cat 1 year and older',19.99,0,20),
('Indoor Adult Dry',2,1,1,1,7,2,'Royal Canin Indoor Adult Dry Cat Food is formulated for healthy cats 1-7 years old',26.09,0,20),
('Spayed & Neutered Thin Slices in Gravy Canned',2,2,1,1,8,2,'Thin slices in gravy is for spayed or neutered cats prone to weight gain from 1 to 7 years of age',46.79,0,30),
('Persian Adult Loaf in Sauce Canned',2,2,10,1,8,2,'Royal Canin Persian Breed Loaf in Sauce wet cat food is formulated to meet the nutritional needs of this purebred cat 1 year and older',19.99,0,35),
('Maine Coon Adult Thin Slices in Gravy Canned',2,2,12,1,8,2,'Royal Canin Maine Coon breed-specific wet cat food is formulated to meet the nutritional needs of this purebred cat 1 year and older',11.5,0,15),
('Aging 12+ Thin Slices in Gravy Canned',2,2,1,1,8,2,'Royal Canin Feline Health Aging 12+ Thin Slices in Gravy is wet cat food tailored for senior cats 12 years and older',19.99,0,25),
('Instinctive 7+ Thin Slices in Gravy Canned',2,2,1,1,8,2,'Royal Canin Instinctive 7+ Years Thin Slices in Gravy is wet cat food for mature cats 7 to 12 years old',26.09,0,36),
('Wild Anchovies',4,1,1,1,11,2,'LeChat Excellence Wild Anchovies is an innovative range created to feed your cat “as nature intended',46.79,0,78),
('Adult Cat Light Turkey',4,1,1,1,12,2,'LeChat Excellence Wild Anchovies is an innovative range created to feed your cat “as nature intended',19.99,0,25),
('Adult Complete with Chicken and Turkey',4,1,1,1,12,2,'Complete chicken-based food for indoor and outdoor adult cats from the age of 1 year',11.5,0,36),
('Adult Beef & Cheese Entr?e In Gravy Wet',3,2,1,1,8,2,'Made With Real Beef complemented with cheese',19.99,0,42),
('Adult Complete Essentials Chicken & Egg Formula',3,1,1,1,2,2,'Natural with added vitamins, minerals and nutrients',26.09,0,20),
('Golden Retriever Adult Dry',2,1,1,1,14,3,'Royal Canin Golden Retriever Adult dry dog food is designed to meet the nutritional needs of purebred Golden Retrievers 15 months and older',46.79,0,20),
('Golden Retriever Adult Dry',2,1,1,1,15,3,'Royal Canin Golden Retriever Adult dry dog food is designed to meet the nutritional needs of purebred Golden Retrievers 15 months and older',47.79,0,30),
('Golden Retriever Puppy Dry',2,1,1,1,15,3,'Royal Canin Golden Retriever Puppy dry dog food is designed to meet the nutritional needs of purebred Golden Retrievers 8 weeks to 15 months old',48.79,0,35),
('Chihuahua Pouch',2,2,1,1,8,3,'Royal Canin Chihuahua wet dog food is designed to meet the nutritional needs of purebred Chihuahuas 8 months and older',49.79,0,15),
('Dachshund Pouch',2,2,1,1,8,3,'Complete and balanced nutrition for adult Dachshunds over 10 months old',50.79,0,25),
('Poodle Pouch',2,2,1,1,8,3,'Complete and balanced nutrition for adult Poodles over 10 months old',51.79,0,36),
('Yorkshire Terrier Pouch',2,2,1,1,8,3,'Royal Canin Yorkshire Terrier Adult wet dog food is designed to meet the nutritional needs of purebred Yorkshire Terriers 10 months and older',52.79,0,78),
('Poodle Adult Dry',2,1,1,1,1,3,'Royal Canin Poodle Adult dry dog food is designed to meet the nutritional needs of purebred Poodles 10 months and older',53.79,0,25),
('Poodle Adult Dry',2,1,1,1,13,3,'Royal Canin Poodle Adult dry dog food is designed to meet the nutritional needs of purebred Poodles 10 months and older',54.79,0,36);    

UPDATE `zooproject`.`product` SET age = 3 
WHERE id = 1
OR id = 2
OR id = 3
OR id = 4
OR id = 5
OR id = 6;

UPDATE `zooproject`.`product` SET age = 4 
WHERE id = 7
OR id = 8
OR id = 9
OR id = 10
OR id = 11
OR id = 12
OR id = 13
OR id = 14
OR id = 15
OR id = 16
OR id = 19
OR id = 20
OR id = 21
OR id = 22
OR id = 23
OR id = 24
OR id = 25
OR id = 26
OR id = 27
OR id = 28
OR id = 29
OR id = 30
OR id = 31
OR id = 32;

UPDATE `zooproject`.`product` SET age = 2 
WHERE id = 17;

UPDATE `zooproject`.`product` SET age = 1 
WHERE id = 18;

ALTER TABLE `zooproject`.`product` ADD image varchar(100) NOT NULL DEFAULT 'default.jpg';

UPDATE `zooproject`.`product` SET image = 'kitten.jpg' where id = 1;
UPDATE `zooproject`.`product` SET image = 'kitten.jpg' where id = 2;
UPDATE `zooproject`.`product` SET image = 'kitten.jpg' where id = 3;
UPDATE `zooproject`.`product` SET image = 'edrrv41an1jimnt7t3ro.jpg' where id = 3;
UPDATE `zooproject`.`product` SET image = 'dqdbooa6xraakzmpvlpw.jpg' where id = 4;
UPDATE `zooproject`.`product` SET image = 'f2upstbsrzy4dj0rkjsw.jpg' where id = 5;
UPDATE `zooproject`.`product` SET image = 'olwumfdcrrntbwdtgeex.jpg' where id = 6;
UPDATE `zooproject`.`product` SET image = 'vpmer4o6dahgzwvgptgj.jpg' where id = 7;
UPDATE `zooproject`.`product` SET image = 'vpmer4o6dahgzwvgptgj.jpg' where id = 8;
UPDATE `zooproject`.`product` SET image = 'vpmer4o6dahgzwvgptgj.jpg' where id = 9;
UPDATE `zooproject`.`product` SET image = 'jyjozk0ojtab0kqqz7ks.jpg' where id = 10;
UPDATE `zooproject`.`product` SET image = 'jyjozk0ojtab0kqqz7ks.jpg' where id = 11;
UPDATE `zooproject`.`product` SET image = 'cgllpvwqyosuau1jyn6d.jpg' where id = 12;
UPDATE `zooproject`.`product` SET image = 'asxpoyuc1trgicldpdu3.jpg' where id = 13;
UPDATE `zooproject`.`product` SET image = 'v7igidxecmkebha1llhp.jpg' where id = 14;
UPDATE `zooproject`.`product` SET image = 'haxefkhdtkjs02lknnvd.jpg' where id = 15;
UPDATE `zooproject`.`product` SET image = 'qvwtmspuiblbletlinac.jpg' where id = 16;
UPDATE `zooproject`.`product` SET image = 'fpoz23uvyit1vvzuoeit.jpg' where id = 17;
UPDATE `zooproject`.`product` SET image = 'i20yyhplb1nismobpocx.jpg' where id = 18;
UPDATE `zooproject`.`product` SET image = 'default.jpg' where id = 19;
UPDATE `zooproject`.`product` SET image = 'default.jpg' where id = 20;
UPDATE `zooproject`.`product` SET image = 'default.jpg' where id = 21;
UPDATE `zooproject`.`product` SET image = 'default.jpg' where id = 22;
UPDATE `zooproject`.`product` SET image = 'default.jpg' where id = 23;
UPDATE `zooproject`.`product` SET image = 'vegqxgowcgswyciix1l5.jpg' where id = 24;
UPDATE `zooproject`.`product` SET image = 'vegqxgowcgswyciix1l5.jpg' where id = 25;
UPDATE `zooproject`.`product` SET image = 'hylvx8m135mqwkfiehjj.jpg' where id = 26;
UPDATE `zooproject`.`product` SET image = 'lzkujuwfmdv6dr9nkzbq.jpg' where id = 27;
UPDATE `zooproject`.`product` SET image = 'od7w1xw14c4gi0v71pyl.jpg' where id = 28;
UPDATE `zooproject`.`product` SET image = 'jpkm4s6kxz6omnatqfsx.jpg' where id = 29;
UPDATE `zooproject`.`product` SET image = 'xpptavrvhno7keoq9xdb.jpg' where id = 30;
UPDATE `zooproject`.`product` SET image = 'cynveigvy0wog3kgdubr.jpg' where id = 31;
UPDATE `zooproject`.`product` SET image = 'cynveigvy0wog3kgdubr.jpg' where id = 32;


CREATE TABLE `zooproject`.`order_status` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`status` VARCHAR(50) NOT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `uq_status` (`status` ASC));

INSERT INTO `zooproject`.`order_status` (status) 
values
('New'),
('Checkout'),
('Paid'),
('Failed'),
('Shipped'), 
('Delivered'),
('Returned'), 
('Complete');

CREATE TABLE `zooproject`.`order` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`user` BIGINT NOT NULL DEFAULT 0,
`status` BIGINT NOT NULL DEFAULT 0,
`total` FLOAT NOT NULL DEFAULT 0,
`delivery_address` BIGINT NOT NULL DEFAULT 0,
PRIMARY KEY (`id`),
FOREIGN KEY (`user`)
REFERENCES `zooproject`.`user` (`id`)
   ON DELETE NO ACTION ON UPDATE NO ACTION,
FOREIGN KEY (`status`)
REFERENCES `zooproject`.`order_status` (`id`)
   ON DELETE NO ACTION ON UPDATE NO ACTION,
FOREIGN KEY (`delivery_address`)
REFERENCES `zooproject`.`user_address` (`id`)
   ON DELETE NO ACTION ON UPDATE NO ACTION);

CREATE TABLE `zooproject`.`order_item` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`order` BIGINT NOT NULL DEFAULT 0,
`product` BIGINT NOT NULL DEFAULT 0,
`price` FLOAT NOT NULL DEFAULT 0,
`quantity` SMALLINT(6) NOT NULL DEFAULT 0,

PRIMARY KEY (`id`),
FOREIGN KEY (`product`)
REFERENCES `zooproject`.`product` (`id`)
   ON DELETE NO ACTION ON UPDATE NO ACTION,
FOREIGN KEY (`order`)
REFERENCES `zooproject`.`order` (`id`)
   ON DELETE NO ACTION ON UPDATE NO ACTION);