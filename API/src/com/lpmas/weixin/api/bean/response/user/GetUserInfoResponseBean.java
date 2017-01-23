package com.lpmas.weixin.api.bean.response.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;

public class GetUserInfoResponseBean extends WxResponseBaseBean {

	// 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
	private int subscribe;

	// 用户的标识，对当前公众号唯一
	@JsonProperty("openid")
	private String openId;

	// 用户的昵称
	@JsonProperty("nickname")
	private String nickName;

	// 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	private int sex;

	// 用户所在城市
	private String city;

	// 用户所在国家。
	private String country;

	// 用户的语言，简体中文为zh_CN
	private String province;

	// 用户所在城市
	private String language;

	// 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
	@JsonProperty("headimgurl")
	private String headImageUrl;

	// 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	@JsonProperty("subscribe_time")
	private long subscribeTime;

	// 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
	@JsonProperty("unionid")
	private String unionId;

	private String remark;

	@JsonProperty("groupid")
	private int groupId;

	@JsonProperty("tagid_list")
	private List<Long> tagIdList;

	public int getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getHeadImageUrl() {
		return headImageUrl;
	}

	public void setHeadImageUrl(String headImageUrl) {
		this.headImageUrl = headImageUrl;
	}

	public long getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(long subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public List<Long> getTagIdList() {
		return tagIdList;
	}

	public void setTagIdList(List<Long> tagIdList) {
		this.tagIdList = tagIdList;
	}
}
