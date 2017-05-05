#!/bin/sh

APP_ENV=`cat /etc/sunseries.ini  | grep ENV_TYPE | awk -F'=' '{ print $2 }'`
java -Dspring.profiles.active=$APP_ENV -Dserver.port=$APP_PORT -jar target/ms-request-handler-*.jar
