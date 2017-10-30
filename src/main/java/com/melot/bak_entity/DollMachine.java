/**
 * This document and its contents are protected by copyright 2012 and owned by Melot Inc.
 * The copying and reproduction of this document and/or its content (whether wholly or partly) or any
 * incorporation of the same into any other material in any media or format of any kind is strictly prohibited.
 * All rights are reserved.
 *
 * Copyright (c) Melot Inc. 2016
 */
package com.melot.bak_entity;

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
@Table(name="res_doll_machine")
public class DollMachine implements Serializable{

    private static final long serialVersionUID = -2578029556125751773L;

    @Id()
    @Column(type="int4" ,name="doll_machine_id")
    private Integer dollMachineId;

    @Query()
    @Column(type="int4" ,name="room_id")
    private Integer roomId;

    @Column(type="int4" ,name="doll_id")
    private Integer dollId;

    @Column(type="text" ,name="doll_machine_name")
    private String dollMachineName;

    @Column(type="int4" ,name="price")
    private Integer price;

    @Column(type="int4" ,name="game_time")
    private Integer gameTime;

    @Column(type="int4" ,name="status")
    private Integer status;

    @Column(type="timestamp" ,name="create_time")
    private Date createTime;

    @Column(type="timestamp" ,name="update_time")
    private Date updateTime;

    @Column(type="int4" ,name="primary_camera_id")
    private Integer primaryCameraId;

    @Column(type="int4" ,name="secondary_camera_id")
    private Integer secondaryCameraId;

    public Integer getDollMachineId() {
        return dollMachineId;
    }

    public void setDollMachineId(Integer dollMachineId) {
        this.dollMachineId = dollMachineId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getDollId() {
        return dollId;
    }

    public void setDollId(Integer dollId) {
        this.dollId = dollId;
    }

    public String getDollMachineName() {
        return dollMachineName;
    }

    public void setDollMachineName(String dollMachineName) {
        this.dollMachineName = dollMachineName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getGameTime() {
        return gameTime;
    }

    public void setGameTime(Integer gameTime) {
        this.gameTime = gameTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getPrimaryCameraId() {
        return primaryCameraId;
    }

    public void setPrimaryCameraId(Integer primaryCameraId) {
        this.primaryCameraId = primaryCameraId;
    }

    public Integer getSecondaryCameraId() {
        return secondaryCameraId;
    }

    public void setSecondaryCameraId(Integer secondaryCameraId) {
        this.secondaryCameraId = secondaryCameraId;
    }
}
