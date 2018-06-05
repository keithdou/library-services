package pcs.libraryservices;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 *
 * @author keithdou
 */
public class MongoClientProvider {
    
    private static final MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
    
    protected MongoClientProvider() {}
    
    public static MongoClient getClient() {
        return mongoClient;
    }
    
}
