package com.mq;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MqTest {
	public static final String queueName = "test";
	
	public static Connection getClient() throws IOException{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername("root");
		factory.setPassword("root");
		factory.setVirtualHost("newHosts");
		factory.setHost("192.168.0.111");
//		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		return connection;
	}
	
	public static void close(Channel channel, Connection connection) throws IOException{
		if(channel != null){
			channel.close();
		}
		if(connection != null){
			connection.close();
		}
	}
	
	public byte[] converTobyte(Object obj) throws IOException{
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ObjectOutputStream object = new ObjectOutputStream(stream);
		object.writeObject(obj);
		byte[] bytes = stream.toByteArray();
		return bytes;
	}
	
	public Object converToObj(byte[] bytes) throws IOException, ClassNotFoundException{
		ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
		ObjectInputStream object = new ObjectInputStream(stream);
		return object.readObject();
	}
}
