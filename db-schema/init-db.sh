#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE DATABASE admin;
	GRANT ALL PRIVILEGES ON DATABASE sample_backend TO admin;
EOSQL