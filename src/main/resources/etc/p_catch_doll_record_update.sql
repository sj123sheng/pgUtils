CREATE OR REPLACE FUNCTION p_catch_doll_record_update
(
  IN  i_id  INTEGER,
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
  OUT  o_tagcode  TEXT
)
RETURNS TEXT AS
$$
BEGIN
  UPDATE catch_doll_record
  SET user_id = coalesce(i_userId, user_id),
    nick_name = coalesce(i_nickName, nick_name),
    portrait = coalesce(i_portrait, portrait),
    doll_machine_id = coalesce(i_dollMachineId, doll_machine_id),
    doll_machine_name = coalesce(i_dollMachineName, doll_machine_name),
    doll_id = coalesce(i_dollId, doll_id),
    doll_name = coalesce(i_dollName, doll_name),
    doll_picture_url = coalesce(i_dollPictureUrl, doll_picture_url),
    status = coalesce(i_status, status),
    exchange_status = coalesce(i_exchangeStatus, exchange_status),
    exchange_num = coalesce(i_exchangeNum, exchange_num),
    waybill_number = coalesce(i_waybillNumber, waybill_number),
    courier_company = coalesce(i_courierCompany, courier_company),
    consignee = coalesce(i_consignee, consignee),
    mobile = coalesce(i_mobile, mobile),
    address = coalesce(i_address, address),
    third_record_id = coalesce(i_thirdRecordId, third_record_id),
    exchange_time = coalesce(i_exchangeTime, exchange_time),
    create_time = coalesce(i_createTime, create_time),
    end_time = coalesce(i_endTime, end_time),
    doll_desc = coalesce(i_dollDesc, doll_desc),
    delivery_time = coalesce(i_deliveryTime, delivery_time),
    room_id = coalesce(i_roomId, room_id)
  WHERE id = i_id;
  o_tagcode := '00000000';
EXCEPTION WHEN OTHERS THEN
  o_tagcode := '01';
END;
$$
LANGUAGE 'plpgsql';