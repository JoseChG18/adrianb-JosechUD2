DROP DATABASE IF EXISTS SW;
CREATE DATABASE IF NOT EXISTS SW;

USE SW;
drop table if exists CHARACTERS;
CREATE TABLE IF NOT EXISTS CHARACTERS(
ID INT PRIMARY KEY AUTO_INCREMENT,
NAME VARCHAR(50),
GENDER VARCHAR(50),
SKIN_COLOR VARCHAR(50),
HOMEWORLD int,
HAIR_COLOR VARCHAR(50)
);
/*select c.id,c.name,c.gender,c.skin_color,p.name,c.hair_color from characters as c
inner join planets as p on c.HOMEWORLD = p.id*/
/*Inserciones en la tabla de Characters*/
Insert into Characters (name,gender,skin_color,homeworld,hair_color) values ('Luke Skywalker','male','fair',1,'blond');
Insert into Characters (name,gender,skin_color,homeworld,hair_color) values ('C-3PO','n/a','gold',1,'n/a');
Insert into Characters (name,gender,skin_color,homeworld,hair_color) values ('R2-D2','n/a','white, blue',1,'n/a');
Insert into Characters (name,gender,skin_color,homeworld,hair_color) values ('Darth Vader','male','white',1,'none');
Insert into Characters (name,gender,skin_color,homeworld,hair_color) values ('Leia Organa','female','light',2,'blown');
Insert into Characters (name,gender,skin_color,homeworld,hair_color) values ('Anakin Skywalker','male','fair',1,'blond');
Insert into Characters (name,gender,skin_color,homeworld,hair_color) values ('Shmi Skywalker','female','fair',1,'black');
Insert into Characters (name,gender,skin_color,homeworld,hair_color) values ('Obi-Wan Kenobi','male','fair',20,'auburn, white');
Insert into Characters (name,gender,skin_color,homeworld,hair_color) values ('Owen Lars','male','light',1,'brown, grey');
Insert into Characters (name,gender,skin_color,homeworld,hair_color) values ('Beru Whitesun lars','female','light',1,'brown');
Insert into Characters (name,gender,skin_color,homeworld,hair_color) values ('R5-D4','n/a','white, red',1,'n/a');
Insert into Characters (name,gender,skin_color,homeworld,hair_color) values ('Biggs Darklighter','male','light',1,'black');


drop table if exists PLANETS;
CREATE TABLE IF NOT EXISTS PLANETS(
ID INT PRIMARY KEY AUTO_INCREMENT,
NAME VARCHAR(50),
TERRAIN VARCHAR(50),
GRAVITY VARCHAR(50),
CLIMATE VARCHAR(100),
POPULATION VARCHAR(50)
);

/*Inserciones en la tabla de Planets*/
Insert into PLANETS (NAME, TERRAIN, GRAVITY, CLIMATE,POPULATION) values ('Tatooine','desert','1 standard','arid','200000');
Insert into PLANETS (NAME, TERRAIN, GRAVITY, CLIMATE,POPULATION) values ('Alderaan','grasslands, mountains','1 standard','temperate','2000000000');
Insert into PLANETS (NAME, TERRAIN, GRAVITY, CLIMATE,POPULATION) values ('Yavin IV','jungle, rainforests','1 standard','temperate, tropical','1000');
Insert into PLANETS (NAME, TERRAIN, GRAVITY, CLIMATE,POPULATION) values ('Hoth','tundra, ice caves, mountain ranges','1.1 standard','frozen','unknown');
Insert into PLANETS (NAME, TERRAIN, GRAVITY, CLIMATE,POPULATION) values ('Dagobah','swamp, jungles','N/A','murky','unknown');
Insert into PLANETS (NAME, TERRAIN, GRAVITY, CLIMATE,POPULATION) values ('Bespin','gas giant','1.5 (surface), 1 standard (Cloud City)','temperate','6000000');
Insert into PLANETS (NAME, TERRAIN, GRAVITY, CLIMATE,POPULATION) values ('Endor','forests, mountains, lakes','0.85 standard','temperate','30000000');
Insert into PLANETS (NAME, TERRAIN, GRAVITY, CLIMATE,POPULATION) values ('Naboo','grassy hills, swamps, forests, mountains','1 standard','temperate','4500000000');
Insert into PLANETS (NAME, TERRAIN, GRAVITY, CLIMATE,POPULATION) values ('Coruscant','cityscape, mountains','1 standard','temperate','1000000000000');
Insert into PLANETS (NAME, TERRAIN, GRAVITY, CLIMATE,POPULATION) values ('Kamino','ocean','1 standard','temperate','1000000000');
Insert into PLANETS (NAME, TERRAIN, GRAVITY, CLIMATE,POPULATION) values ('Geonosis','rock, desert, mountain, barren','0.9 standard','temperate, arid','100000000000');
Insert into PLANETS (NAME, TERRAIN, GRAVITY, CLIMATE,POPULATION) values ('Utapau','scrublands, savanna, canyons, sinkholes','1 standard','temperate, arid, windy','95000000');
Insert into PLANETS (NAME, TERRAIN, GRAVITY, CLIMATE,POPULATION) values ('Mustafar','volcanoes, lava rivers, mountains, caves','1 standard','hot','20000');
Insert into PLANETS (NAME, TERRAIN, GRAVITY, CLIMATE,POPULATION) values ('Kashyyyk','jungle, forests, lakes, rivers','1 standard','tropical','45000000');
Insert into PLANETS (NAME, TERRAIN, GRAVITY, CLIMATE,POPULATION) values ('Polis Massa','airless asteroid','0.56 standard','artificial temperate','1000000');
Insert into PLANETS (NAME, TERRAIN, GRAVITY, CLIMATE,POPULATION) values ('Mygeeto','glaciers, mountains, ice canyons','1 standard','frigid','19000000');
Insert into PLANETS (NAME, TERRAIN, GRAVITY, CLIMATE,POPULATION) values ('Felucia','fungus forests','0.75 standard','hot, humid','8500000');
Insert into PLANETS (NAME, TERRAIN, GRAVITY, CLIMATE,POPULATION) values ('Cato Neimoidia','mountains, fields, forests, rock arches','1 standard','temperate, moist','10000000');
Insert into PLANETS (NAME, TERRAIN, GRAVITY, CLIMATE,POPULATION) values ('Saleucami','caves, desert, mountains, volcanoes','unknown','hot','1400000000');
Insert into PLANETS (NAME, TERRAIN, GRAVITY, CLIMATE,POPULATION) values ('Stewjon','grass','1 standard','temperate','unknown');


alter table Characters
	add foreign key(Homeworld) references Planets(id);


drop table if exists FILMS;
CREATE TABLE IF NOT EXISTS FILMS(
ID INT PRIMARY KEY AUTO_INCREMENT,
TITLE VARCHAR(50),
DIRECTOR VARCHAR(50),
RELEASEDATE DATE,
PRODUCER VARCHAR(100)
);

/*Inserciones en la tabla de Planets*/
Insert into Films (TITLE, DIRECTOR, RELEASEDATE, PRODUCER) values ('The Phantom Menace','George Lucas','1999-05-19','Rick McCallum');
Insert into Films (TITLE, DIRECTOR, RELEASEDATE, PRODUCER) values ('Attack of the Clones','George Lucas','2002-05-16','Rick McCallum');
Insert into Films (TITLE, DIRECTOR, RELEASEDATE, PRODUCER) values ('Revenge of the Sith','George Lucas','2005-05-19','Rick McCallum');
Insert into Films (TITLE, DIRECTOR, RELEASEDATE, PRODUCER) values ('A New Hope','George Lucas','1977-05-25','Gary Kurtz, Rick McCallum');
Insert into Films (TITLE, DIRECTOR, RELEASEDATE, PRODUCER) values ('The Empire Strikes Back','Irvin Kershner','1980-05-17','Howard G. Kazanjian, George Lucas, Rick McCallum');
Insert into Films (TITLE, DIRECTOR, RELEASEDATE, PRODUCER) values ('Return of the Jedi','Richard Marquand','1983-05-25','Rick McCallum');



