CREATE OR REPLACE FUNCTION p_catch_doll_record_get
(
  IN  i_id  INTEGER
)
RETURNS SETOF catch_doll_record AS 
$$
  select id, user_id, nick_name, portrait, doll_machine_id, doll_machine_name, doll_id, 
          doll_name, doll_picture_url, status, exchange_status, exchange_num, waybill_number, 
          courier_company, consignee, mobile, address, third_record_id, exchange_time, create_time, 
          end_time, doll_desc, delivery_time, room_id from catch_doll_record where id = i_id;
$$
LANGUAGE 'sql'
COST 100;