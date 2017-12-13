Periodicals
=================

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

