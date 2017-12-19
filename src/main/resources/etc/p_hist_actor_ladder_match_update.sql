CREATE OR REPLACE FUNCTION p_hist_actor_ladder_match_update
(
  IN  i_ladder_match_record_id  INTEGER,
  IN  i_actor_id  INTEGER,
  IN  i_season_id  INTEGER,
  IN  i_opponent_actor_id  INTEGER,
  IN  i_ladder_match_result  INTEGER,
  IN  i_receive_score  INTEGER,
  IN  i_opponent_receive_score  INTEGER,
  IN  i_receive_show_money  BIGINT,
  IN  i_opponent_receive_show_money  BIGINT,
  IN  i_create_time  TIMESTAMP,
  IN  i_pk_id  INTEGER,
  OUT  o_tag_code  TEXT
)
RETURNS TEXT AS
$$
BEGIN
  UPDATE hist_actor_ladder_match
  SET actor_id = coalesce(i_actor_id, actor_id),
    season_id = coalesce(i_season_id, season_id),
    opponent_actor_id = coalesce(i_opponent_actor_id, opponent_actor_id),
    ladder_match_result = coalesce(i_ladder_match_result, ladder_match_result),
    receive_score = coalesce(i_receive_score, receive_score),
    opponent_receive_score = coalesce(i_opponent_receive_score, opponent_receive_score),
    receive_show_money = coalesce(i_receive_show_money, receive_show_money),
    opponent_receive_show_money = coalesce(i_opponent_receive_show_money, opponent_receive_show_money),
    create_time = coalesce(i_create_time, create_time),
    pk_id = coalesce(i_pk_id, pk_id)
  WHERE ladder_match_record_id = i_ladder_match_record_id;
  o_tag_code := '00000000';
EXCEPTION WHEN OTHERS THEN
  o_tag_code := '01';
END;
$$
LANGUAGE 'plpgsql';