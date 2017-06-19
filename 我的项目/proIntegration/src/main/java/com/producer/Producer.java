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
		//路由键exchange设置exchangeTest   申明交换机
		channel.exchangeDeclare("test_topic", "direct");
//		channel.queueDelete(MqTest.queueName);
		
		//绑定队列
		channel.queueBind(MqTest.queueName, "test_topic", MqTest.queueName);
		
		channel.basicPublish("test_topic", MqTest.queueName, MessageProperties.PERSISTENT_TEXT_PLAIN, MqTest.converTobyte(tree));
		System.out.println("消息发送成功");
		
		MqTest.close(channel, connection);
		
		
//-------------------------------------------------------------------------------------------------		
		
//		Tree tree1 = new Tree("古松1", new BigDecimal(100), "观赏树1");
		
		Connection connection1 = MqTest.getClient();
		Channel channel1 = connection1.createChannel();
		
		//申明队列
		channel1.queueDeclare(MqTest.queueName, true, false, true, null);
		
		//路由键exchange设置exchangeTest   申明交换机
		channel1.exchangeDeclare("exchange_test", "fanout");
//		channel.queueDelete(MqTest.queueName);
		channel1.queueBind(MqTest.queueName, "exchange_test", MqTest.queueName);
		
		for(int i = 0; i < 10; i++){
			channel1.basicPublish("exchange_test", MqTest.queueName, MessageProperties.PERSISTENT_TEXT_PLAIN,
					MqTest.converTobyte(new Tree("古松"+i, new BigDecimal(100), "观赏树"+i)));
			System.out.println("消息发送成功" + i);
		}
		
	
		
		MqTest.close(channel1, connection1);
	}
}
