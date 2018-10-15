set -e

cd src/server

gradle clean build
gradle test -i
