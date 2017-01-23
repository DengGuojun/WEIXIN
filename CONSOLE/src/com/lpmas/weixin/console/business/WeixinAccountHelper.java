package com.lpmas.weixin.console.business;

import java.util.List;
import java.util.Map;

import com.lpmas.admin.bean.AdminGroupInfoBean;
import com.lpmas.admin.business.AdminUserHelper;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.weixin.bean.WeixinAccountInfoBean;

public class WeixinAccountHelper {

	private List<AdminGroupInfoBean> groupList;
	private List<WeixinAccountInfoBean> accountList;
	private Map<Integer, String> accountCodeMap;
	private Map<String, Integer> accountIdMap;
	private Map<Integer, WeixinAccountInfoBean> accountInfoMap;

	public WeixinAccountHelper(AdminUserHelper adminUserHelper) {
		groupList = adminUserHelper.getUserGroupList();

		WeixinAccountInfoBusiness weixinAccountInfoBusiness = new WeixinAccountInfoBusiness();
		accountList = weixinAccountInfoBusiness.getWeixinAccountInfoListByGroupList(groupList);
		accountCodeMap = ListKit.list2Map(accountList, "accountId", "accountCode");
		accountIdMap = ListKit.list2Map(accountList, "accountCode", "accountId");
		accountInfoMap = ListKit.list2Map(accountList, "accountId");
	}

	public ReturnMessageBean hasPermission(int accountId) {
		ReturnMessageBean returnMessage = new ReturnMessageBean();
		if (groupList.isEmpty()) {
			returnMessage.setMessage("对不起您不属于任何用户组,无权查看");
			return returnMessage;
		}

		if (!accountCodeMap.containsKey(accountId)) {
			returnMessage.setMessage("公众号ID非法");
			return returnMessage;
		}
		returnMessage.setCode(Constants.STATUS_VALID);
		return returnMessage;
	}

	public List<AdminGroupInfoBean> getGroupList() {
		return groupList;
	}

	public List<WeixinAccountInfoBean> getAccountList() {
		return accountList;
	}

	public Map<Integer, String> getAccountCodeMap() {
		return accountCodeMap;
	}

	public Map<String, Integer> getAccountIdMap() {
		return accountIdMap;
	}

	public Map<Integer, WeixinAccountInfoBean> getAccountInfoMap() {
		return accountInfoMap;
	}

	public boolean isInGroup() {
		return !groupList.isEmpty();
	}

	public int getDefaultAccount() {
		if (accountList.isEmpty())
			return 0;
		return accountList.get(0).getAccountId();
	}

}
