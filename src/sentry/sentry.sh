docker run -d --name sentry-redis redis
docker run -d --name sentry-postgres -e POSTGRES_PASSWORD=secret -e POSTGRES_USER=sentry postgres
docker run -it --rm -e SENTRY_SECRET_KEY='sentry' --link sentry-postgres:postgres --link sentry-redis:redis sentry upgrade
docker run -d -p 80:9000 --name my-sentry -e SENTRY_SECRET_KEY='sentry' --link sentry-redis:redis --link sentry-postgres:postgres sentry
docker run -d --name sentry-cron -e SENTRY_SECRET_KEY='sentry' --link sentry-postgres:postgres --link sentry-redis:redis sentry run cron
docker run -d --name sentry-worker-1 -e SENTRY_SECRET_KEY='sentry' --link sentry-postgres:postgres --link sentry-redis:redis sentry run worker
