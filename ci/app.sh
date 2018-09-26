set -e

docker-compose up --build -d

sleep 10

# TODO - Probe 3 times for healthcheck

curl localhost:4200

docker-compose down
