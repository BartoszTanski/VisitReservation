# Visit Reservation Application (In progress)

### It is a spring boot app that provides REST API. Application is connected to Postgres database.
#### Stack: Spring boot, PostgreSql, Spring MVC, Thymeleaf 

## Functionalities:
- Booking and deleting operations on visits as Client
- Searching for available visits
- CRUD operations on visits as Employee
- Managing employees and clients as Admin
- Verifying user email
- Authentication (Employee, Admin)
  

Method	| Path	| Description	| Role needed
------------- | ------------------------- | ------------- | ---------
POST	| /api/v1/visit/book	| Make new reservation	| User         	
GET	| /api/v1/visit/{id}	| Get details of reservation	| User
DELETE	| /api/v1/visit/{id}	| Delete reservation	| User
PUT	| /api/v1/visit/{id}	| Update reservation details	| Employee

#### Example endpoints:
- http://localhost:8080/api/v1/visit/book (POST) Enables making new reservation
- http://localhost:8080/api/v1/visit/{id} (GET) Returns details of reservation ny its id
- http://localhost:8080/api/v1/visit/{id} (PUT) Enables updating of reservation details
- http://localhost:8080/api/v1/visit/{id} (DELETE) Deletes choosen reservation
- http://localhost:8080/api/v1/visits/employee/{id} (GET) Returns list of visits connected to given employee(id)
- http://localhost:8080/api/v1/visits/client/{id} (GET) Returns list of visits booked by given client(id)

 
***
To perform actions other than GET use REST CLIENT ex.(Postman, Insomnia)

## How To Run*
*Requires PostgresSQL Server
1. Download and unzip the source repository for this guide, or clone it using Git: 
git clone (https://github.com/BartoszTanski/VisitReservation)https://github.com/BartoszTanski/VisitReservation
2. Import project to your IDE
3. Change project properties in /src/main/resources/application.yaml:
postgres username and password
postgres database url
4. Run VisitReservationApplication.java as Java application

