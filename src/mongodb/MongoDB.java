
package mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Projections.include;
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
public void amosar1(){
    MongoClient mongoClient = new MongoClient("localhost", 27017);
    MongoDatabase database = mongoClient.getDatabase("tenda");
    MongoCollection<Document> collection = database.getCollection("pedidos");
    
     Document doc = new Document();

        doc = collection.find(eq("_id", "p3")).first();

        System.out.println(doc);

}
public void amosar2(){
    MongoClient mongoClient = new MongoClient("localhost", 27017);
    MongoDatabase database = mongoClient.getDatabase("tenda");
    MongoCollection<Document> collection = database.getCollection("pedidos");
    
    Document doc = new Document();
     doc = collection.find(eq("_id", "p1")).projection(include("codcli", "codpro", "cantidade")).first();
     System.out.println(doc);
}
public void amosar3(){
    MongoClient mongoClient = new MongoClient("localhost", 27017);
    MongoDatabase database = mongoClient.getDatabase("tenda");
    MongoCollection<Document> collection = database.getCollection("pedidos");
    
    // Necesitamos varios doc de esta collecion
    // Un iterator permite recorre una collecion, y asi leer multiples elementos
    // Todo doc cuya cantidad sea superior a 2, que nos devuelva el codcli y el codpro
    FindIterable<Document> doc = collection.find(gt("cantidade", 2)).projection(include("codcli", "codpro"));
    
    // Vamos recorreindo el iterartor
    // Se almacena en las variables cada uno de los valores que nos devuelve de codcli y cod pro
    for (Document d: doc){
        String codcli = d.getString("codcli");
        String codpro = d.getString("codpro");
        
        //Imprimiendo el resultado
        System.out.println("Pedido de codcli " + codcli + " y codpro de " + codpro );
    }
}

public void amosar4(){
    // Similar al anterio
    // Ahora necesitamos los que tenga cantidad entre 2 y 5, las dos a la vez
    // Como es doble condicion se pone el and
     MongoClient mongoClient = new MongoClient("localhost", 27017);
    MongoDatabase database = mongoClient.getDatabase("tenda");
    MongoCollection<Document> collection = database.getCollection("pedidos");
    
    FindIterable<Document> doc = collection.find(and(gt("cantidade",2),lt("cantidade",5))).projection(include("codcli","codpro"));
    
    for (Document d:doc){
        String codcli = d.getString("codcli");
        String codpro = d.getString("codpro");
        
        System.out.println("Peidos de codcli " + codcli  + " y codpro " + codpro);
    }
}

public void amosar5(){
    
}

          

    public static void main(String[] args) {
      MongoDB mongo = new MongoDB();
//      mongo.insertarDocumento();
//      mongo.actualizarDocumento();
      
    
     // mongo.incDocumento();
     //  mongo.borrarDocument();
      mongo.amosar1();
      mongo.amosar2();
      mongo.amosar3();
      mongo.amosar4();  
       
   
    
}
}
