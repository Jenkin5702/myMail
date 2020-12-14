package com.tju.myMailServer.server;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.tju.myMailServer.entities.Mail;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBAgent {
    MongoClient mongoClient;
    MongoDatabase mongoDatabase;
    MongoCollection<Document> collection;
    public DBAgent(String col){
        // 连接到 mongodb 服务
        mongoClient = new MongoClient( "127.0.0.1" , 27017 );
        mongoDatabase = mongoClient.getDatabase("mail");
        collection = mongoDatabase.getCollection(col);
    }

    public String insert(String json) {
        Document document = Document.parse(json);
        List<Document> documents = new ArrayList<>();
        documents.add(document);
        collection.insertMany(documents);
        return "inserted:" + json;
    }

    public String load(Map<String, Object> kv) {
        FindIterable<Document> findIterable = collection.find();
        for(String k: kv.keySet()){
            findIterable = findIterable.filter(Filters.eq(k, kv.get(k)));
        }
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        List<String> resultSet=new ArrayList<>();
        while(mongoCursor.hasNext()){
            Document d=mongoCursor.next();
            resultSet.add(
                    new Mail(
                            d.getString("title"),
                            d.getString("from"),
                            d.getString("to"),
                            d.getLong("time"),
                            d.getString("suppliment"),
                            d.getString("content")
                    ).toString()
            );
        }
        System.out.println(resultSet.toString());
        return resultSet.toString();
    }

    public String delete(Map<String, Object> kv) {
        List<Bson> conditions = new ArrayList<>();
        for(String k: kv.keySet()){
            conditions.add(Filters.eq(k, kv.get(k)));
        }
        collection.deleteOne(Filters.and(conditions));
        return "deleted:" + kv.toString();
    }

    public String update(Map<String, Object> kv, String after){
        List<Bson> conditions = new ArrayList<>();
        for(String k: kv.keySet()){
            conditions.add(Filters.eq(k, kv.get(k)));
        }
        collection.updateMany(Filters.and(conditions), new Document("$set",Document.parse(after)));
        return "updated" + kv.toString() + after;
    }
}
