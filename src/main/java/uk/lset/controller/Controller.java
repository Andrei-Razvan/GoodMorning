package uk.lset.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @PostMapping(path = "/post")
    public String post() {
        return "Good Morning";
    }

    @GetMapping(path = "/get")
    public Boolean get() {
        return true;
    }

}
