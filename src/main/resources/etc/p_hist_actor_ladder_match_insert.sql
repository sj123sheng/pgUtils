CREATE OR REPLACE FUNCTION p_hist_actor_ladder_match_insert
(
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
  OUT  o_ladder_match_record_id  INTEGER
)
RETURNS INTEGER AS
$$
BEGIN
  o_ladder_match_record_id := nextval('kkcx.seq_hist_actor_ladder_match');
  INSERT INTO hist_actor_ladder_match (ladder_match_record_id, actor_id, season_id, opponent_actor_id, 
    ladder_match_result, receive_score, opponent_receive_score, 
    receive_show_money, opponent_receive_show_money, 
    create_time, pk_id)
  VALUES (o_ladder_match_record_id, i_actor_id, i_season_id, i_opponent_actor_id, 
    i_ladder_match_result, i_receive_score, i_opponent_receive_score, 
    i_receive_show_money, i_opponent_receive_show_money, 
    i_create_time, i_pk_id);
EXCEPTION WHEN OTHERS THEN
  o_ladder_match_record_id := -1;
END;
$$
LANGUAGE 'plpgsql';