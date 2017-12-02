
USE realestate;

drop table if exists `Files`;
create table `Files` (
  `id` bigint not null AUTO_INCREMENT,
  `name` varchar(255) not null,
  `contentType` varchar(255) null,
  `extension` varchar(10) not null,
  `content` longtext not null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table `RealEstates` add column `img1` varchar(255) null;
alter table `RealEstates` add column `img2` varchar(255) null;
alter table `RealEstates` add column `img3` varchar(255) null;
alter table `RealEstates` add column `img4` varchar(255) null;
alter table `RealEstates` add column `img5` varchar(255) null;
