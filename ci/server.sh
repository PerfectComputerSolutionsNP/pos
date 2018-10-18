set -e

mkdir -p $LOGGING_PATH

cd src/server

gradle clean build
gradle test -i
