set -e

cd src/server

./gradlew clean build
./gradlew test -i

java -jar build/libs/server.jar &

sleep 10

curl localhost:4200/user
