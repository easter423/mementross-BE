package hello.hellospring2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PolicyController {

    @GetMapping("/privacy")
    public String Privacy(){
        return "policies/privacy";
    }

    @GetMapping("/terms")
    public String Terms(){
        return "policies/terms";
    }

    @GetMapping("/deletion")
    public String Deletion(){
        return "policies/deletion";
    }
}
