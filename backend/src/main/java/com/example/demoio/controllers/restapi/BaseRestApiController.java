package com.example.demoio.controllers.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class BaseRestApiController {

    @Autowired
    private ApplicationContext ctx;

    public ApplicationContext getCtx() {
        return ctx;
    }
}
