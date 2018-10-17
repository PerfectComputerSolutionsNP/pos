set -e

if [ $TRAVIS_BRANCH == "master" ] && [ $TRAVIS_PULL_REQUEST == "false" ]; then

  echo "Building docker images"

  docker build -t perfectcomputersolutionsnp/pos-api src/server
  docker build -t perfectcomputersolutionsnp/pos-gui src/client

  echo "Pushing docker images"

  docker push perfectcomputersolutionsnp/pos-api
  docker push perfectcomputersolutionsnp/pos-gui
else
  echo "Not pushing image to docker because branch '$TRAVIS_BRANCH' is not master branch"
fi
