FROM node:10.11

RUN apt-get update -qq && apt-get install -y build-essential libpq-dev vim

ENV NODE_ROOT /usr/app/

RUN mkdir -p $NODE_ROOT

WORKDIR $NODE_ROOT

COPY . .

RUN npm install -g @angular/cli
RUN yarn install

# Uncomment this if you went with Option #2 in Step #2 (Windows Only)
# RUN npm rebuild node-sass --force

EXPOSE 4200 49153

# TODO - Run in prod mode, not dev mode

CMD npm start
