DROP TABLE IF EXISTS event CASCADE;
DROP TABLE IF EXISTS id_recurrence CASCADE;
DROP TABLE IF EXISTS id_calendarType CASCADE;

CREATE TABLE event (
	id INT PRIMARY KEY AUTO_INCREMENT,
	date_start DATE_TIME NOT NULL,
	date_end DATE_TIME NOT NULL,
	location VARCHAR(32),
	description VARCHAR(256),
	id_recurrence INT REFERENCES id(id_recurrence),            -- enum[not, day, week, month, year]
	id_calendarType INT REFERENCES id(id_calendarType)         -- enum[fridray, icallandar, google]
);

CREATE TABLE id_recurrence (
    id INT PRIMARY KEY,
    name VARCHAR(32) NOT NULL
);

CREATE TABLE id_calendarType (
    id INT PRIMARY KEY,
    name VARCHAR(32) NOT NULL
);

---Filling table id_recurrence :
0,Nothing
1,Daily
2,Weekly
3,Monthly
4,Yearly

---Filling table id_calendarType :
0,Friday
1,iClandar
2,GoogleCalendar
