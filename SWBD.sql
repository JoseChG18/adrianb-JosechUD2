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


drop table if exists SPECIES;
CREATE TABLE IF NOT EXISTS SPECIES(
ID INT PRIMARY KEY AUTO_INCREMENT,
NAME VARCHAR(50),
CLASSIFICATION VARCHAR(50),
HOMEWORLD INT,
LANGUAGE VARCHAR(100),
AVERAGELIFESPAN varchar(50),

foreign key(Homeworld) references Planets(id)
);
/*Inserciones en la tabla de Species*/
Insert into SPECIES (NAME, CLASSIFICATION, HOMEWORLD, LANGUAGE, AVERAGELIFESPAN) values ('Human','mammal',9,'Galactic Basic','120');
Insert into SPECIES (NAME, CLASSIFICATION, HOMEWORLD, LANGUAGE, AVERAGELIFESPAN) values ('Droid','artificial',NULL,'n/a','indefinite');
Insert into SPECIES (NAME, CLASSIFICATION, HOMEWORLD, LANGUAGE, AVERAGELIFESPAN) values ('Wookie','mammal',14,'Shyriiwook','400');
/*Insert into SPECIES (NAME, CLASSIFICATION, HOMEWORLD, LANGUAGE, AVERAGELIFESPAN) values ('Rodian','reptilian',23,'Galactic Basic','unknown');     No tenemos el planeta 23 en la base de datos aun*/
/*Insert into SPECIES (NAME, CLASSIFICATION, HOMEWORLD, LANGUAGE, AVERAGELIFESPAN) values ('Hutt','gastropod',24,'Huttese','1000');					No tenemos el planeta 24 en la base de datos aun*/
/*Insert into SPECIES (NAME, CLASSIFICATION, HOMEWORLD, LANGUAGE, AVERAGELIFESPAN) values ('Yoda\'s species','mammal',28,'Galactic basic','900');	No tenemos el planeta 28 en la base de datos aun*/
/*Insert into SPECIES (NAME, CLASSIFICATION, HOMEWORLD, LANGUAGE, AVERAGELIFESPAN) values ('Trandoshan','reptile',29,'Dosh','unknown');				No tenemos el planeta 29 en la base de datos aun*/
/*Insert into SPECIES (NAME, CLASSIFICATION, HOMEWORLD, LANGUAGE, AVERAGELIFESPAN) values ('Mon Calamari','amphibian',31,'Mon Calamarian','unknown');No tenemos el planeta 31 en la base de datos aun*/
Insert into SPECIES (NAME, CLASSIFICATION, HOMEWORLD, LANGUAGE, AVERAGELIFESPAN) values ('Ewok','mammal',7,'Ewokese','unknown');
/*Insert into SPECIES (NAME, CLASSIFICATION, HOMEWORLD, LANGUAGE, AVERAGELIFESPAN) values ('Sullustan','mammal',33,'Sullutese','unknown');          No tenemos el planeta 33 en la base de datos aun*/


drop table if exists VEHICLES;
CREATE TABLE IF NOT EXISTS VEHICLES(
ID INT PRIMARY KEY AUTO_INCREMENT,
NAME VARCHAR(50),
MODEL VARCHAR(50),
MANUFACTURER VARCHAR(100),
COSTINCREDITS VARCHAR(100),
LENGTH VARCHAR(50),
PASSENGERS VARCHAR(50),
CARGOCAPACITY VARCHAR(100),
VEHICLECLASS VARCHAR(50)
);

/*Inserciones en la tabla de Vehicles*/
Insert into VEHICLES (NAME, MODEL, MANUFACTURER, COSTINCREDITS, LENGTH, PASSENGERS, CARGOCAPACITY, VEHICLECLASS) values ('Sand Crawler','Digger Crawler','Corellia Mining Corporation','150000','36.8','30','50000','wheeled');
Insert into VEHICLES (NAME, MODEL, MANUFACTURER, COSTINCREDITS, LENGTH, PASSENGERS, CARGOCAPACITY, VEHICLECLASS) values ('T-16 skyhopper','T-16 skyhopper','Incom Corporation','14500','10.4','1','50','repulsorcraft');
Insert into VEHICLES (NAME, MODEL, MANUFACTURER, COSTINCREDITS, LENGTH, PASSENGERS, CARGOCAPACITY, VEHICLECLASS) values ('X-34 landspeeder','X-34 landspeeder','SoroSuub Corporation','10550','3.4','1','5','repulsorcraft');
Insert into VEHICLES (NAME, MODEL, MANUFACTURER, COSTINCREDITS, LENGTH, PASSENGERS, CARGOCAPACITY, VEHICLECLASS) values ('TIE/LN starfighter','Twin Ion Engine/Ln Starfighter','Sienar Fleet Systems','unknown','6.4','0','65','starfighter');
Insert into VEHICLES (NAME, MODEL, MANUFACTURER, COSTINCREDITS, LENGTH, PASSENGERS, CARGOCAPACITY, VEHICLECLASS) values ('Snowspeeder','t-47 airspeeder','Incom corporation','unknown','4.5','0','10','airspeeder');
Insert into VEHICLES (NAME, MODEL, MANUFACTURER, COSTINCREDITS, LENGTH, PASSENGERS, CARGOCAPACITY, VEHICLECLASS) values ('TIE bomber','TIE/sa bomber','Sienar Fleet Systems','unknown','7.8','0','none','space/planetary bomber');
Insert into VEHICLES (NAME, MODEL, MANUFACTURER, COSTINCREDITS, LENGTH, PASSENGERS, CARGOCAPACITY, VEHICLECLASS) values ('AT-AT','All Terrain Armored Transport','Kuat Drive Yards, Imperial Department of Military Research','unknown','20','40','1000','assault walker');
Insert into VEHICLES (NAME, MODEL, MANUFACTURER, COSTINCREDITS, LENGTH, PASSENGERS, CARGOCAPACITY, VEHICLECLASS) values ('AT-ST','All Terrain Scout Transport','Kuat Drive Yards, Imperial Department of Military Research','unknown','2','0','200','walker');
Insert into VEHICLES (NAME, MODEL, MANUFACTURER, COSTINCREDITS, LENGTH, PASSENGERS, CARGOCAPACITY, VEHICLECLASS) values ('Storm IV Twin-Pod cloud car','Storm IV Twin-Pod','Bespin Motors','75000','7','0','10','repulsorcraft');
Insert into VEHICLES (NAME, MODEL, MANUFACTURER, COSTINCREDITS, LENGTH, PASSENGERS, CARGOCAPACITY, VEHICLECLASS) values ('Sail barge','Modified Luxury Sail Barge','Ubrikkian Industries Custom Vehicle Division','285000','30','500','2000000','sail barge');


drop table if exists STARSHIPS;
CREATE TABLE IF NOT EXISTS STARSHIPS(
ID INT PRIMARY KEY AUTO_INCREMENT,
NAME VARCHAR(50),
MODEL VARCHAR(50),
MANUFACTURER VARCHAR(100),
COSTINCREDITS VARCHAR(100),
LENGTH VARCHAR(50),
CARGOCAPACITY VARCHAR(100),
STARSHIPCLASS VARCHAR(50),
HIPERDRIVERATING VARCHAR(50)
);

Insert into STARSHIPS (NAME, MODEL, MANUFACTURER, COSTINCREDITS, LENGTH, CARGOCAPACITY, STARSHIPCLASS,HIPERDRIVERATING) values ('CR90 corvette','CR90 corvette','Corellian Engineering Corporation','3500000','150','3000000','corvette','2.0');
Insert into STARSHIPS (NAME, MODEL, MANUFACTURER, COSTINCREDITS, LENGTH, CARGOCAPACITY, STARSHIPCLASS,HIPERDRIVERATING) values ('Star Destroyer','Imperial I-class Star Destroyer','Kuat Drive Yards','150000000','1.600','36000000','Star Destroyer','2.0');
Insert into STARSHIPS (NAME, MODEL, MANUFACTURER, COSTINCREDITS, LENGTH, CARGOCAPACITY, STARSHIPCLASS,HIPERDRIVERATING) values ('Sentinel-class landing craft','Sentinel-class landing craft','Sienar Fleet Systems, Cyngus Spaceworks','240000','38','180000','landing craft','1.0');
Insert into STARSHIPS (NAME, MODEL, MANUFACTURER, COSTINCREDITS, LENGTH, CARGOCAPACITY, STARSHIPCLASS,HIPERDRIVERATING) values ('Death Star','DS-1 Orbital Battle Station','Imperial Department of Military Research, Sienar Fleet Systems','1000000000000','120000','1000000000000','Deep Space Mobile Battlestation','4.0');
Insert into STARSHIPS (NAME, MODEL, MANUFACTURER, COSTINCREDITS, LENGTH, CARGOCAPACITY, STARSHIPCLASS,HIPERDRIVERATING) values ('Millennium Falcon','YT-1300 light freighte','Corellian Engineering Corporation','100000','34.37','100000','Light freighter','0.5');
Insert into STARSHIPS (NAME, MODEL, MANUFACTURER, COSTINCREDITS, LENGTH, CARGOCAPACITY, STARSHIPCLASS,HIPERDRIVERATING) values ('Y-wing','BTL Y-wing','Koensayr Manufacturing','134999','14','110','assault starfighter','1.0');
Insert into STARSHIPS (NAME, MODEL, MANUFACTURER, COSTINCREDITS, LENGTH, CARGOCAPACITY, STARSHIPCLASS,HIPERDRIVERATING) values ('X-wing','T-65 X-wing','Incom Corporation','149999','12.5','110','Starfighter','1.0');
Insert into STARSHIPS (NAME, MODEL, MANUFACTURER, COSTINCREDITS, LENGTH, CARGOCAPACITY, STARSHIPCLASS,HIPERDRIVERATING) values ('TIE Advanced x1','Twin Ion Engine Advanced x1','Sienar Fleet Systems','unknown','9.2','150','Starfighter','1.0');
Insert into STARSHIPS (NAME, MODEL, MANUFACTURER, COSTINCREDITS, LENGTH, CARGOCAPACITY, STARSHIPCLASS,HIPERDRIVERATING) values ('Executor','Executor-class star dreadnought','Kuat Drive Yards, Fondor Shipyards','1143350000','19000','250000000','Star dreadnought','2.0');
Insert into STARSHIPS (NAME, MODEL, MANUFACTURER, COSTINCREDITS, LENGTH, CARGOCAPACITY, STARSHIPCLASS,HIPERDRIVERATING) values ('Rebel transport','GR-75 medium transport','Gallofree Yards, Inc.','unknown','90','19000000','Medium transport','4.0');

