set -e

docker-compose up --build -d

sleep 10

curl localhost:4200/user

docker-compose down
