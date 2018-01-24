Periodicals
=================

## Web application that allows to subscribe on periodicals

The application is based on Servlets, JSP + JSTL, JDBC, HTML + CSS and LOG4J 
as a logging system. The code is covered with unit tests using JUnit and Mockito.

## Environment Setup

The application requires:

- JDK 1.8
- Maven 4.0.0 or above
- Docker 17.05.0

## How to run

1. Build docker image for database

    ```
    docker build -t periodicals:periodicals-db -f Dockerfile.mysql .
    ```
    
2. Run the database 

    ```
    docker run -p 3306:3306 -e MYSQL_USER=periodicals -e MYSQL_PASSWORD=periodicals -e MYSQL_DATABASE=periodicals --name periodicals_db periodicals:periodicals-db
    ```

    The following commands must be performed in separate terminal session.

3. Build war-archive with the application 

    ```
    mvn clean package
    ```
4. Build web-server image

    ```
    docker build -t periodicals:periodicals-web -f Dockerfile.web .
    ```

5. Run the web server with the application 

    ```
    docker run -it --rm -p 8080:8080 --name periodicals_web --link periodicals_db  periodicals:periodicals-web
    ```

The application is accessable via http://localhost:8080/.

Use **admin1/admin1** as _username/password_ to admin login.

## Interface

Main page of the application

![main-page](./samples/main-page.png)

Sign up page

![sign-up-page](./samples/sign-up-page.png)

Reader log in page

![reader-login-page](./samples/reader-login-page.png)

Reader main page

![reader-main-page](./samples/reader-main-page.png)

Subscribe page

![subscribe-page](./samples/subscribe-page.png)

Payment page

![payment-page](./samples/payment-page.png)

Profile page

![reader-main-page](./samples/profile-page.png)

Admin log in page

![admin-login-page](./samples/admin-login-page.png)

Admin main page

![admin-main-page](./samples/admin-main-page.png)

Admin periodicals page

![admin-periodicals-page](./samples/admin-periodicals-page.png)

Create a new periodical

![create-periodical-page](./samples/create-periodical-page.png)

Edit page

![edit-periodical-page](./samples/edit-periodical-page.png)