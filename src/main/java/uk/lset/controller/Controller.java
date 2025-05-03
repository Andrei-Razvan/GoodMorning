package uk.lset.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;


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
        logger.info("Post Request");
        return "Good Morning";
    }
    @PostMapping(path = "/webhook")  //tells Spring Boot to map Http Post requests sent to "/webhook" to this method.GitHub will data to "/webhook"
    public ResponseEntity<?>webhook(@RequestBody Map<String, String> body) throws JsonProcessingException {   //Spring automatically take the incoming Json body and convert it into a Java Map with String keys and values
        //logger.info("Webhook Request:{}", new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(body)); //creates new instance of ObjectMapper(is used to convert between Java objects and JSON
                                                                                                                         //configures the ObjectMapper to pretty-print the JSON output(nice formatting)
                                                                                                                         //converts Java object to a JSON string
                                                                                                                         //logger.info logs formatted JSON to the console
        String webhook = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(body);
        logger.info("Webhook request: {}", webhook);

        String home = System.getProperty("user.home");
        String path = home + File.separator + "Desktop" + File.separator + "logs.txt";
        try{
            File file = new File(path);
            File parent = file.getParentFile();
            if(parent != null && !parent.exists() ){
                parent.mkdirs();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
            bw.write(webhook);
            bw.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        return ResponseEntity.ok().build();                                                                              //returns an HTTP 200 Ok response to the sender
    }
}
