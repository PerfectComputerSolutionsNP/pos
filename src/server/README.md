# Point of Sale API
This codebase is a backend RESTful API written in Groovy and Java using the SpringBoot framework.

# Environment Variables
There are several environment variables that need to be configured.

## Required Environment Variables
The following environment variables are required for the application to run.

### `DB_URL`
Connection URL to MySQL database.

### `DB_USER`
MySQL user that will be used for database transactions.

### `DB_PASSWORD`
Password for user for database transactions.

### `JWT_SECRET`
A secret to be used when generated JavaScript Web Tokens (JTWs).

### `LOGGING_PATH`
Local directory / path to save log files to. The recommended path is `/logs/pos/server`.

### `SENTRY_DSN`
Data Source Name (DSN) for publishing Exceptions to Sentry server.

### `EMAIL_USERNAME`
Username of the email address that will be used to send email notifications.

### `EMAIL_PASSWORD`
Password of the email address that will be used to send email notifications.


## Optional Environment Variables
The following environment variables are optional.

### `SSL_KEYSTORE_PASSWORD`
This is only required if SSL / TSL is being used and a keystore file is present.