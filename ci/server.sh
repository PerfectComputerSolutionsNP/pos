set -e

cd src/server

gradle clean build
gradle test -i

# TODO - Delete everything after this line once real tests are implemented

java -jar build/libs/server.jar &

pid=$!

sleep 10

curl localhost:8080/user

kill -9 $pid
