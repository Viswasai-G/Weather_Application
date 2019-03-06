package Weather_App;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.model.*;
import com.google.gson.Gson;



import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Database {
    //new ProfileCredentialsProvider("default")
    AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_WEST_2).build();
    
    //To use DynamoDb locally, set up a local DynamoDb and uncomment the below lines of code. 
    //Comment out the above connection to DynamoDB web service
    

    //AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
    //.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
    //.build();

    DynamoDB dynamoDB = new DynamoDB(client);

    String tableName = "weatherDB";

    public void createTable(){

        try{
            System.out.println("Attempting to create table. Please Wait...");
            Table table = dynamoDB.createTable(tableName, Arrays.asList(new KeySchemaElement("Email", KeyType.HASH)),
                    Arrays.asList(new AttributeDefinition("Email", ScalarAttributeType.S)),
                    new ProvisionedThroughput(10L,10L));
            table.waitForActive();
            System.out.println("Success..Table status:"+ table.getDescription().getTableStatus());
        }
        catch (Exception e){
            System.err.println("Error creating table:");
            System.err.println(e.getMessage());
        }
    }

    public void addData(String tableName, String userName,String Email, int zip, String city, Double cityId,
                        Double temp, Double temp_min, Double temp_max, Double pressure, Double humid){

        Table table = dynamoDB.getTable(tableName);

        try{
            System.out.println("Adding new item to DB...");

            final Map<String, Object> weatherInfo = new HashMap<String, Object>();
            weatherInfo.put("temp", temp);
            weatherInfo.put("temp_min", temp_min);
            weatherInfo.put("temp_max", temp_max);
            weatherInfo.put("pressure", pressure);
            weatherInfo.put("humidity", humid);

            final Map<String, Object> cityInfo= new HashMap<String, Object>();
            cityInfo.put("cityName", city);
            cityInfo.put("cityId", cityId);
            cityInfo.put("main", weatherInfo);

            PutItemOutcome outcome = table.putItem(new Item().withPrimaryKey("Email", Email ).withInt("zip", zip).with("name",
                    userName).withMap("weather", cityInfo));
            System.out.println("Success: "+outcome.getPutItemResult());
        }
        catch (Exception e){
            System.err.println("Failed: ");
            System.err.println(e.getMessage());
        }
    }

    public User readData(String tableName, String Email){

        User userDetails = new User();
        userDetails.setEmailid(Email);

        Table table = dynamoDB.getTable(tableName);

        GetItemSpec spec = new GetItemSpec().withPrimaryKey("Email",Email);

        try{
            System.out.println("Attempting to read:");
            Gson g = new Gson();
            String results = table.getItem(spec).toJSON();
            userDetails = g.fromJson(results, User.class);

        }
        catch (Exception e){
            System.out.println("Unable to read item.." + e.getMessage());
        }
        return userDetails;
    }
}
