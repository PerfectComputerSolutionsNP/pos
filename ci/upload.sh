#!/bin/bash
set -e

if [ $TRAVIS_BRANCH != "master" ] || [ $TRAVIS_PULL_REQUEST == "true" ]; then

  echo "Not pushing image to docker because branch '$TRAVIS_BRANCH' is not master branch"
  # exit 0
fi

text="Automated release from Travis CI"
branch="master"
repo_full_name=$(git config --get remote.origin.url | sed 's/.*:\/\/github.com\///;s/.git$//')
token="${GITHUB_TOKEN}"

result=$(curl -X GET \
  https://api.github.com/repos/$TRAVIS_REPO_SLUG/releases/latest \
  -H 'Accept: application/vnd.github.v3+json' \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache')

version=$(echo $result |  jq '.["name"]' | tr -d '"')

old=$(echo "$version" | awk -F '\\.' '{print $NF}')
new=$(( $old + 1 ))

echo "Lastest version found: $version"

version="${version%.*}.$new"

generate_post_data() {
  cat <<EOF
{
  "tag_name": "$version",
  "target_commitish": "$branch",
  "name": "$version",
  "body": "$text",
  "draft": false,
  "prerelease": false
}
EOF
}

echo "Creating release $version for repo: $repo_full_name branch: $branch"
curl --data "$(generate_post_data)" "https://api.github.com/repos/$repo_full_name/releases?access_token=$token"

echo "Building docker images"
docker build -t perfectcomputersolutionsnp/pos-api:$version src/server
docker build -t perfectcomputersolutionsnp/pos-gui:$version src/client

echo "Pushing docker images"
docker push perfectcomputersolutionsnp/pos-api:$version
docker push perfectcomputersolutionsnp/pos-gui:$version
