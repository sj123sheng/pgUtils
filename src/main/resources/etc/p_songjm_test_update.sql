CREATE OR REPLACE FUNCTION p_songjm_test_update
(
  IN  i_colId  INTEGER,
  IN  i_colName  TEXT,
  IN  i_colDate  TIMESTAMP,
  OUT  o_tagcode  TEXT
)
RETURNS TEXT AS
$$
BEGIN
  UPDATE songjm_test
  SET col_name = coalesce(i_colName, col_name),
    col_date = coalesce(i_colDate, col_date)
  WHERE col_id = i_colId;
  o_tagcode := '00000000';
EXCEPTION WHEN OTHERS THEN
  o_tagcode := '01';
END;
$$
LANGUAGE 'plpgsql';