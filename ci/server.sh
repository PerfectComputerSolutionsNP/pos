set -e

cd src/server

./gradle clean build
./gradle test -i

java -jar build/libs/server.jar &

sleep 10

curl localhost:4200/user
