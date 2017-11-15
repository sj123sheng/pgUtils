CREATE OR REPLACE FUNCTION p_catch_doll_record_delete
(
  IN  i_id  INTEGER,
  OUT  o_tagcode  TEXT
)
RETURNS TEXT AS
$$
BEGIN
  DELETE FROM catch_doll_record WHERE id = i_id;
  o_tagcode := '00000000';
EXCEPTION WHEN OTHERS THEN
  o_tagcode := '01';
END;
$$
LANGUAGE 'plpgsql';