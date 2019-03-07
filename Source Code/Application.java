package Weather_App;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        
        //Create the database and run the application
        
        Database firstdb = new Database();
        firstdb.createTable();
        SpringApplication.run(Application.class, args);
    }

}
