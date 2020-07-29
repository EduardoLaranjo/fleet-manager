FROM node:lts-alpine AS build

WORKDIR /build

COPY ./package*.json /build/

RUN npm ci

COPY ./src /build/src

COPY ./public /build/public

ENV REACT_APP_GOOGLE_API_KEY AIzaSyCDgaxepLvkAFYBZBUu1m0JtFAffGXT5XY

RUN npm run build


FROM nginx:stable-alpine

COPY --from=build /build/build/ /usr/share/nginx/html

COPY ./nginx.conf /etc/nginx/conf.d/default.conf
