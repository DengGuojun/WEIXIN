package com.lpmas.weixin.bean;

import java.sql.Timestamp;

import com.lpmas.framework.annotation.FieldTag;

public class WeixinAccountInfoBean {
	@FieldTag(name = "公众号ID")
	private int accountId = 0;
	@FieldTag(name = "公众号名称")
	private String accountName = "";
	@FieldTag(name = "公众号代码")
	private String accountCode = "";
	@FieldTag(name = "微信用户ID")
	private String weixinUserId = "";
	@FieldTag(name = "微信号")
	private String weixinAccountId = "";
	@FieldTag(name = "公众号类型")
	private int accountType = 0;
	@FieldTag(name = "应用ID")
	private int appId = 0;
	@FieldTag(name = "商店ID")
	private int storeId = 0;
	@FieldTag(name = "用户组ID")
	private int groupId = 0;
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

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getWeixinUserId() {
		return weixinUserId;
	}

	public void setWeixinUserId(String weixinUserId) {
		this.weixinUserId = weixinUserId;
	}

	public String getWeixinAccountId() {
		return weixinAccountId;
	}

	public void setWeixinAccountId(String weixinAccountId) {
		this.weixinAccountId = weixinAccountId;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
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
