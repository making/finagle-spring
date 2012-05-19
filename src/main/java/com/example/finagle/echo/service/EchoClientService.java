package com.example.finagle.echo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.example.finagle.spring.AbstractClientService;
import com.twitter.finagle.Service;

@org.springframework.stereotype.Service
public class EchoClientService extends AbstractClientService<String, String> {
    private static final Logger logger = LoggerFactory
            .getLogger(EchoClientService.class);

    @Qualifier("echoClient")
    @Autowired
    public void setClient(Service<String, String> client) {
        super.setClient(client);
    };

    public void onFailure(Throwable cause) {
        logger.warn("failed to serve!", cause);
    }

    public void onSuccess(String request) {
        logger.info("result : {}", request);
    }
}
