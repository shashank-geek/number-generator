**Problem Statement**

**Question 1-NUMBER GENERATOR**

Write a spring boot application that generates number:

The below API should write into a file /tmp/{TASK_ID}_output.txt in the descending order  ,a sequence of numbers in the decreasing order till 0 , with step and start number given  as input  ;The First API returns a task;When the status of the task is called, the second API , it has to return appropriate status SUCCESS if done or IN_PROGRESS if task is still running;

The third API when called with a completed task should return the list of numbers reading from the file;

 

Expectation:

1-Best design practices with modularity and class and type definitions , OO principles to be should be followed.

2-Documentation to run the code.

3-Relevant test cases.

**API 1**

```POST - http://localhost:8080/api/generate```

Request - 
```
{
    "goal":"20",
    "step":"2"
}
```
Response -
```
{
    "task": "1"
}
```

**API 2**

```GET http://localhost:8080/api/tasks/1/status```

Response
```
{
    "result": "SUCCESS"
}
```

**API 3**

```GET http://localhost:8080/api/tasks/3?action=get_numlist```

Response

```
{
    "task": "20,18,16,14,12,10,8,6,4,2,0"
}
```

**Solution:**

To implement the above solution I have generated this project from Spring initializr - https://start.spring.io/ with depedencies

a. Spring boot starter web

b. Devtools

c. JPA

d. H2 database - http://localhost:8080/h2-console

e. Also used LoggerFactory LOG4J for logging purpose.

f. LOMBOK is also used for demonstration purpose


The architecture of this project is that we will have a controller, service, helper and repository class.

The REST call will flow like this: NumberGeneratorController -> NumberGeneratorService -> NumberGeneratorHelper -> GoalRepository

I have also implemented Global exception handling mechanism so that we will have full control over the body of the response and the HTTP status code.

Junit test cases has been written for all the classes with 100% code coverage.

```How to run this application```

1. Clone the number-generator in your local.
2. Import the project as maven project in your intellij or eclipse ide.
3. Once the dependencies has been downloaded. Start the application locally with packaged tomcat server.
4. Install POSTMAN in your machine to test the application.

You can have a look at below API to test it from POSTMAN:

**API 1** -> This api will return response as task which is UUID and it will generate file in your local machine at 
location /tmp folder with the name "UUID" + "_output.txt"

```POST - http://localhost:8080/api/generate```

Request - 
```
{
    "goal":"20",
    "step":"2"
}
```
Response - HTTP status code 202 Accepted
```
{
    "task": "1"
}
```

**API 2**

```GET http://localhost:8080/api/tasks/1/status```

Response - HTTP status code 200 OK
```
{
    "result": "SUCCESS"
}
```

**API 3**

```GET http://localhost:8080/api/tasks/3?action=get_numlist```

Response - HTTP status code 200 OK

```
{
    "task": "20,18,16,14,12,10,8,6,4,2,0"
}
```

```GET http://localhost:8080/api/tasks/99999?action=get_numlist```

Response - HTTP status code 400 Bad Request

```
{
    "code": "102",
    "detailMessage": "UUID not found",
    "message": "UUID not found"
}
```

```GET http://localhost:8080/api/tasks/1?action=randomAction```

Response - HTTP status code 400 Bad Request

```
{
    "code": "101",
    "detailMessage": "Action is invalid",
    "message": "Invalid Action"
}
```