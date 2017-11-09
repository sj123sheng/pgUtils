CREATE OR REPLACE FUNCTION p_songjm_test_get
(
  IN  i_colId  INTEGER
)
RETURNS SETOF songjm_test AS 
$$
  select col_id, col_name, col_date from songjm_test where col_id = i_colId;
$$
LANGUAGE 'sql'
COST 100;