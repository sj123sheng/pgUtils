CREATE OR REPLACE FUNCTION p_conf_ladder_match_update
(
  IN  i_season_id  INTEGER,
  IN  i_season_name  TEXT,
  IN  i_bonus_pool_multiple  INTEGER,
  IN  i_give_reward  INTEGER,
  IN  i_start_time  TIMESTAMP,
  IN  i_end_time  TIMESTAMP,
  IN  i_create_time  TIMESTAMP,
  IN  i_update_time  TIMESTAMP,
  OUT  o_tag_code  TEXT
)
RETURNS TEXT AS
$$
BEGIN
  UPDATE conf_ladder_match
  SET season_name = coalesce(i_season_name, season_name),
    bonus_pool_multiple = coalesce(i_bonus_pool_multiple, bonus_pool_multiple),
    give_reward = coalesce(i_give_reward, give_reward),
    start_time = coalesce(i_start_time, start_time),
    end_time = coalesce(i_end_time, end_time),
    create_time = coalesce(i_create_time, create_time),
    update_time = coalesce(i_update_time, update_time)
  WHERE season_id = i_season_id;
  o_tag_code := '00000000';
EXCEPTION WHEN OTHERS THEN
  o_tag_code := '01';
END;
$$
LANGUAGE 'plpgsql';