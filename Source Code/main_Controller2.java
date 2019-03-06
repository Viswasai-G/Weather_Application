package Weather_App;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



import javax.validation.Valid;


@Controller
public class main_Controller2 {
    Database dbObject = new Database();

    @GetMapping("/returningUser")
    public String greeting( Model model) {
        model.addAttribute("user", new User());
        return "returningUser";

    }

    @PostMapping("/returningUser")
    public String result(@Valid User user, BindingResult bindingResult){
        String Email_val = user.getEmailid();

        if(bindingResult.hasErrors()){
            if(bindingResult.hasFieldErrors("emailid")){
                return "returningUser";
            }
        }
        User temp_user = dbObject.readData("weatherDB", Email_val);

        user.setName(temp_user.getName());
        user.setZip(temp_user.getZip());
        user.setWeather(temp_user.getWeather());
/*

    To get Updated results....

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
