package com.consumer;

import java.io.IOException;

import org.apache.commons.lang3.SerializationUtils;

import com.alibaba.fastjson.JSON;
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
		
		// 创建队列消费者 
		QueueingConsumer consumer = new QueueingConsumer(channel);
		
		//设置最大服务消息接收数量 
		channel.basicQos(10); 
		boolean ack = false; // 是否自动确认消息被成功消费
		channel.queueDeclare(MqTest.queueName, true, false, true, null);
		//路由键exchange设置
//		channel.exchangeDeclare("test_topic", "fanout");
//		channel.queueDelete(MqTest.queueName);
//		channel.queueBind(MqTest.queueName, "test_topic", MqTest.queueName);
		
		channel.basicConsume("communication_queue", ack, consumer);
		System.err.println("wait message ......");
		while(true){
			try {
				QueueingConsumer.Delivery delivery = consumer.nextDelivery();
				Object obj = MqTest.converToObj(delivery.getBody());
				obj = JSON.parseObject(com.alibaba.dubbo.common.json.JSON.json(obj));
//				String message = new String(delivery.getBody());
//				Object obj = MqTest.converToObj(delivery.getBody());
//				Tree message = (Tree) MqTest.converToObj(delivery.getBody());
				System.out.println(obj);
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
