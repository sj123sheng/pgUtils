CREATE OR REPLACE FUNCTION p_conf_ladder_match_delete
(
  IN  i_season_id  INTEGER,
  OUT  o_tag_code  TEXT
)
RETURNS TEXT AS
$$
BEGIN
  DELETE FROM conf_ladder_match WHERE season_id = i_season_id;
  o_tag_code := '00000000';
EXCEPTION WHEN OTHERS THEN
  o_tag_code := '01';
END;
$$
LANGUAGE 'plpgsql';