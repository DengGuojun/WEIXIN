package com.lpmas.weixin.client.factory;

import com.lpmas.framework.mq.MQFactory;
import com.lpmas.framework.mq.MQObject;
import com.lpmas.framework.mq.MQSender;
import com.lpmas.framework.mq.activemq.ActiveMQObject;
import com.lpmas.framework.mq.activemq.ActiveMQSender;
import com.lpmas.weixin.client.config.WeixinMQConfig;

public class WeixinMQFactory extends MQFactory {

	@Override
	public MQObject getMQObject() {
		return new ActiveMQObject(WeixinMQConfig.BROKER_ID);
	}

	@Override
	public MQSender getMQSender() {
		return new ActiveMQSender(WeixinMQConfig.BROKER_ID);
	}

}
