package com;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class MongoTest {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Mongo mongo = new Mongo("localhost:27017");
		DB db = mongo.getDB("testPro");
		DBCollection collection = db.getCollection("people");
		DBCursor cursor = collection.find();
		DBObject dbObject = cursor.next();
		System.out.println(dbObject.get("name"));
		
		BasicDBObject object = new BasicDBObject();
		
	}
}