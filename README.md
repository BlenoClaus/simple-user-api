# Simple-user-api

```
cd simple-user-api/
```

1) First start the Postgres database service:

    ```
    cd user/
    docker-compose -f docker-compose.yml up
    ```
    
    This service already creates the userdb database.

2) In the root folder, build the project

    ```
    cd ..
    mvn clean install
    ```

3) Initialize the remaining 3 services: Discovery, Gateway and User. For this, use the start.sh script or or start the three services individually:

    ```
    sh start.sh
    ```
    or
    ```
    java -jar ./discovery/target/discovery.jar
    java -jar ./gateway/target/gateway.jar
    java -jar ./user/target/user.jar
    ```

Now import in Postman the postman/simple-user-api.postman_collection.json file to test the API


