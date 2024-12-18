package com.example.order.order.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HomeController {

    @GetMapping("/")
    public String Home() {
        String home = "Welcome Order Service Make by M. Khairul Amri";
        return home;
    }

}
