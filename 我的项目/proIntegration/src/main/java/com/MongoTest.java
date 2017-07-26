package com;

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
	}
}
