package com.example.finagle.spring;

import java.net.InetSocketAddress;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.twitter.finagle.CodecFactory;
import com.twitter.finagle.builder.ServerBuilder;
import com.twitter.finagle.builder.ServerConfig.Yes;

public class ServerBuilderFactoryBean<Req, Rep> implements InitializingBean,
        FactoryBean<ServerBuilder<Req, Rep, Yes, Yes, Yes>> {

    private String serverName = "hoge";
    private String host;
    private int port;
    private CodecFactory<Req, Rep> codecFactory;
    private ServerBuilder<Req, Rep, Yes, Yes, Yes> serverBuilder;

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setCodecFactory(CodecFactory<Req, Rep> codecFactory) {
        this.codecFactory = codecFactory;
    }

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(codecFactory);
        Assert.notNull(host);
        serverBuilder = ServerBuilder.get().codec(codecFactory)
                .name(serverName).bindTo(new InetSocketAddress(host, port));
    }

    public ServerBuilder<Req, Rep, Yes, Yes, Yes> getObject() throws Exception {
        return serverBuilder;
    }

    public Class<?> getObjectType() {
        return ServerBuilder.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
