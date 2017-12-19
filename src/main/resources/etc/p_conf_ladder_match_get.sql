CREATE OR REPLACE FUNCTION p_conf_ladder_match_get
(
  IN  i_season_id  INTEGER
)
RETURNS SETOF conf_ladder_match AS 
$$
  select season_id, season_name, bonus_pool_multiple, give_reward, start_time, end_time, 
          create_time, update_time from conf_ladder_match where season_id = i_season_id;
$$
LANGUAGE 'sql'
COST 100;