DROP FUNCTION IF EXISTS insert_user(_login TEXT, _firstname TEXT, _lastname TEXT, _last_login_on TIMESTAMP);

CREATE OR REPLACE FUNCTION insert_user(_login TEXT, _firstname TEXT, _lastname TEXT, _last_login_on TIMESTAMP)
  RETURNS users AS
  $BODY$
  DECLARE
    _users users;
  BEGIN
    INSERT INTO users (login, firstname, lastname, last_login_on)
    VALUES (_login, _firstname, _lastname, _last_login_on)
      RETURNING * INTO _users;
    RETURN _users;
  END;
  $BODY$
LANGUAGE 'plpgsql' VOLATILE
COST 100;
