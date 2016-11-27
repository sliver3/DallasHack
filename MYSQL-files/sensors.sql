CREATE TABLE SENSORS (
	sensorID int not null,
	position tinyint(1) not null default 0,
	location varchar(255) not null default 'Kitchen'
);