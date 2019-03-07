package Weather_App;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

//Springboot controller for newUserReg.html page. Returns index webpage when '/newUserReg' is accessed by user.
@Controller
public class main_Controller {
    //Create a new database object to connect with the DB.
    Database dbObject = new Database();

    //newUserReg webpage with user class attributes linked to the form input tags.
    @GetMapping("/newUserReg")
    public String greeting( Model model) {
        model.addAttribute("user", new User());
        return "newUserReg";

    }


    //After user enters data, we validate the given data. Return the same page if error exists in data entered. Else, return results.
    @PostMapping("/newUserReg")
    public String result(@Valid User user, BindingResult bindingResult){
        int zipSearcher = user.getZip();
        if(user.getName().isEmpty()){
            user.setName("Stranger");
        }

        //Return the same page if error in data exists.
        if(bindingResult.hasErrors()){

            return "newUserReg";
        }
        
        //Connect with openweather API to get weather data using zip entered by user. This zip is stored in zipSearcher.
        String url = "https://api.openweathermap.org/data/2.5/weather?zip="+zipSearcher+",us&units=imperial&APPID=813952d1da48dc8341125740b7112339";
        RestTemplate restTemplate = new RestTemplate();
        //Map the JSON returned by API to weatherData class object.
        weatherData weatherdata = restTemplate.getForObject(url, weatherData.class);
        
        //Set this weather data to the user. Store the values in the DB and return results webpage to the user.
        user.setWeather(weatherdata);
        dbObject.addData("weatherDB", user.getName(), user.getEmailid(), user.getZip(), user.getWeather().getCityName(),
                user.getWeather().getCityId(),user.getWeather().getMain().getTemp(),user.getWeather().getMain().getTemp_min(),
                user.getWeather().getMain().getTemp_max(),user.getWeather().getMain().getPressure(),user.getWeather().getMain().getHumidity());
        return "result";
    }

}
