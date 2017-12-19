CREATE OR REPLACE FUNCTION p_res_actor_ladder_match_delete
(
  IN  i_actor_id  INTEGER,
  IN  i_season_id  INTEGER,
  OUT  o_tag_code  TEXT
)
RETURNS TEXT AS
$$
BEGIN
  DELETE FROM res_actor_ladder_match WHERE actor_id = i_actor_id  AND season_id = i_season_id;
  o_tag_code := '00000000';
EXCEPTION WHEN OTHERS THEN
  o_tag_code := '01';
END;
$$
LANGUAGE 'plpgsql';