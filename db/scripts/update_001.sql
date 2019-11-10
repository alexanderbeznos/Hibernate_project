create table if not exists football_position(
id serial primary key,
name varchar(100) unique);

create table if not exists players(
id serial primary key,
name varchar(100),
lastname varchar(100),
marketvalue integer,
country varchar(100),
club varchar(100),
football_position_id int references football_position(id));

create table if not exists users(
id serial primary key,
login varchar(100) unique,
password varchar(100));

insert into football_position (name) values ('Goalkeeper');
insert into football_position (name) values ('Defender');
insert into football_position (name) values ('Midfielder');
insert into football_position (name) values ('Forward');