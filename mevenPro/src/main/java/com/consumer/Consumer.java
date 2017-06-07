package com.consumer;

import java.io.IOException;

import com.mq.MqTest;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

import po.Tree;

public class Consumer {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Connection connection = MqTest.getClient();
		Channel channel = connection.createChannel();
		
		// �������������� 
		QueueingConsumer consumer = new QueueingConsumer(channel);
		
		//������������Ϣ�������� 
		channel.basicQos(10); 
		boolean ack = false; // �Ƿ��Զ�ȷ����Ϣ���ɹ�����
		channel.queueDeclare(MqTest.queueName, true, false, true, null);
		//·�ɼ�exchange����
//		channel.exchangeDeclare("test_topic", "fanout");
//		channel.queueDelete(MqTest.queueName);
//		channel.queueBind(MqTest.queueName, "test_topic", MqTest.queueName);
		
		channel.basicConsume(MqTest.queueName, ack, consumer);
		System.err.println("wait message ......");
		while(true){
			try {
				QueueingConsumer.Delivery delivery = consumer.nextDelivery();
//				String message = new String(delivery.getBody());
				Tree message = (Tree) MqTest.converToObj(delivery.getBody());
				System.out.println(message);
			} catch (ShutdownSignalException e) {
				e.printStackTrace();
			} catch (ConsumerCancelledException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
