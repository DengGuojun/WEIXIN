package com.lpmas.weixin.console.business;

import javax.servlet.http.HttpServletResponse;

import com.lpmas.framework.web.HttpResponseKit;

public class FancyBoxUtil {

	public static final int ACTION_HISTORY_BACK = 1;
	public static final int ACTION_WINDOW_CLOSE = 2;
	public static final int ACTION_WINDOW_REFRESH = 3;
	public static final int ACTION_NONE = 4;

	public static void alertAndClose(HttpServletResponse response, String message) {
		HttpResponseKit.printMessage(response,
				"<script>alert('" + message + "');try{ self.parent.jQuery.fancybox.close();}catch(e){}</script>");
	}

	public static void alertAndCloseAndOperateParent(HttpServletResponse response, String message, int type) {
		String operation = "";
		switch (type) {
		case ACTION_HISTORY_BACK:
			operation = "self.parent.history.back();";
			break;
		case ACTION_WINDOW_CLOSE:
			operation = "self.parent.window.close();";
		case ACTION_WINDOW_REFRESH:
			operation = "self.parent.location.reload();";
		default:
			break;
		}
		HttpResponseKit.printMessage(response, "<script>alert('" + message
				+ "');try{ self.parent.jQuery.fancybox.close();" + operation + "}catch(e){}</script>");
	}

}
