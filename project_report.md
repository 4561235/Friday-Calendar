# Project Report
# Mateusz SZCZECH
# Luca GASCHE

## How to compile the project?

Launch

```
./mvnw clean package
```

In friday-back directory. A jar will appear in

```
friday-back/target/quarkus-app/quarkus-run.jar
```

Launch

```
java -jar quarkus-run.jar
```

To launch the app and go to

```
http://localhost:8080/
```

in your browser to use the calendar.

When the app is built, a warning appears because it estimates that it needs the jackson resteasy dependency in order to read JSON.
We are not sure if we are allowed use Jackson resteasy, so we decided to not include it in the pom.

## How to delete all data from database everytime when application launch?

Go to:

```
friday-back/src/main/resources/application.properties
```

And change this line:

```
quarkus.hibernate-orm.database.generation = update
```

to:
```
quarkus.hibernate-orm.database.generation = drop-and-create
```

## Working features:
* Create/Update/Delete events. Events are saved in H2 database
* An event has an title, description, location, date from, date to, time from, time to and a recurrence
* All recurrences are working (NONE, DAILY, WEEKLY, MONTHLY, YEARLY)
* An event can be all day
* Browse the calendar by month and year
* Calendar refresh automatically
* Day summary
* Status of Paris subway
* Import events from google calendar
* Import events from file (Ical format)

## Not working features
* Can't delete reccurent events
* Importing events from URL
