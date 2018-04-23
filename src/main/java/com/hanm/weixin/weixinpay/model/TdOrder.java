package com.hanm.weixin.weixinpay.model;

import java.math.BigDecimal;
import java.util.Date;

public class TdOrder {
    /**
     * 交易记录ID
     */
    private String     id;

    /**
     * 商品ID
     */
    private String     pdId;

    /**
     * 用户ID
     */
    private String     userId;

    /**
     * 购买数量
     */
    private Integer    tdCount;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 总价格
     */
    private BigDecimal totalPrice;

    /**
     * 订单状态
     */
    private String     state;

    private String     receiverDetailLocation;

    private String     memo;

    /**
     * 订单创建时间
     */
    private Date       gmtCreated;

    /**
     * 订单修改时间
     */
    private Date       gmtModified;

    private String     receiverCounty;

    private String     receiverProvince;

    private String     receiverCity;

    private String     receiverPhone;

    private String     receiverName;

    /**
     * 获取交易记录ID
     *
     * @return ID - 交易记录ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置交易记录ID
     *
     * @param id 交易记录ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取商品ID
     *
     * @return PD_ID - 商品ID
     */
    public String getPdId() {
        return pdId;
    }

    /**
     * 设置商品ID
     *
     * @param pdId 商品ID
     */
    public void setPdId(String pdId) {
        this.pdId = pdId;
    }

    /**
     * 获取用户ID
     *
     * @return USER_ID - 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取购买数量
     *
     * @return TD_COUNT - 购买数量
     */
    public Integer getTdCount() {
        return tdCount;
    }

    /**
     * 设置购买数量
     *
     * @param tdCount 购买数量
     */
    public void setTdCount(Integer tdCount) {
        this.tdCount = tdCount;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * 获取订单状态
     *
     * @return STATE - 订单状态
     */
    public String getState() {
        return state;
    }

    /**
     * 设置订单状态
     *
     * @param state 订单状态
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return RECEIVER_DETAIL_LOCATION
     */
    public String getReceiverDetailLocation() {
        return receiverDetailLocation;
    }

    /**
     * @param receiverDetailLocation
     */
    public void setReceiverDetailLocation(String receiverDetailLocation) {
        this.receiverDetailLocation = receiverDetailLocation;
    }

    /**
     * @return MEMO
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 获取订单创建时间
     *
     * @return GMT_CREATED - 订单创建时间
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * 设置订单创建时间
     *
     * @param gmtCreated 订单创建时间
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * 获取订单修改时间
     *
     * @return GMT_MODIFIED - 订单修改时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置订单修改时间
     *
     * @param gmtModified 订单修改时间
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * @return RECEIVER_COUNTY
     */
    public String getReceiverCounty() {
        return receiverCounty;
    }

    /**
     * @param receiverCounty
     */
    public void setReceiverCounty(String receiverCounty) {
        this.receiverCounty = receiverCounty;
    }

    public String getReceiverProvince() {
        return receiverProvince;
    }

    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }

    /**
     * @return RECEIVER_CITY
     */
    public String getReceiverCity() {
        return receiverCity;
    }

    /**
     * @param receiverCity
     */
    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    /**
     * @return RECEIVER_PHONE
     */
    public String getReceiverPhone() {
        return receiverPhone;
    }

    /**
     * @param receiverPhone
     */
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    /**
     * @return RECEIVER_NAME
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * @param receiverName
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}