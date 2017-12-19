CREATE OR REPLACE FUNCTION p_res_actor_ladder_match_insert
(
  IN  i_actor_id  INTEGER,
  IN  i_season_id  INTEGER,
  IN  i_ladder_match_integral  INTEGER,
  IN  i_game_dan  INTEGER,
  IN  i_ladder_match_time  INTEGER,
  IN  i_winning_time  INTEGER,
  IN  i_winning_rate  INTEGER,
  IN  i_show_money_give_reward  INTEGER,
  IN  i_medal_give_reward  INTEGER,
  IN  i_show_money_count  BIGINT,
  IN  i_medal_id  INTEGER,
  IN  i_receive_show_money  BIGINT,
  IN  i_create_time  TIMESTAMP,
  IN  i_update_time  TIMESTAMP,
  OUT  o_tag_code  TEXT
)
RETURNS TEXT AS
$$
BEGIN
  INSERT INTO res_actor_ladder_match (actor_id, season_id, ladder_match_integral, 
    game_dan, ladder_match_time, winning_time, 
    winning_rate, show_money_give_reward, medal_give_reward, 
    show_money_count, medal_id, receive_show_money, 
    create_time, update_time)
  VALUES (i_actor_id, i_season_id, i_ladder_match_integral, 
    i_game_dan, i_ladder_match_time, i_winning_time, 
    i_winning_rate, i_show_money_give_reward, i_medal_give_reward, 
    i_show_money_count, i_medal_id, i_receive_show_money, 
    i_create_time, i_update_time);
  o_tag_code := '00000000';
EXCEPTION WHEN OTHERS THEN
  o_tag_code := '01';
END;
$$
LANGUAGE 'plpgsql';