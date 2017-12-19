CREATE OR REPLACE FUNCTION p_res_actor_ladder_match_update
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
  UPDATE res_actor_ladder_match
  SET ladder_match_integral = coalesce(i_ladder_match_integral, ladder_match_integral),
    game_dan = coalesce(i_game_dan, game_dan),
    ladder_match_time = coalesce(i_ladder_match_time, ladder_match_time),
    winning_time = coalesce(i_winning_time, winning_time),
    winning_rate = coalesce(i_winning_rate, winning_rate),
    show_money_give_reward = coalesce(i_show_money_give_reward, show_money_give_reward),
    medal_give_reward = coalesce(i_medal_give_reward, medal_give_reward),
    show_money_count = coalesce(i_show_money_count, show_money_count),
    medal_id = coalesce(i_medal_id, medal_id),
    receive_show_money = coalesce(i_receive_show_money, receive_show_money),
    create_time = coalesce(i_create_time, create_time),
    update_time = coalesce(i_update_time, update_time)
  WHERE actor_id = i_actor_id
  AND season_id = i_season_id;
  o_tag_code := '00000000';
EXCEPTION WHEN OTHERS THEN
  o_tag_code := '01';
END;
$$
LANGUAGE 'plpgsql';