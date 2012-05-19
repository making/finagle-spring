package com.example.finagle.http;

import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.finagle.http.service.HttpServerService;
import com.example.finagle.spring.Context;
import com.twitter.finagle.Service;
import com.twitter.finagle.builder.ServerBuilder;
import com.twitter.finagle.builder.ServerConfig.Yes;

public class HttpServerMain {
    private static final Logger logger = LoggerFactory
            .getLogger(HttpServerMain.class);

    /**
     * @param args
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = Context.getApplicationContext();

        Service<HttpRequest, HttpResponse> service = ctx
                .getBean(HttpServerService.class);
        ServerBuilder<HttpRequest, HttpResponse, Yes, Yes, Yes> serverBuilder = ctx
                .getBean("httpServerBuilder", ServerBuilder.class);

        ServerBuilder.safeBuild(service, serverBuilder);
        logger.info("start server {}", service);
        ctx.close();
    }

}
