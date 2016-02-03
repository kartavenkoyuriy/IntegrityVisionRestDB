DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS users_id_seq;

CREATE SEQUENCE users_id_seq;

CREATE TABLE users (
	ID integer NOT NULL DEFAULT nextval('users_id_seq'),
	login text NOT NULL,
	firstname text NOT NULL,
	lastname text NOT NULL,
	last_login_on timestamp NOT NULL
);
ALTER TABLE users ALTER ID SET DEFAULT NEXTVAL('users_id_seq');
