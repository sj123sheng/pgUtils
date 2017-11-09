CREATE OR REPLACE FUNCTION p_songjm_test_insert
(
  IN  i_colId  INTEGER,
  IN  i_colName  TEXT,
  IN  i_colDate  TIMESTAMP,
  OUT  o_tagcode  TEXT
)
RETURNS TEXT AS
$$
BEGIN
  INSERT INTO songjm_test (col_id, col_name, col_date)
  VALUES (i_colId, i_colName, i_colDate);
  o_tagcode := '00000000';
EXCEPTION WHEN OTHERS THEN
  o_tagcode := '01';
END;
$$
LANGUAGE 'plpgsql';