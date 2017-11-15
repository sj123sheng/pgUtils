CREATE OR REPLACE FUNCTION p_catch_doll_record_insert
(
  IN  i_userId  INTEGER,
  IN  i_nickName  TEXT,
  IN  i_portrait  TEXT,
  IN  i_dollMachineId  INTEGER,
  IN  i_dollMachineName  TEXT,
  IN  i_dollId  INTEGER,
  IN  i_dollName  TEXT,
  IN  i_dollPictureUrl  TEXT,
  IN  i_status  INTEGER,
  IN  i_exchangeStatus  INTEGER,
  IN  i_exchangeNum  INTEGER,
  IN  i_waybillNumber  TEXT,
  IN  i_courierCompany  TEXT,
  IN  i_consignee  TEXT,
  IN  i_mobile  TEXT,
  IN  i_address  TEXT,
  IN  i_thirdRecordId  INTEGER,
  IN  i_exchangeTime  TIMESTAMP,
  IN  i_createTime  TIMESTAMP,
  IN  i_endTime  TIMESTAMP,
  IN  i_dollDesc  TEXT,
  IN  i_deliveryTime  TIMESTAMP,
  IN  i_roomId  INTEGER,
  OUT  o_id  INTEGER
)
RETURNS INTEGER AS
$$
BEGIN
  o_id := nextval('tshow.catch_doll_record_id_seq');
  INSERT INTO catch_doll_record (id, user_id, nick_name, portrait, 
    doll_machine_id, doll_machine_name, doll_id, 
    doll_name, doll_picture_url, status, 
    exchange_status, exchange_num, waybill_number, 
    courier_company, consignee, mobile, 
    address, third_record_id, exchange_time, 
    create_time, end_time, doll_desc, 
    delivery_time, room_id)
  VALUES (o_id, i_userId, i_nickName, i_portrait, 
    i_dollMachineId, i_dollMachineName, i_dollId, 
    i_dollName, i_dollPictureUrl, i_status, 
    i_exchangeStatus, i_exchangeNum, i_waybillNumber, 
    i_courierCompany, i_consignee, i_mobile, 
    i_address, i_thirdRecordId, i_exchangeTime, 
    i_createTime, i_endTime, i_dollDesc, 
    i_deliveryTime, i_roomId);
EXCEPTION WHEN OTHERS THEN
  o_id := -1;
END;
$$
LANGUAGE 'plpgsql';