#!/bin/sh

docker run \
    -d \
    --name postgres \
    -p 5432:5432 \
    -v postgres-pgdata-volume:/var/lib/postgresql/data \
    -e POSTGRES_PASSWORD=postgres \
    postgres:16.3-alpine3.20
