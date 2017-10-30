/**
 * This document and its contents are protected by copyright 2012 and owned by Melot Inc.
 * The copying and reproduction of this document and/or its content (whether wholly or partly) or any
 * incorporation of the same into any other material in any media or format of any kind is strictly prohibited.
 * All rights are reserved.
 *
 * Copyright (c) Melot Inc. 2016
 */
package com.melot.entity;

import com.melot.annotation.Column;
import com.melot.annotation.Id;
import com.melot.annotation.Query;
import com.melot.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * Title: ActivityGift
 * <p>
 * Description: 参与活动礼物
 * </p>
 * @author  姚国平<a href="mailto:guoping.yao@melot.cn">
 * @version V1.0
 * @since 2016-7-31 下午12:28:53 
 */
@Table(name="catch_doll_record")
public class CatchDollRecord implements Serializable{

    private static final long serialVersionUID = -2019757007935549216L;

    @Id()
    @Column(type="int4" ,name="id")
    private Integer catchDollRecordId;

    @Query()
    @Column(type="int4" ,name="user_id")
    private Integer userId;

    @Column(type="text" ,name="nick_name")
    private String nickName;

    @Column(type="text" ,name="portrait")
    private String portrait;

    @Column(type="int4" ,name="doll_machine_id")
    private Integer dollMachineId;

    @Column(type="text" ,name="doll_machine_name")
    private String dollMachineName;

    @Column(type="int4" ,name="doll_id")
    private Integer dollId;

    @Column(type="text" ,name="doll_name")
    private String dollName;

    @Column(type="text" ,name="doll_picture_url")
    private String dollPictureUrl;

    @Column(type="int4" ,name="status")
    private Integer status;

    @Query()
    @Column(type="int4" ,name="exchange_status")
    private Integer exchangeStatus;

    @Column(type="int4" ,name="exchange_num")
    private Integer exchangeNum;

    @Column(type="text" ,name="waybill_number")
    private String waybillNumber;

    @Column(type="text" ,name="courier_company")
    private String courierCompany;

    @Column(type="text" ,name="consignee")
    private String consignee;

    @Column(type="text" ,name="mobile")
    private String mobile;

    @Column(type="text" ,name="address")
    private String address;

    @Column(type="int4" ,name="third_record_id")
    private Integer thirdRecordId;

    @Column(type="timestamp" ,name="exchange_time")
    private Date exchangeTime;

    @Column(type="timestamp" ,name="create_time")
    private Date createTime;

    @Column(type="timestamp" ,name="end_time")
    private Date endTime;

    @Column(type="text" ,name="doll_desc")
    private String dollDesc;

    @Column(type="timestamp" ,name="delivery_time")
    private Date deliveryTime;


    @Column(type="int4" ,name="room_id")
    private Integer roomId;

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getCatchDollRecordId() {
        return catchDollRecordId;
    }

    public void setCatchDollRecordId(Integer catchDollRecordId) {
        this.catchDollRecordId = catchDollRecordId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public Integer getDollMachineId() {
        return dollMachineId;
    }

    public void setDollMachineId(Integer dollMachineId) {
        this.dollMachineId = dollMachineId;
    }

    public String getDollMachineName() {
        return dollMachineName;
    }

    public void setDollMachineName(String dollMachineName) {
        this.dollMachineName = dollMachineName;
    }

    public Integer getDollId() {
        return dollId;
    }

    public void setDollId(Integer dollId) {
        this.dollId = dollId;
    }

    public String getDollName() {
        return dollName;
    }

    public void setDollName(String dollName) {
        this.dollName = dollName;
    }

    public String getDollPictureUrl() {
        return dollPictureUrl;
    }

    public void setDollPictureUrl(String dollPictureUrl) {
        this.dollPictureUrl = dollPictureUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getExchangeStatus() {
        return exchangeStatus;
    }

    public void setExchangeStatus(Integer exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }

    public Integer getExchangeNum() {
        return exchangeNum;
    }

    public void setExchangeNum(Integer exchangeNum) {
        this.exchangeNum = exchangeNum;
    }

    public String getWaybillNumber() {
        return waybillNumber;
    }

    public void setWaybillNumber(String waybillNumber) {
        this.waybillNumber = waybillNumber;
    }

    public String getCourierCompany() {
        return courierCompany;
    }

    public void setCourierCompany(String courierCompany) {
        this.courierCompany = courierCompany;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getThirdRecordId() {
        return thirdRecordId;
    }

    public void setThirdRecordId(Integer thirdRecordId) {
        this.thirdRecordId = thirdRecordId;
    }

    public Date getExchangeTime() {
        return exchangeTime;
    }

    public void setExchangeTime(Date exchangeTime) {
        this.exchangeTime = exchangeTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDollDesc() {
        return dollDesc;
    }

    public void setDollDesc(String dollDesc) {
        this.dollDesc = dollDesc;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
