# Registration Application
Register application, this application have divided to three part : Web Application, Backend Application, Database.

## Web Page 
Web Page Application using HTML5, Jquery and Boostrap, and see the documentation of Web Page. For open webpage service go to folder frontend and open <strong>index.html</strong>.
![Alt Text](usage.gif)

## Backend
On Backend Application using SpringBoot version 2.3.3 running on Java 11. It have unit testing using Junit, handler exception,In Memory Database (H2) for Testing, and Database Postgres SQL for real register, and integrate with database using Hibernate. For running backend service go to backend and run
```mvn spring-boot:run```. Before run backend service, make sure the database connection can integrate with backend application.
Check on your <strong>application.properties</strong> to change database configuration.

## Database
In database folder you'll find dump file for import the data structures and data. The database in this application is Postgres 12.3.
