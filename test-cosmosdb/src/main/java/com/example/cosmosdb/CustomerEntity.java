package com.example.cosmosdb;

import com.microsoft.azure.storage.table.TableServiceEntity;

import java.util.Date;



/**
 * @author xboat date 2019-12-14
 */
public class CustomerEntity extends TableServiceEntity {
    CustomerEntity(String lastName, String firstName) {
        this.partitionKey = lastName;
        this.rowKey = firstName;
    }

    public CustomerEntity() { }

    public String ctnNo;

    public String confirmBatchNo;

    public Date createTime;

    public String createUserId;

    public Date createUserName;

    public String hawbNo;

    public String poNo;

    public Date updateTime;

    public String updateUserId;

    public String updateUserName;

    public String getCtnNo() {
        return ctnNo;
    }

    public void setCtnNo(String ctnNo) {
        this.ctnNo = ctnNo;
    }

    public String getConfirmBatchNo() {
        return confirmBatchNo;
    }

    public void setConfirmBatchNo(String confirmBatchNo) {
        this.confirmBatchNo = confirmBatchNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(Date createUserName) {
        this.createUserName = createUserName;
    }

    public String getHawbNo() {
        return hawbNo;
    }

    public void setHawbNo(String hawbNo) {
        this.hawbNo = hawbNo;
    }

    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }
}
