DROP SCHEMA IF EXISTS `finalProj` ;
CREATE SCHEMA IF NOT EXISTS `finalProj`;
USE `finalProj` ;

drop table if exists users;

create table if not exists users(
	email varchar(128) not null,
    password varchar(128) not null,
    
    primary key(email)
);

create table if not exists comments(
	post_id integer not null auto_increment,
    user_email varchar(128),
    location_id varchar(128) not null,
    comments text,
    rating integer,
    image_url varchar(128),

    
    primary key(post_id),
    
    constraint fk_user_email
		foreign key (user_email) references users(email)
);

--select * from users;

insert ignore into users(email, password) values ('a1@email.com', 'pw1');

--select * from users where BINARY email='A1@email.com';

--select * from comments;

--select count(image_url)>0 as dup_ from comments where image_url = "a";