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
@Table(name="res_doll")
public class Doll implements Serializable{

    private static final long serialVersionUID = -2578029556125751773L;

    @Id()
    @Column(type="int4" ,name="doll_id")
    private Integer dollId;
    
    @Query
    @Column(type="text" ,name="doll_name")
    private String dollName;

    @Column(type="text" ,name="doll_desc")
    private String dollDesc;

    @Column(type="int4" ,name="doll_price")
    private Integer dollPrice;

    @Column(type="text" ,name="picture_url")
    private String pictureUrl;

    @Column(type="int4" ,name="exchange_num")
    private Integer exchangeNum;

    @Column(type="timestamp" ,name="create_time")
    private Date createTime;

    @Column(type="timestamp" ,name="update_time")
    private Date updateTime;

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

    public String getDollDesc() {
        return dollDesc;
    }

    public void setDollDesc(String dollDesc) {
        this.dollDesc = dollDesc;
    }

    public Integer getDollPrice() {
        return dollPrice;
    }

    public void setDollPrice(Integer dollPrice) {
        this.dollPrice = dollPrice;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Integer getExchangeNum() {
        return exchangeNum;
    }

    public void setExchangeNum(Integer exchangeNum) {
        this.exchangeNum = exchangeNum;
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
}
