package pcs.libraryservices;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author keith
 */
@Path("/")
public class LibraryServices {

    private static final Logger LOG = LoggerFactory.getLogger(LibraryServices.class);

    private static final String[] NUMERIC_FIELDS = {"itemId", "date"};

    private static final String[] REGEX_FIELDS = {"title", "author"};

    private static final String PAGE_SIZE_PARAM = "pageSize";
    private static final String PAGE_NUMBERE_PARAM = "pageNumber";
    private static final String ORDER_BY_PARAM = "orderBy";
    private static final String SORT_DIRECTION = "sortDirection";
    private static final String[] CONTROL_PARAMS = {PAGE_NUMBERE_PARAM, PAGE_SIZE_PARAM, ORDER_BY_PARAM, SORT_DIRECTION};

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/books")
    public Response listBooks(@Context HttpServletRequest request, @Context HttpServletResponse response, @Context UriInfo ui,
            @DefaultValue("1") @QueryParam(PAGE_NUMBERE_PARAM) int pageNumber,
            @DefaultValue("16") @QueryParam(PAGE_SIZE_PARAM) int pageSize,
            @DefaultValue("date") @QueryParam(ORDER_BY_PARAM) String sortColumn,
            @DefaultValue("D") @QueryParam(SORT_DIRECTION) String sortDirection) {

        LOG.debug("getBooks starts");

        MongoClient mongoClient = MongoClientProvider.getClient();
        MongoDatabase database = mongoClient.getDatabase("library");
        MongoCollection bookCollection = database.getCollection("books", Book.class);

        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        bookCollection = bookCollection.withCodecRegistry(pojoCodecRegistry);

        LOG.debug("Extract params");
        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();

        Document query = new Document();

        queryParams.entrySet().forEach(entry -> {
            if (Arrays.asList(CONTROL_PARAMS).contains(entry.getKey())) {
                return;  // exit this LAMBDA method only
            }
            entry.getValue().forEach(val -> {
                if (Arrays.asList(NUMERIC_FIELDS).contains(entry.getKey()) && StringUtils.isNumeric(val)) {
                    query.append(entry.getKey(), Long.valueOf(val));
                } else if (Arrays.asList(REGEX_FIELDS).contains(entry.getKey())) {
                    query.append(entry.getKey(), new BasicDBObject("$regex", val));
                } else {
                    query.append(entry.getKey(), val);
                }
            });
        });

        if (!query.containsKey("itemId")) {
            // There are some invlaid item ids -we only want ints and longs
            List<Integer> validTypes = Arrays.asList(new Integer[]{16, 18});
            query.append("itemId", new BasicDBObject("$type", validTypes));
        }
        LOG.debug("Query{}", query);
        LOG.debug("Page number is {}", pageNumber);
        LOG.debug("sort by {} {}", sortColumn, sortDirection);

        List<Book> bookList = new ArrayList<>();
        long recordCount = bookCollection.count(query);
        LOG.debug("Record count={}",recordCount);

        bookCollection.find(query)
                .sort(new BasicDBObject(sortColumn, sortDirection.equals("A") ? 1 : -1))
                .skip((pageNumber - 1) * pageSize)
                .limit(pageSize)
                .forEach((Block<Book>) book -> {
                    //LOG.debug("Adding {}", book);
                    bookList.add(book);
                });
        
        return Response.ok().entity(new ListBooksResponse(recordCount, bookList)).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/languages")
    public Response listLanguages() {

        MongoClient mongoClient = MongoClientProvider.getClient();
        MongoDatabase database = mongoClient.getDatabase("library");
        
        BasicDBObject distinctCommand = new BasicDBObject()
                .append("distinct", "books")
                .append("key","language");
                
        LOG.debug("distinctCommand={}",distinctCommand);        
        
        Document languageDoc = database.runCommand(distinctCommand);
        
        LOG.debug("languages={}",languageDoc);
        
        List<String> languageList = (List) languageDoc.get("values");
        
        languageList.sort((p1, p2) -> p1.compareTo(p2));
        
        return Response.ok().entity(languageList).build();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/libraries")
    public Response listLibraries() {

        MongoClient mongoClient = MongoClientProvider.getClient();
        MongoDatabase database = mongoClient.getDatabase("library");
        
        BasicDBObject distinctCommand = new BasicDBObject()
                .append("distinct", "books")
                .append("key","checkoutLibrary");
                
        LOG.debug("distinctCommand={}",distinctCommand);        
        
        Document languageDoc = database.runCommand(distinctCommand);
        
        LOG.debug("languages={}",languageDoc);
        
        List<String> languageList = (List) languageDoc.get("values");
        
        languageList.sort((p1, p2) -> p1.compareTo(p2));
        
        return Response.ok().entity(languageList).build();
    }
}
