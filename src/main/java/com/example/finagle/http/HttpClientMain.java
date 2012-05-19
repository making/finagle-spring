package com.example.finagle.http;

import org.jboss.netty.handler.codec.http.DefaultHttpRequest;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpVersion;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.finagle.http.service.HttpClientService;
import com.example.finagle.spring.Context;

public class HttpClientMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = Context.getApplicationContext();
        final HttpClientService client = ctx.getBean(HttpClientService.class);

        HttpRequest request = new DefaultHttpRequest(HttpVersion.HTTP_1_1,
                HttpMethod.GET, "/");
        client.execute(request);
        ctx.close();
    }

}
