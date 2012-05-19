package com.example.finagle.echo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.finagle.echo.service.EchoServerService;
import com.example.finagle.spring.Context;
import com.twitter.finagle.Service;
import com.twitter.finagle.builder.ServerBuilder;
import com.twitter.finagle.builder.ServerConfig.Yes;

public class EchoServerMain {
    private static final Logger logger = LoggerFactory
            .getLogger(EchoServerMain.class);

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = Context.getApplicationContext();

        Service<String, String> service = ctx.getBean(EchoServerService.class);
        ServerBuilder<String, String, Yes, Yes, Yes> serverBuilder = ctx
                .getBean("echoServerBuilder", ServerBuilder.class);

        ServerBuilder.safeBuild(service, serverBuilder);
        logger.info("start server {}", service);
    }
}
