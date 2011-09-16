drop database if exists taotao;
create database taotao;
use taotao;
create table user(uid int primary key AUTO_INCREMENT,
       uname varchar(20),
       upass varchar(20),
       email varchar(20),
       utype int ,
       isLogin int ,
       regtime varchar(10),
       credit_standing int);
create table goods(gid int PRIMARY key AUTO_INCREMENT,
       gname varchar(20),
       gstorage int ,
       gprice double(5,2),
       guid int ,
       FOREIGN key (guid) REFERENCES user(uid));


