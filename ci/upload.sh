set -e

git config --global user.email "${GITHUB_EMAIL}"
git config --global user.name "Perfect Computer Solutions"

version="v0.0.0"

git tag -a -m "Releasing $version from Travis CI" $version

git push origin $version

if [ $TRAVIS_BRANCH == "master" ] && [ $TRAVIS_PULL_REQUEST == "false" ]; then

  echo "Building docker images"

  docker build -t perfectcomputersolutionsnp/pos-api src/server:$version
  docker build -t perfectcomputersolutionsnp/pos-gui src/client:$version

  echo "Pushing docker images"

  docker push perfectcomputersolutionsnp/pos-api
  docker push perfectcomputersolutionsnp/pos-gui
else
  echo "Not pushing image to docker because branch '$TRAVIS_BRANCH' is not master branch"
fi
