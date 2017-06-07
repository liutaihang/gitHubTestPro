package com.consumer;

import java.io.IOException;

import com.mq.MqTest;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class Consumer {
	public static void main(String[] args) throws IOException {
		Connection connection = MqTest.getClient();
		Channel channel = connection.createChannel();
		
		// �������������� 
		QueueingConsumer consumer = new QueueingConsumer(channel);
		
		//������������Ϣ�������� 
		channel.basicQos(10); 
		boolean ack = false; // �Ƿ��Զ�ȷ����Ϣ���ɹ�����
		channel.queueDeclare(MqTest.queueName, true, false, true, null);
		channel.basicConsume(MqTest.queueName, ack, consumer);
		while(true){
			try {
				QueueingConsumer.Delivery delivery = consumer.nextDelivery();
				String message = new String(delivery.getBody());
				System.out.println(message);
				channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
			} catch (ShutdownSignalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ConsumerCancelledException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
