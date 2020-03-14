#!/bin/bash

set -euo pipefail

initdb() {
    # init database with multiple schemas
    PGPASSWORD=postgres psql -U postgres -f init_db.sql

    # create tables in all schemas
    schemas="schema1 schema2 schema3 schema4"
    for s in $schemas
    do
        sed "s/<<SCHEMA>>/$s/g" create_tables.sql | PGPASSWORD=foobar psql -U springboot -d dbschemas -
    done
}

delete() {
    PGPASSWORD=postgres psql -U postgres -c 'drop database dbschemas;'
}

# reset the database, needed to run the init script again
# ATTENTION!! Deletes the whole database.
# delete

initdb
