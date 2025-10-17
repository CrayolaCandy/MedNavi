drop schema if exists accounts;
CREATE SCHEMA `accounts`;
use accounts;

drop table if exists accounts.login;
create table accounts.login(
	#UID int primary key auto_increment,
    username varchar(50) primary key not null,
    password varchar(50) not null,
	zip int not null
);

drop table if exists accounts.record;
create table accounts.record(
	
    recordID int primary key auto_increment,
    username varchar(50) not null,
    search longtext,
    outcome longtext,
    keyword varchar(50) not null,
    doctor1 varchar(50),
    doctor2 varchar(50),
    
    #Foreign key(username) References login(username)
    constraint record_fk_login foreign key (username)
    references login(username)
);

drop table if exists accounts.studentLogin;
create table accounts.studentLogin(
	username varchar(50) primary key not null,
    password varchar(50) not null,
	zip int not null
);

drop table if exists accounts.sRecord;
create table accounts.sRecord(
	recordID int primary key auto_increment,
    username varchar(50) not null,
    search longtext,
    outcome longtext,
    keyword varchar(50) not null,

	constraint sRecord_fk_login foreign key (username)
    references studentLogin(username)
);

insert into accounts.login (username, password, zip)
values
("jhu17", "jinsong1281567", 11355),
("hgee", "himchand1270671", 11429), 
("jweng07", "jonathan1285484", 11368);

insert into accounts.studentLogin (username, password, zip)
values
("jhu17", "1281567jinsong", 11355),
("jweng07", "1285484jonathan", 11368);

insert into accounts.record (username, search, outcome, keyword, doctor1, doctor2)
values 
("jhu17", "Who are you?", "I am an AI    language model developed by OpenAI. I am designed to provide information, answer questions, and engage in conversation on a wide range of topics. How can I assist you today?", "1. Who are", "cardiologist", "radiologist"),
("jweng07", "what are you doing??", "I am an AI language model developed by OpenAI. My purpose is to assist and provide information to the best of my abilities. How can I assist you today?", "1. What are", "d1", "d2"),
("hgee", "Can you help me out?", "Of course! I'll do my best to help you. What do you need assistance with?", "1. Can you", "d1", "d2"),
("jhu17", "My name is Jinsong ", "Hello Jinsong! How can I assist you today?", "2. My name", "d1", "d2");

insert into accounts.sRecord (username, search, outcome, keyword)
values 
("jhu17", "Who?", "I am an AI    language model developed by OpenAI. I am designed to provide information, answer questions, and engage in conversation on a wide range of topics. How can I assist you today?", "1. Who are"),
("jweng07", "are?", "I am an AI    language model developed by OpenAI. I am designed to provide information, answer questions, and engage in conversation on a wide range of topics. How can I assist you today?", "1. are"),
("jhu17", "you?", "I am an AI    language model developed by OpenAI. I am designed to provide information, answer questions, and engage in conversation on a wide range of topics. How can I assist you today?", "2. Who");

Select * from accounts.login;

Select * from accounts.studentLogin;

Select * from accounts.record;

Select * from accounts.sRecord;
