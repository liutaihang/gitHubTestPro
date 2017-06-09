package com.producer;

import java.io.IOException;
import java.math.BigDecimal;

import com.mq.MqTest;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import po.Tree;

public class Producer {
	public static void main(String[] args) throws IOException {
		String message = "消息传递";
		Tree tree = new Tree("古松", new BigDecimal(100), "观赏树");
		
		Connection connection = MqTest.getClient();
		Channel channel = connection.createChannel();
		channel.queueDeclare(MqTest.queueName, true, false, true, null);
		//路由键exchange设置exchangeTest
		channel.exchangeDeclare("test_topic", "fanout");
//		channel.queueDelete(MqTest.queueName);
		channel.queueBind(MqTest.queueName, "test_topic", MqTest.queueName);
		
		channel.basicPublish("test_topic", MqTest.queueName, MessageProperties.PERSISTENT_TEXT_PLAIN, MqTest.converTobyte(tree));
		System.out.println("消息发送成功");
		
		MqTest.close(channel, connection);
	}
}
