package sj.generated.model;

import java.io.Serializable;
import java.util.Date;

public class CatchDollRecord implements Serializable {
    private Integer id;

    private Integer userId;

    private String nickName;

    private String portrait;

    private Integer dollMachineId;

    private String dollMachineName;

    private Integer dollId;

    private String dollName;

    private String dollPictureUrl;

    private Integer status;

    private Integer exchangeStatus;

    private Integer exchangeNum;

    private String waybillNumber;

    private String courierCompany;

    private String consignee;

    private String mobile;

    private String address;

    private Integer thirdRecordId;

    private Date exchangeTime;

    private Date createTime;

    private Date endTime;

    private String dollDesc;

    private Date deliveryTime;

    private Integer roomId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait == null ? null : portrait.trim();
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
        this.dollMachineName = dollMachineName == null ? null : dollMachineName.trim();
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
        this.dollName = dollName == null ? null : dollName.trim();
    }

    public String getDollPictureUrl() {
        return dollPictureUrl;
    }

    public void setDollPictureUrl(String dollPictureUrl) {
        this.dollPictureUrl = dollPictureUrl == null ? null : dollPictureUrl.trim();
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
        this.waybillNumber = waybillNumber == null ? null : waybillNumber.trim();
    }

    public String getCourierCompany() {
        return courierCompany;
    }

    public void setCourierCompany(String courierCompany) {
        this.courierCompany = courierCompany == null ? null : courierCompany.trim();
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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
        this.dollDesc = dollDesc == null ? null : dollDesc.trim();
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CatchDollRecord other = (CatchDollRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getNickName() == null ? other.getNickName() == null : this.getNickName().equals(other.getNickName()))
            && (this.getPortrait() == null ? other.getPortrait() == null : this.getPortrait().equals(other.getPortrait()))
            && (this.getDollMachineId() == null ? other.getDollMachineId() == null : this.getDollMachineId().equals(other.getDollMachineId()))
            && (this.getDollMachineName() == null ? other.getDollMachineName() == null : this.getDollMachineName().equals(other.getDollMachineName()))
            && (this.getDollId() == null ? other.getDollId() == null : this.getDollId().equals(other.getDollId()))
            && (this.getDollName() == null ? other.getDollName() == null : this.getDollName().equals(other.getDollName()))
            && (this.getDollPictureUrl() == null ? other.getDollPictureUrl() == null : this.getDollPictureUrl().equals(other.getDollPictureUrl()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getExchangeStatus() == null ? other.getExchangeStatus() == null : this.getExchangeStatus().equals(other.getExchangeStatus()))
            && (this.getExchangeNum() == null ? other.getExchangeNum() == null : this.getExchangeNum().equals(other.getExchangeNum()))
            && (this.getWaybillNumber() == null ? other.getWaybillNumber() == null : this.getWaybillNumber().equals(other.getWaybillNumber()))
            && (this.getCourierCompany() == null ? other.getCourierCompany() == null : this.getCourierCompany().equals(other.getCourierCompany()))
            && (this.getConsignee() == null ? other.getConsignee() == null : this.getConsignee().equals(other.getConsignee()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getThirdRecordId() == null ? other.getThirdRecordId() == null : this.getThirdRecordId().equals(other.getThirdRecordId()))
            && (this.getExchangeTime() == null ? other.getExchangeTime() == null : this.getExchangeTime().equals(other.getExchangeTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getDollDesc() == null ? other.getDollDesc() == null : this.getDollDesc().equals(other.getDollDesc()))
            && (this.getDeliveryTime() == null ? other.getDeliveryTime() == null : this.getDeliveryTime().equals(other.getDeliveryTime()))
            && (this.getRoomId() == null ? other.getRoomId() == null : this.getRoomId().equals(other.getRoomId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());
        result = prime * result + ((getPortrait() == null) ? 0 : getPortrait().hashCode());
        result = prime * result + ((getDollMachineId() == null) ? 0 : getDollMachineId().hashCode());
        result = prime * result + ((getDollMachineName() == null) ? 0 : getDollMachineName().hashCode());
        result = prime * result + ((getDollId() == null) ? 0 : getDollId().hashCode());
        result = prime * result + ((getDollName() == null) ? 0 : getDollName().hashCode());
        result = prime * result + ((getDollPictureUrl() == null) ? 0 : getDollPictureUrl().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getExchangeStatus() == null) ? 0 : getExchangeStatus().hashCode());
        result = prime * result + ((getExchangeNum() == null) ? 0 : getExchangeNum().hashCode());
        result = prime * result + ((getWaybillNumber() == null) ? 0 : getWaybillNumber().hashCode());
        result = prime * result + ((getCourierCompany() == null) ? 0 : getCourierCompany().hashCode());
        result = prime * result + ((getConsignee() == null) ? 0 : getConsignee().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getThirdRecordId() == null) ? 0 : getThirdRecordId().hashCode());
        result = prime * result + ((getExchangeTime() == null) ? 0 : getExchangeTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getDollDesc() == null) ? 0 : getDollDesc().hashCode());
        result = prime * result + ((getDeliveryTime() == null) ? 0 : getDeliveryTime().hashCode());
        result = prime * result + ((getRoomId() == null) ? 0 : getRoomId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", nickName=").append(nickName);
        sb.append(", portrait=").append(portrait);
        sb.append(", dollMachineId=").append(dollMachineId);
        sb.append(", dollMachineName=").append(dollMachineName);
        sb.append(", dollId=").append(dollId);
        sb.append(", dollName=").append(dollName);
        sb.append(", dollPictureUrl=").append(dollPictureUrl);
        sb.append(", status=").append(status);
        sb.append(", exchangeStatus=").append(exchangeStatus);
        sb.append(", exchangeNum=").append(exchangeNum);
        sb.append(", waybillNumber=").append(waybillNumber);
        sb.append(", courierCompany=").append(courierCompany);
        sb.append(", consignee=").append(consignee);
        sb.append(", mobile=").append(mobile);
        sb.append(", address=").append(address);
        sb.append(", thirdRecordId=").append(thirdRecordId);
        sb.append(", exchangeTime=").append(exchangeTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", dollDesc=").append(dollDesc);
        sb.append(", deliveryTime=").append(deliveryTime);
        sb.append(", roomId=").append(roomId);
        sb.append("]");
        return sb.toString();
    }
}