set -e

cd src

cd server

docker build  -t perfectcomputersolutionsnp/pos-api .

cd ../client

docker build  -t perfectcomputersolutionsnp/pos-gui .

cd ../..

docker-compose up
