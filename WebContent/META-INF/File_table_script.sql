USE catapp;
CREATE TABLE File_info (
	File_name VARCHAR (200),
	File_path VARCHAR (300),
	Cell_line VARCHAR (50),
	Phenotype VARCHAR (100),
	File_type VARCHAR (100),
	File_owner_ID VARCHAR (20),
	Supervisor_owner_ID VARCHAR (20),
	Row_ID INT UNSIGNED NOT NULL AUTO_INCREMENT KEY) 
ENGINE MyISAM;