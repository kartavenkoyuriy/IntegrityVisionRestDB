DROP FUNCTION IF EXISTS insert_user(_login TEXT, _firstname TEXT, _lastname TEXT, _last_login_on TIMESTAMP);
DROP FUNCTION IF EXISTS get_users_by_date(_date TIMESTAMP, _limit INTEGER);
DROP FUNCTION IF EXISTS update_user(_id INTEGER, _login TEXT, _firstname TEXT, _lastname TEXT, _last_login_on TIMESTAMP );

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

CREATE OR REPLACE FUNCTION update_user(_id  INTEGER, _login TEXT, _firstname TEXT, _lastname TEXT,
                                       _last_login_on TIMESTAMP)
  RETURNS users AS
  '
  DECLARE
    _users users;
  BEGIN
      UPDATE users
    SET login       = COALESCE(_login, users.login),
      firstname     = COALESCE(_firstname, users.firstname),
      lastname      = COALESCE(_lastname, users.lastname),
      last_login_on = COALESCE(_last_login_on, users.last_login_on)
      WHERE users.id = _id
      RETURNING * INTO _users;
    RETURN _users;
  END;
  '
LANGUAGE 'plpgsql' VOLATILE
COST 100;

CREATE OR REPLACE FUNCTION insert_user(_login TEXT, _firstname TEXT, _lastname TEXT, _last_login_on TIMESTAMP)
  RETURNS users AS
  '
  DECLARE
    _users users;
  BEGIN
    INSERT INTO users (login, firstname, lastname, last_login_on)
    VALUES (_login, _firstname, _lastname, _last_login_on)
      RETURNING * INTO _users;
    RETURN _users;
  END;
'
LANGUAGE 'plpgsql' VOLATILE
COST 100;

CREATE OR REPLACE FUNCTION get_users_by_date(_date TIMESTAMP, _limit INTEGER)
  RETURNS SETOF users AS
  'SELECT * FROM users
  WHERE last_login_on::date = _date::date LIMIT _limit;' language 'sql';

