package Weather_App;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



import javax.validation.Valid;

//Springboot controller for returningUser.html page. Returns index webpage when '/returningUser' is accessed by user.
@Controller
public class main_Controller2 {
    //Create a new database object to connect with the DB.
    Database dbObject = new Database();

    //newUserReg webpage with user class attributes linked to the form input tags.
    @GetMapping("/returningUser")
    public String greeting( Model model) {
        model.addAttribute("user", new User());
        return "returningUser";

    }

    //After user enters data, we validate the given data. Return the same page if error exists in data entered. Else, return results.
    @PostMapping("/returningUser")
    public String result(@Valid User user, BindingResult bindingResult){
        String Email_val = user.getEmailid();

        //Return same page if error in data exists
        if(bindingResult.hasErrors()){
            if(bindingResult.hasFieldErrors("emailid")){
                return "returningUser";
            }
        }
        
        //Read data from DB using the primary key 'Email' entered by the user.
        User temp_user = dbObject.readData("weatherDB", Email_val);
    
        //Set the outcome to user object so that the webpage can access the results.
        user.setName(temp_user.getName());
        user.setZip(temp_user.getZip());
        user.setWeather(temp_user.getWeather());
/*

    To get Updated weather results, Connect to the API with the zip information stored in DB and get results....

        int zipSearcher = temp_user.getZip();
        String url = "https://api.openweathermap.org/data/2.5/weather?zip="+zipSearcher+",us&units=imperial&APPID=813952d1da48dc8341125740b7112339";
        RestTemplate restTemplate = new RestTemplate();
        weatherData weatherdata = restTemplate.getForObject(url, weatherData.class);
        user.setWeather(weatherdata);
        dbObject.addData("weatherDB", user.getName(), user.getEmailid(), user.getZip(), user.getWeather().getCityName(), user.getWeather().getCityId(),user.getWeather().getMain().getTemp(),user.getWeather().getMain().getTemp_min(),user.getWeather().getMain().getTemp_max(),user.getWeather().getMain().getPressure(),user.getWeather().getMain().getHumidity());
*/
        return "result";
    }

}
