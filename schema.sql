DROP SCHEMA IF EXISTS `finalProj` ;
CREATE SCHEMA IF NOT EXISTS `finalProj`;
USE `finalProj` ;

drop table if exists users;

create table if not exists users(
	email varchar(128) not null,
    password varchar(128) not null,
    
    primary key(email)
);

insert into users(email, password) values ('a1@email.com', 'pw1');
insert into users(email, password) values ('account1', 'password2');

select * from users;