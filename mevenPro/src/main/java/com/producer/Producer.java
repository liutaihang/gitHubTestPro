package com.producer;

import java.io.IOException;

import com.mq.MqTest;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

public class Producer {
	public static void main(String[] args) throws IOException {
		String message = "��Ϣ����";
		Connection connection = MqTest.getClient();
		Channel channel = connection.createChannel();
		channel.queueDeclare(MqTest.queueName, true, false, true, null);
		channel.basicPublish("", MqTest.queueName, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
		System.out.println("��Ϣ���ͳɹ�");
		
		MqTest.close(channel, connection);
		System.out.println("���ӹر�");
	}
}
