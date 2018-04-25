#!/usr/bin/env bash -x

export MYSQL_PASS=123456
export MYSQL_RUN_DB=gt
export MYSQL_TEST_DB=gt_test
USAGE="Usage: run.sh [init|start|stop|test|clean|docker_start]"

if [ "$#" -ne 1 ]; then
    echo $USAGE
    exit 1
fi

case $1 in
    init)
        echo "pull image..."
        docker pull mysql:5.6
        echo "start container..."
        docker run --name mysql-test -p 3306:3306 -e MYSQL_ROOT_PASSWORD=$MYSQL_PASS -d mysql:5.6
        sleep 5
        echo "drop database $MYSQL_RUN_DB"
        docker exec -d mysql-test mysqladmin -uroot -p$MYSQL_PASS drop $MYSQL_RUN_DB
        echo "create database $MYSQL_RUN_DB"
        docker exec -d mysql-test mysqladmin -uroot -p$MYSQL_PASS create $MYSQL_RUN_DB

        echo "drop database $MYSQL_TEST_DB"
        docker exec -d mysql-test mysqladmin -uroot -p$MYSQL_PASS drop $MYSQL_TEST_DB
        echo "create database $MYSQL_TEST_DB"
        docker exec -d mysql-test mysqladmin -uroot -p$MYSQL_PASS create $MYSQL_TEST_DB
        ;;
    start)
        docker restart mysql-test

        echo "start spring boot"
        mvn clean spring-boot:run -Dspring.profiles.active=prod -Dspring.datasource.password=$MYSQL_PASS -Dspring.datasource.url=jdbc:mysql://127.0.0.1:3306/$MYSQL_RUN_DB
        ;;
    stop)
        echo "stopping docker container mysql-test"
        docker stop mysql-test
        echo "done."
        ;;
    test)
        docker restart mysql-test

        mvn clean test -Dspring.profiles.active=prod -Dspring.datasource.password=$MYSQL_PASS -Dspring.datasource.url=jdbc:mysql://127.0.0.1:3306/$MYSQL_TEST_DB
        ;;
    clean)
        mvn clean
        docker stop mysql-test
        docker rm mysql-test
        ;;
    docker_start)
        docker start mysql-test
        ;;
    *)
        echo $USAGE
        exit 1
        ;;
esac

exit 0