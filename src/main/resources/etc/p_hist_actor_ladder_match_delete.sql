CREATE OR REPLACE FUNCTION p_hist_actor_ladder_match_delete
(
  IN  i_ladder_match_record_id  INTEGER,
  OUT  o_tag_code  TEXT
)
RETURNS TEXT AS
$$
BEGIN
  DELETE FROM hist_actor_ladder_match WHERE ladder_match_record_id = i_ladder_match_record_id;
  o_tag_code := '00000000';
EXCEPTION WHEN OTHERS THEN
  o_tag_code := '01';
END;
$$
LANGUAGE 'plpgsql';