CREATE OR REPLACE FUNCTION p_songjm_test_delete
(
  IN  i_colId  INTEGER,
  OUT  o_tagcode  TEXT
)
RETURNS TEXT AS
$$
BEGIN
  DELETE FROM songjm_test WHERE col_id = i_colId;
  o_tagcode := '00000000';
EXCEPTION WHEN OTHERS THEN
  o_tagcode := '01';
END;
$$
LANGUAGE 'plpgsql';