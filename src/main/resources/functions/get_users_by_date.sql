DROP FUNCTION IF EXISTS get_users_by_date(_date TIMESTAMP, _limit INTEGER);

CREATE OR REPLACE FUNCTION get_users_by_date(_date TIMESTAMP, _limit INTEGER)
  RETURNS SETOF users AS
  'SELECT * FROM users
  WHERE last_login_on::date = _date::date LIMIT _limit;' language 'sql';

