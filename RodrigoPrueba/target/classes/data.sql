drop table superHeroes if exists;
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 1 increment by 1;
create table superHeroes (id integer not null AUTO_INCREMENT, name varchar(255), primary key (id));
insert into superHeroes (name) values ('Batman');
insert into superHeroes (name) values ('SuperMan');
insert into SuperHeroes (name) values ('IronMan');
insert into SuperHeroes (name) values ('Hulk');