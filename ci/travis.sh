set -e

# TODO - Start a mongo container for testing purposes

./ci/server.sh
./ci/client.sh

# TODO - Kill the mongo container so docker-compose has no conflicts

./ci/app.sh
