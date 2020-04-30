package com.kpdoggie.instrumentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    RandomService randomService;

    @GetMapping("")
    public ResponseEntity helloWorld() {

        randomService.randomLogic();

        return ResponseEntity.ok("hello world");
    }


}
