package com.example.finagle.http.service;

import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.example.finagle.spring.AbstractClientService;
import com.twitter.finagle.Service;

@org.springframework.stereotype.Service
public class HttpClientService extends
        AbstractClientService<HttpRequest, HttpResponse> {
    private static final Logger logger = LoggerFactory
            .getLogger(HttpClientService.class);

    @Autowired
    @Qualifier("httpClient")
    public void setClient(Service<HttpRequest, HttpResponse> client) {
        super.setClient(client);
    }

    public void onSuccess(HttpResponse response) {
        logger.debug("received response: {}", response);
        logger.debug("content: {}",
                response.getContent().toString(CharsetUtil.UTF_8));
    }

    public void onFailure(Throwable cause) {
        logger.warn("failed!", cause);
    }
}
