# Bookstore

Bookstore app provides features to
-------------------------------
- Add Book to Booksore (http://localhost:8080/api/addBook)
- Get Book By Title AND Author Name (http://localhost:8080/api/fetchBookByTitleAndAuthorName?title=Head First Java&name=Kathy Sierra)
- Get Book By Title OR Author Name (http://localhost:8080/api/fetchBookByTitleOrAuthorName?title=The Spy Coast&name=Kathy Sierra)
- Update Book (http://localhost:8080/api/updateBook)
- Delete Book by ISBN (http://localhost:8080/api/deleteBook?isbn=166251512X)

This application uses Spring Boot, JPA, H2 in memory database, Spring Security

Project Structure
------------------
- constants - this contains the constants used in the project
- controller - this contains the controller and apis provided
- dto - this contains the response object for api
- exception - this contains the exceptions used in the project
- model - this contains the entities used in the project
- repository - repository layer for database
- service - contains service interface and classes
- security - contains the security configurations used in the project
- root package - application main class, 
- src/main/resources - contains the data.sql (dummy data used to load intially at the time of spring startup for testing)
- App folder - contains postman collection to test the requests

How to test the application
----------------------------
There are two ways to test the application

1) Access the URL provided at top of this page to test the apis in browser
2) Import the postman collection (https://github.com/pkuks/bookstore/blob/main/BookStore.postman_collection.json) in postman tool and run the collection
