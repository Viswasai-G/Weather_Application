package Weather_App;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


//Springboot controller for index.html page. Returns index webpage when '/index' is accessed by user.
@Controller
public class index_Controller {

    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
