CREATE DATABASE dbschemas;
GRANT ALL PRIVILEGES ON DATABASE dbschemas TO springboot;

\c dbschemas

CREATE SCHEMA schema1 AUTHORIZATION springboot;
CREATE SCHEMA schema2 AUTHORIZATION springboot;
CREATE SCHEMA schema3 AUTHORIZATION springboot;
CREATE SCHEMA schema4 AUTHORIZATION springboot;
