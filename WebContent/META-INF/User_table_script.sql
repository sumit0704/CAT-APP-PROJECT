CREATE DATABASE catapp;
USE catapp;
CREATE TABLE users (
	User_ID VARCHAR (20),
	First_Name VARCHAR (20),
	Last_Name VARCHAR (20),
	Institution VARCHAR (100),
	Phone_Number VARCHAR (40),
	Email VARCHAR (50),
	Supervisor_ID VARCHAR (20),
	Supervisor_First_Name VARCHAR (20),
	Supervisor_Last_Name VARCHAR (20),
	Supervisor_Phone_Number VARCHAR (40),
	Supervisor_Email VARCHAR (50),
	Last_login_time DATETIME,
	Admin_or_not VARCHAR (3),
	Row_ID INT UNSIGNED NOT NULL AUTO_INCREMENT KEY) 
ENGINE MyISAM;
