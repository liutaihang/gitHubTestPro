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
		String message = "��Ϣ����";
		Tree tree = new Tree("����", new BigDecimal(100), "������");
		
		Connection connection = MqTest.getClient();
		Channel channel = connection.createChannel();
		channel.queueDeclare(MqTest.queueName, true, false, true, null);
		//·�ɼ�exchange����exchangeTest
		channel.exchangeDeclare("test_topic", "fanout");
//		channel.queueDelete(MqTest.queueName);
		channel.queueBind(MqTest.queueName, "test_topic", MqTest.queueName);
		
		channel.basicPublish("test_topic", MqTest.queueName, MessageProperties.PERSISTENT_TEXT_PLAIN, MqTest.converTobyte(tree));
		System.out.println("��Ϣ���ͳɹ�");
		
		MqTest.close(channel, connection);
	}
}
