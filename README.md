# Test application for IBS interview.
Use:
- Java 1.8
- Maven 3.5.0
- Tomcat 9.0.1

## Build
```
mvn package
```

### Builded war file
[ibs-test.war](ibs-test.war)

## Rest-resources
- /api
  - /person
    - `GET` - All persons
  - /status
    - `GET` - All statuses
  - /claim
    - `GET` - All claims
    - /{id}
      - `GET` - get claim by id
      - `PUT` - update claim by id (Need JSON claim-payload)
      - `DELETE` - delete claim by id
      - `POST` - create claim (Need JSON claim-payload)

Claim payload example:
```
{
"id": 1,
"number": 10,
"name": "Test claim",
"from": 2,
"to": 3,
"status": 3
}
```
