package test.com.lpmas.weixin.api.receive;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.lpmas.framework.util.XmlKit;
import com.lpmas.weixin.api.bean.material.NewsInfoBean;
import com.lpmas.weixin.api.bean.receive.reply.ReplyImageBean;
import com.lpmas.weixin.api.bean.receive.reply.ReplyMusicBean;
import com.lpmas.weixin.api.bean.receive.reply.ReplyNewsBean;
import com.lpmas.weixin.api.bean.receive.reply.ReplyTextBean;
import com.lpmas.weixin.api.bean.receive.reply.ReplyVideoBean;
import com.lpmas.weixin.api.bean.receive.reply.ReplyVoiceBean;

public class TestReplyMessageProcessor {
	@Test
	public void test(){
		ReplyVoiceBean replyVoiceBean = new ReplyVoiceBean();
		System.out.println(XmlKit.toXml(replyVoiceBean));
		
		ReplyVideoBean replyVideoBean = new ReplyVideoBean();
		System.out.println(XmlKit.toXml(replyVideoBean));
		
		ReplyImageBean replyImageBean = new ReplyImageBean();
		System.out.println(XmlKit.toXml(replyImageBean));
		
		ReplyTextBean replyTextBean = new ReplyTextBean();
		System.out.println(XmlKit.toXml(replyTextBean));
		
		ReplyMusicBean replyMusicBean = new ReplyMusicBean();
		System.out.println(XmlKit.toXml(replyMusicBean));
		
		ReplyNewsBean replyNewsBean = new ReplyNewsBean();
		NewsInfoBean newsInfoBean = new NewsInfoBean();
		NewsInfoBean newsInfoBeanA = new NewsInfoBean();
		
		List<NewsInfoBean> articleList = new ArrayList<NewsInfoBean>();
		articleList.add(newsInfoBean);
		articleList.add(newsInfoBeanA);
				
		replyNewsBean.setArticleList(articleList);
		System.out.println(XmlKit.toXml(replyNewsBean));
	}
}
