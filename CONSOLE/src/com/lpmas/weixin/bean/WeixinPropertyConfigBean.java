package com.lpmas.weixin.bean;

import java.sql.Timestamp;

import com.lpmas.framework.annotation.FieldTag;

public class WeixinPropertyConfigBean {
	@FieldTag(name = "属性配置ID")
	private int propertyId = 0;
	@FieldTag(name = "类型")
	private int propertyType = 0;
	@FieldTag(name = "公众号ID")
	private int accountId = 0;
	@FieldTag(name = "属性关键字1")
	private String propertyKey1 = "";
	@FieldTag(name = "属性关键字2")
	private String propertyKey2 = "";
	@FieldTag(name = "属性关键字3")
	private String propertyKey3 = "";
	@FieldTag(name = "属性关键字4")
	private String propertyKey4 = "";
	@FieldTag(name = "属性关键字5")
	private String propertyKey5 = "";
	@FieldTag(name = "属性值1")
	private String propertyValue1 = "";
	@FieldTag(name = "属性值2")
	private String propertyValue2 = "";
	@FieldTag(name = "属性值3")
	private String propertyValue3 = "";
	@FieldTag(name = "状态")
	private int status = 0;
	@FieldTag(name = "创建时间")
	private Timestamp createTime = null;
	@FieldTag(name = "创建用户")
	private int createUser = 0;
	@FieldTag(name = "修改时间")
	private Timestamp modifyTime = null;
	@FieldTag(name = "修改用户")
	private int modifyUser = 0;
	@FieldTag(name = "备注")
	private String memo = "";

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public int getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(int propertyType) {
		this.propertyType = propertyType;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getPropertyKey1() {
		return propertyKey1;
	}

	public void setPropertyKey1(String propertyKey1) {
		this.propertyKey1 = propertyKey1;
	}

	public String getPropertyKey2() {
		return propertyKey2;
	}

	public void setPropertyKey2(String propertyKey2) {
		this.propertyKey2 = propertyKey2;
	}

	public String getPropertyKey3() {
		return propertyKey3;
	}

	public void setPropertyKey3(String propertyKey3) {
		this.propertyKey3 = propertyKey3;
	}

	public String getPropertyKey4() {
		return propertyKey4;
	}

	public void setPropertyKey4(String propertyKey4) {
		this.propertyKey4 = propertyKey4;
	}

	public String getPropertyKey5() {
		return propertyKey5;
	}

	public void setPropertyKey5(String propertyKey5) {
		this.propertyKey5 = propertyKey5;
	}

	public String getPropertyValue1() {
		return propertyValue1;
	}

	public void setPropertyValue1(String propertyValue1) {
		this.propertyValue1 = propertyValue1;
	}

	public String getPropertyValue2() {
		return propertyValue2;
	}

	public void setPropertyValue2(String propertyValue2) {
		this.propertyValue2 = propertyValue2;
	}

	public String getPropertyValue3() {
		return propertyValue3;
	}

	public void setPropertyValue3(String propertyValue3) {
		this.propertyValue3 = propertyValue3;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public int getCreateUser() {
		return createUser;
	}

	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}

	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public int getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(int modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}