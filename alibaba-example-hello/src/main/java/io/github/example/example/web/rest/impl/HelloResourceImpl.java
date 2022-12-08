package io.github.example.example.web.rest.impl;

import io.github.example.example.feign.WorldClient;
import io.github.example.example.web.rest.HelloResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class HelloResourceImpl implements HelloResource {

    public static final Logger logger = LoggerFactory.getLogger(HelloResourceImpl.class);
    private static final String WORD_API = "http://world/api/world";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WorldClient worldClient;

//    @Value("${foo}")
    private String foo;

    @GetMapping("/rest")
    public ResponseEntity<String> rest(@RequestParam(value = "name",defaultValue = "world",required = false) String name, @RequestParam(value = "millis",defaultValue = "0",required = false) Long millis){
        return restTemplate.getForEntity(URI.create(String.format(WORD_API +"?name=%s&millis=%d",name,millis)),String.class);
    }


    @GetMapping("/feign")
    public ResponseEntity<String> client(@RequestParam(value = "name",defaultValue = "world",required = false) String name,@RequestParam(value = "millis",defaultValue = "0",required = false) Long millis){
        return worldClient.world(name,millis);
    }

    @Override
    public ResponseEntity<String> world(@RequestParam(value = "name",defaultValue = "world",required = false) String name,@RequestParam(value = "millis",defaultValue = "0",required = false) Long millis){
        logger.info("测试");
        return worldClient.world(name,millis);
    }

    @GetMapping("/foo")
    public ResponseEntity<String> foo(){
        return ResponseEntity.ok(foo);
    }


    private AtomicInteger integer = new AtomicInteger();

    @GetMapping("/integer")
    public ResponseEntity<Integer> integer(){
        return ResponseEntity.ok(integer.incrementAndGet());
    }

    @GetMapping("/p")
    public R p(){
        return new R(1,null);
    }
}
