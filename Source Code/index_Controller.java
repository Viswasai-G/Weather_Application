package Weather_App;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class index_Controller {

    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
