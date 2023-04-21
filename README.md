# Resting API

In this test we want you to create a RESTful API, using Spring Boot and Spring web.

The resource you will work with are American presidents and your RESTful resources should be located at `/api/president`.

Here is one president represented as JSON:

```json
{
  "id": "e8584982-6c32-4408-870f-e2712f735fce",
  "from": "2009",
  "to": "2017",
  "name": "Barack Obama"
}
```

For each president the following rules apply:

- `id` is required. New Id's can be created with `UUID.randomUUID()`
- `name` is required
- `from` is required and should be the year as a YYYY-string, for example `2010`
- `to` is not required as some presidents has not ended their tenure ... yet.


RESTful means that you should adhere to the REST style and supply endpoints for:

- Create (POST) - creating a new president... without riots, please!
- Read (GET) - two endpoints; one to get a specific president and one that lists all presidents.
  - When listing presidents, you only need to return the name and the location of the `GET` endpoint.  No paging or count needed.
    Like this : 
```json
[
  {
    "name": "Barack Obama",
    "uri": "/api/president/e8584982-6c32-4408-870f-e2712f735fce"
  },
  { ... }
]
```
- Update (PUT) - to update president data. Remember that PUT replaces the data with the payload you send it, where PATCH updates individual fields. PATCH is not part of this test.
- Delete (DELETE) - to delete one president.

Let the REST constraints guide you in how to structure the endpoints.
Ensure to return the correct status codes, location and content-type headers, as we have talked about during the week. For each endpoint return the data that is created, or updated, in JSON-format.

We want you to demonstrate that you can write RESTful APIs and return appropriate results, status codes and potential error messages from your API endpoints.

## Handling errors

Your Web API should never crash, but handle any potential errors in a graceful manner. Use status codes appropriatly to help the client to understand what has happened, if an error has happened.

## A word on the "database"

The database is just in-memory and accessed via the `PresidentRepository`, and can be injected in the constructor of a controller.

Since the database is "in-memory" remember that any changes you make to the data will go away between test runs as the Repository is initialised.

## Boiler plate code and scripts

We have supplied a lot of integration-tests in the `RepositoryTest.java` but we encourage you to write more tests to make sure the API works well.

We have also created some starting points for you,

- the initialization of the controller (but no Routing-values...)
- the `President` class that is stored in the database and the `ListPresidentResponse` used to return a list of presidents.

## Handing in your solution

Please submit your `src/main` folders to your Google Drive in a new directory called `restingAPI` inside the directory with your name. Ensure that all java code and any property files are included so we can start the API and run our test suite against it.
This means that the folder should contain a `main` folder at its root, with the `java` folder inside it. _IF_ you add a resources folder that your code depends on, make sure to add this as well.
## FAQ

Should ID be included in the request body or part of the url, when making a put request to the api?

> It should be in the url. All other data should be in the body!

Should I upload my test files

> No, we will run our own suite
