package com.example.finagle.spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Context {
    public static ConfigurableApplicationContext getApplicationContext() {
        return new GenericXmlApplicationContext(
                "classpath:/META-INF/spring/app-context.xml");
    }
}
