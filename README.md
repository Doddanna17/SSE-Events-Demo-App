# SSE-Events-Demo-App
SSE-Events-Demo-App

### Step 1: Start the DB
Command: 

docker run --name shortUrlPostgres -e POSTGRES_PASSWORD=password -e POSTGRES_USER=postgres -e POSTGRES_DB=testdb -p 5432:5432 -d postgres

### Step 2: 
Start the Application

### Step 3:
Use the [SSEDemoExecuterFrontend.html](SSEDemoExecuterFrontend.html) for testing the browser level interaction.
Note: Only subscription to topic is present for publishing the event use the below curl

```curl --location --globoff --request POST 'http://localhost:8080/api/events?eventType={{topicName}}&message={{message}}'```
