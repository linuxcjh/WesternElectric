package com.nuoman.westernele.numberDetail.model;

/**
 * 工号查询获得的基本信息实体类
 * Created by 杨小过 on 2016/11/22.
 */

public class BasicInformation {

    /*
    {
        "contractDeliveryDate":"2018.01.01",
        "contractNo":"",
        "contractSignDate":"2016.01.01",
        "innerManager":"",
        "innerManagerTel":"",
        "orderId":"1",
        "orderNo":"XD0000000001",
        "payProcess":"",
        "payType":"",
        "productType":"",
        "projectLevel":"",
        "saleManager":"",
        "saleManagerTel":"",
        "sendDate":"2017.01.01",
        "warrantyExplaination":""
    }
     */

    private String contractDeliveryDate;
    private String contractNo;
    private String contractSignDate;
    private String innerManager;
    private String innerManagerTel;
    private String orderId;
    private String orderNo;
    private String payProcess;
    private String payType;
    private String productType;
    private String projectLevel;
    private String saleManager;
    private String saleManagerTel;
    private String sendDate;
    private String warrantyExplaination;

    public String getContractDeliveryDate() {
        return contractDeliveryDate;
    }

    public void setContractDeliveryDate(String contractDeliveryDate) {
        this.contractDeliveryDate = contractDeliveryDate;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractSignDate() {
        return contractSignDate;
    }

    public void setContractSignDate(String contractSignDate) {
        this.contractSignDate = contractSignDate;
    }

    public String getInnerManager() {
        return innerManager;
    }

    public void setInnerManager(String innerManager) {
        this.innerManager = innerManager;
    }

    public String getInnerManagerTel() {
        return innerManagerTel;
    }

    public void setInnerManagerTel(String innerManagerTel) {
        this.innerManagerTel = innerManagerTel;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPayProcess() {
        return payProcess;
    }

    public void setPayProcess(String payProcess) {
        this.payProcess = payProcess;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProjectLevel() {
        return projectLevel;
    }

    public void setProjectLevel(String projectLevel) {
        this.projectLevel = projectLevel;
    }

    public String getSaleManager() {
        return saleManager;
    }

    public void setSaleManager(String saleManager) {
        this.saleManager = saleManager;
    }

    public String getSaleManagerTel() {
        return saleManagerTel;
    }

    public void setSaleManagerTel(String saleManagerTel) {
        this.saleManagerTel = saleManagerTel;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getWarrantyExplaination() {
        return warrantyExplaination;
    }

    public void setWarrantyExplaination(String warrantyExplaination) {
        this.warrantyExplaination = warrantyExplaination;
    }
}
