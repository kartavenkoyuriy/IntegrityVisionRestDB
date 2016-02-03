CREATE OR REPLACE FUNCTION update_user(_id INTEGER, _login TEXT, _firstname TEXT, _lastname TEXT, _last_login_on TIMESTAMP)
  RETURNS VOID AS $$
BEGIN
  UPDATE users
    SET login = COALESCE(update_user._login, users.login),
      firstname = COALESCE(update_user._firstname, users.firstname),
      lastname = COALESCE(update_user._lastname, users.lastname),
      last_login_on = COALESCE(update_user._last_login_on, users.last_login_on)
  WHERE users.id = update_user._id;
END;
$$ LANGUAGE plpgsql;



-- CREATE OR REPLACE FUNCTION update_user(
--   nickname   LABEL,
--   full_name  TEXT   DEFAULT NULL,
--   email      EMAIL  DEFAULT NULL,
--   uri        URI    DEFAULT NULL,
--   twitter    CITEXT DEFAULT NULL
-- ) RETURNS BOOLEAN LANGUAGE plpgsql SECURITY DEFINER AS $$
--
-- BEGIN
-- UPDATE users
-- SET full_name      = COALESCE(update_user.full_name, users.full_name),
--   email          = COALESCE(update_user.email,     users.email),
--   uri            = COALESCE(update_user.uri,       users.uri),
--   twitter        = COALESCE(trim(leading '@' FROM update_user.twitter), users.twitter),
--   updated_at     = NOW()
-- WHERE users.nickname = update_user.nickname
--       AND users.status   = 'active';
-- RETURN FOUND;
-- END;