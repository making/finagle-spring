package com.example.finagle.echo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.twitter.finagle.Service;
import com.twitter.util.Future;

@org.springframework.stereotype.Service
public class EchoServerService extends Service<String, String> {
    private static final Logger logger = LoggerFactory
            .getLogger(EchoServerService.class);

    @Override
    public Future<String> apply(String req) {
        logger.debug("received : {}", req);
        return Future.value(req);
    }

}
