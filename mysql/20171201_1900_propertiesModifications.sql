
USE realestate;

drop table if exists `PropertyType`;
create table `PropertyType` (
  `id` int(11) not null AUTO_INCREMENT,
  `name` VARCHAR(30) not null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `PropertyType` (`name`) values
  ('House'),
  ('Flat'),
  ('Upper Portion'),
  ('Lower Portion'),
  ('Farm House'),
  ('Room'),
  ('Penthouse'),
  ('Residential Plot'),
  ('Commercial Plot'),
  ('Agricultural Land'),
  ('Industrial Land'),
  ('Plot File'),
  ('Plot Form'),
  ('Office'),
  ('Shop'),
  ('Warehouse'),
  ('Factory'),
  ('Building'),
  ('Other')
;

alter table `RealEstates` add column `propertyTypeId` int(11) null;
alter table `RealEstates`
  add CONSTRAINT `propertyTypeFK`
  FOREIGN KEY (`propertyTypeId`)
  references `PropertyType`(id)
  on UPDATE CASCADE
  on DELETE set NULL;

alter table `RealEstates` add column `city` VARCHAR(255) null;