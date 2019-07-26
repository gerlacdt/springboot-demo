CREATE TABLE users (
       id serial PRIMARY KEY,
       -- created_at timestamp without time zone NOT NULL,
       -- updated_at timestamp without time zone NOT NULL,
       firstname text,
       surname text,
       email text NOT NULL,
       age smallint,
       is_premium boolean NOT NULL
)
