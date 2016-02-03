CREATE OR REPLACE FUNCTION insert_user(_login TEXT, _firstname TEXT, _lastname TEXT, _last_login_on TIMESTAMP)
  RETURNS VOID AS $$
BEGIN
  INSERT INTO users(login, firstname, lastname, last_login_on)
  VALUES (_login, _firstname, _lastname, _last_login_on);
END;
$$ LANGUAGE plpgsql;
