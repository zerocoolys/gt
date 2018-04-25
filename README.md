# eWallet document

## requirements
	
	1. jdk8+
	2. maven3
	3. mysql5.6
	4. docker
	
## usage

execute run.sh under the project root path.

```
	Usage: run.sh [init|start|stop|test|clean|docker_start] 
```
first , execute *run.sh init* for setup docker enviroment, include mysql. After init finished, execute *run.sh start* to start application.

### command info

#### init 
init docker enviroment

#### start
run spring boot application for manual testing

#### stop
stop docker container

#### test
run testcases

#### clean
remove docker container and maven build file.

#### docker_start
start docker container

### tech stack
This application build on top of the spring boot framework, integrate with JPA and RESTful stack.

### API 

#### request body

NameRequest def

```json
{
	"email": "test@test.com"
}
```
TransferRequest def
```json
{
	"email":"test@test.com",
	"transferee":"test1@test.com",
	"amount":1
}
```

#### POST /accounts NameRequest

create a account with input *email*

#### POST /get_account NameRequest

get a account info with input *email*

#### POST /transfer TransferRequest

create a transaction between *email* and *transferee*, and amount is *amount*

#### POST /get_transactions NameRequest

get all user transactions with input *email*

