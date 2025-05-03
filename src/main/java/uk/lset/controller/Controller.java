package uk.lset.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @GetMapping(path = "/get")
    public Boolean get() {
        logger.info("Get Request");
        return true;
    }

    @PostMapping(path = "/post")
    public String post() {
        return "Good Morning";
    }


}
