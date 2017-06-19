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
		//·�ɼ�exchange����exchangeTest   ����������
		channel.exchangeDeclare("test_topic", "direct");
//		channel.queueDelete(MqTest.queueName);
		
		//�󶨶���
		channel.queueBind(MqTest.queueName, "test_topic", MqTest.queueName);
		
		channel.basicPublish("test_topic", MqTest.queueName, MessageProperties.PERSISTENT_TEXT_PLAIN, MqTest.converTobyte(tree));
		System.out.println("��Ϣ���ͳɹ�");
		
		MqTest.close(channel, connection);
		
		
//-------------------------------------------------------------------------------------------------		
		
//		Tree tree1 = new Tree("����1", new BigDecimal(100), "������1");
		
		Connection connection1 = MqTest.getClient();
		Channel channel1 = connection1.createChannel();
		
		//��������
		channel1.queueDeclare(MqTest.queueName, true, false, true, null);
		
		//·�ɼ�exchange����exchangeTest   ����������
		channel1.exchangeDeclare("exchange_test", "fanout");
//		channel.queueDelete(MqTest.queueName);
		channel1.queueBind(MqTest.queueName, "exchange_test", MqTest.queueName);
		
		for(int i = 0; i < 10; i++){
			channel1.basicPublish("exchange_test", MqTest.queueName, MessageProperties.PERSISTENT_TEXT_PLAIN,
					MqTest.converTobyte(new Tree("����"+i, new BigDecimal(100), "������"+i)));
			System.out.println("��Ϣ���ͳɹ�" + i);
		}
		
	
		
		MqTest.close(channel1, connection1);
	}
}
