package com.consumer;

import java.io.IOException;

import com.mq.MqTest;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class Consumeri {
	public static void main(String[] args) {
		try {
			Connection connection = MqTest.getClient();
			Channel channel = connection.createChannel();
			channel.queueDeclare(MqTest.queueName, true, false, true, null);
			
			channel.exchangeDeclare("exchange_test", "fanout");
			
			QueueingConsumer consumer = new QueueingConsumer(channel);
			
			channel.basicConsume(MqTest.queueName, false, consumer);
			System.out.println("吉棋��連。。。。。。。。。to two");
			while(true){
				QueueingConsumer.Delivery delivery = consumer.nextDelivery();
				Object obj = MqTest.converToObj(delivery.getBody());
				System.out.println(obj);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ShutdownSignalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConsumerCancelledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
