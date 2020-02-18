
package mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDB {
public void insertarDocumento(){
       
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        
        MongoDatabase database = mongoClient.getDatabase("tenda");

        
        MongoCollection<Document> collection = database.getCollection("pedidos");

        Document document = new Document();

        document.put("_id", "p4");
        document.put("codcli", "c1");
        document.put("codpro", "pro3");
        document.put("cantidade", 5);
        document.put("data", "02/02/2019");

        collection.insertOne(document);

}
public void actualizarDocumento(){
    MongoClient mongoClient = new MongoClient("localhost", 27017);
    MongoDatabase database = mongoClient.getDatabase("tenda");
    MongoCollection<Document> collection = database.getCollection("pedidos");
        BasicDBObject doc = new BasicDBObject();
        doc.put("_id", "p3");
        BasicDBObject newDoc = new BasicDBObject();
        newDoc.put("codpro", "pro5");

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDoc);

        collection.updateOne(doc, updateObject);

}
public void incDocumento(){
    MongoClient mongoClient = new MongoClient("localhost", 27017);
    MongoDatabase database = mongoClient.getDatabase("tenda");
    MongoCollection<Document> collection = database.getCollection("pedidos");
    BasicDBObject updateObject = new BasicDBObject();
    BasicDBObject doc = new BasicDBObject();
    doc.put("_id", "p2");

updateObject.append("$inc", new BasicDBObject().append("cantidade", 7));
collection.updateOne(doc, updateObject);
    
}

public void borrarDocument(){
      MongoClient mongoClient = new MongoClient("localhost", 27017);
    MongoDatabase database = mongoClient.getDatabase("tenda");
    MongoCollection<Document> collection = database.getCollection("pedidos");
    
    BasicDBObject doc = new BasicDBObject();
    doc.put("_id","p2");
 
    collection.deleteOne(doc);

}


          

    public static void main(String[] args) {
      MongoDB mongo = new MongoDB();
//      mongo.insertarDocumento();
//      mongo.actualizarDocumento();
      
      //METODOS DE INC Y BORRAR NO HACEN NADA, REVISAR POR QUE
     // mongo.incDocumento();
       mongo.borrarDocument();
      
        
       
   
    
}
}
