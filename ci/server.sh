set -e

cd src/server

gradle clean build
gradle test -i

java -jar build/libs/server.jar &

pid=$1

sleep 10

curl localhost:8080/user

kill -9 $pid
