CREATE OR REPLACE FUNCTION p_res_actor_ladder_match_get
(
  IN  i_actor_id  INTEGER,
  IN  i_season_id  INTEGER
)
RETURNS SETOF res_actor_ladder_match AS 
$$
  select actor_id, season_id, ladder_match_integral, game_dan, ladder_match_time, winning_time, 
          winning_rate, show_money_give_reward, medal_give_reward, show_money_count, medal_id, 
          receive_show_money, create_time, update_time from res_actor_ladder_match where actor_id = i_actor_id  and season_id = i_season_id;
$$
LANGUAGE 'sql'
COST 100;