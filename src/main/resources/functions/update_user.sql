DROP FUNCTION IF EXISTS update_user(_id INTEGER, _login TEXT, _firstname TEXT, _lastname TEXT, _last_login_on TIMESTAMP );

CREATE OR REPLACE FUNCTION update_user(_id  INTEGER, _login TEXT, _firstname TEXT, _lastname TEXT,
                                       _last_login_on TIMESTAMP)
  RETURNS users AS
  $BODY$
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
  $BODY$
LANGUAGE 'plpgsql' VOLATILE
COST 100;
