CREATE OR REPLACE FUNCTION p_conf_ladder_match_insert
(
  IN  i_season_name  TEXT,
  IN  i_bonus_pool_multiple  INTEGER,
  IN  i_give_reward  INTEGER,
  IN  i_start_time  TIMESTAMP,
  IN  i_end_time  TIMESTAMP,
  IN  i_create_time  TIMESTAMP,
  IN  i_update_time  TIMESTAMP,
  OUT  o_season_id  INTEGER
)
RETURNS INTEGER AS
$$
BEGIN
  o_season_id := nextval('kkcx.seq_conf_ladder_match');
  INSERT INTO conf_ladder_match (season_id, season_name, bonus_pool_multiple, give_reward, 
    start_time, end_time, create_time, 
    update_time)
  VALUES (o_season_id, i_season_name, i_bonus_pool_multiple, i_give_reward, 
    i_start_time, i_end_time, i_create_time, 
    i_update_time);
EXCEPTION WHEN OTHERS THEN
  o_season_id := -1;
END;
$$
LANGUAGE 'plpgsql';