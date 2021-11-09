DROP TABLE IF EXISTS event CASCADE;
DROP TABLE IF EXISTS id_recurrence CASCADE;
DROP TABLE IF EXISTS id_calendarType CASCADE;

CREATE TABLE id_recurrence (
    id INT PRIMARY KEY,
    name VARCHAR(32) NOT NULL
);

CREATE TABLE id_calendarType (
    id INT PRIMARY KEY,
    name VARCHAR(32) NOT NULL
);

CREATE TABLE event (
	id INT PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(32) NOT NULL,
	date_start TIMESTAMP NOT NULL,
	date_end TIMESTAMP NOT NULL,
	location VARCHAR(32),
	description VARCHAR(256),
	id_recurrence INT,          -- enum[not, day, week, month, year]
	id_calendarType INT,        -- enum[fridray, icalendar, google]
	FOREIGN KEY(id_recurrence) REFERENCES id_recurrence(id),
	FOREIGN KEY(id_calendarType) REFERENCES id_calendarType(id)
);

---Filling table id_recurrence :
INSERT INTO id_recurrence VALUES (0, 'Nothing');
INSERT INTO id_recurrence VALUES (1, 'Daily');
INSERT INTO id_recurrence VALUES (2, 'Weekly');
INSERT INTO id_recurrence VALUES (3, 'Monthly');
INSERT INTO id_recurrence VALUES (4, 'Yearly');

---Filling table id_calendarType :
--COPY id_calendarType FROM STDIN csv;
INSERT INTO id_calendarType VALUES (0, 'Friday');
INSERT INTO id_calendarType VALUES (1, 'iCalendar');
INSERT INTO id_calendarType VALUES (2, 'GoogleCalendar');

---Filling table event :
INSERT INTO event VALUES ( 1, 'title1', '2000-10-31T01:30:00.000-05:00', '2000-11-01T01:30:00.000-05:00', 'Paris', 'descri1', 0, 0);
INSERT INTO event VALUES ( 2, 'title2', '2021-11-08T01:30:00.000-05:00', '2021-11-08T01:30:00.000-17:00', 'Londre', 'descri2', 1, 1);
INSERT INTO event VALUES ( 3, 'title3', '2021-12-31T01:30:00.000-05:00', '2021-12-01T01:30:00.000-05:00', 'Espagne', 'descri3', 4, 2);
