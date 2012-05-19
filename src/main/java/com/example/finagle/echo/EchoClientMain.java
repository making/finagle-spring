package com.example.finagle.echo;

import org.springframework.context.ConfigurableApplicationContext;

import com.example.finagle.echo.service.EchoClientService;
import com.example.finagle.spring.Context;

public class EchoClientMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = Context.getApplicationContext();
        EchoClientService client = ctx.getBean(EchoClientService.class);

        for (int i = 0; i < 100; i++) {
            client.execute(String.format("[%03d] hello world!%n", i));
        }
        client.release();
    }
}
