package com;

import java.io.IOException;
import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mq.MqTest;

import po.Tree;
@Component
public class MongoTest {
	
	@Resource
	private MqTest mTest;
	
	public static void main(String[] args) {
		Mongo mongo = new Mongo("localhost:27017");
		DB db = mongo.getDB("testPro");
		DBCollection collection = db.getCollection("people");
		DBCursor cursor = collection.find();
		DBObject dbObject = cursor.next();
		System.out.println(dbObject);
		
		collection = db.getCollection("tree");
		Tree tree = new Tree("name", new BigDecimal(3), "type");
		BasicDBObject object = (BasicDBObject) com.mongodb.util.JSON.parse(JSON.toJSON(tree).toString());
//		collection.save(object);
//		tree = (Tree) collection.findOne(object);
//		System.out.println(tree);
		BasicDBList list = new BasicDBList();
		list.add(new BasicDBObject("this", "ss"));
		System.out.println(new BasicDBObject("$or", list));
		/**
		 * [-84, -19, 0, 5, 116, 0, 51, 76, 89, 67, 95, 65, 85, 84, 72, 95, 85, 83, 69, 82, 95, 73, 
		 * 78, 70, 79, 95, 48, 50, 101, 99, 99, 102, 53, 55, 48, 97, 52, 50, 51, 
		 * 48, 56, 101, 102, 49, 100, 56, 101, 50, 100, 97, 53, 51, 50, 53, 101, 98, 49, 100]
		 */
		
//		try {
//			byte[] by = MqTest.converTobyte("LYC_AUTH_USER_INFO_02eccf570a42308ef1d8e2da5325eb1d");
//			System.out.println(by.toString());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		System.out.println(7 % 2);
	}
}
