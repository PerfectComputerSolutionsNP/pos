# Point of Sale (POS)
A simple Point-of-Sale (POS) system in the form of a web application.

# Installation for development
We must set up both the client and server.

## Installing dependencies
This application has the following dependencies:

- Node 10.11
- Java 8

## Setting up the server
Execute the following commands:

```
git clone

cd /pos/src/server

./gradlew clean build

java -jar build/libs/server.jar
```

This will run the API on port 8080. It will be
accessible from `http://localhost:8080` of the development machine.

## Setting up the client
Once you have setup the server, execute the following:

```
cd ../client

npm install

npm start
```

The user interface should now be available on `http://localhost:4200`.
