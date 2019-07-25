#!/bin/bash

# insert new user
curl -H "Content-Type: application/json" -H "Accept: application/json" -X POST -d '{"firstname":"firstname1","surname":"surname","age":39,"email":"email@example.com","premium": false}' http://localhost:8080/users

# query all users
curl -s -H "Accept: application/json" http://localhost:8080/users | jq

#query single user
curl -s -H "Accept: application/json" http://localhost:8080/users/62 | jq

# delete single user
curl -v -H "Accept: application/json" -X DELETE http://localhost:8080/users/62
