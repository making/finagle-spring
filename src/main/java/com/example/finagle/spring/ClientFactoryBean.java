package com.example.finagle.spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.twitter.finagle.CodecFactory;
import com.twitter.finagle.Service;
import com.twitter.finagle.builder.ClientBuilder;

public class ClientFactoryBean<Req, Rep> implements InitializingBean,
        DisposableBean, FactoryBean<Service<Req, Rep>> {

    private Service<Req, Rep> service;
    private CodecFactory<Req, Rep> codecFactory;
    private String hosts;
    private int hostConnectionLimit = 1;

    public Service<Req, Rep> getObject() throws Exception {
        return service;
    }

    public Class<?> getObjectType() {
        return Service.class;
    }

    public boolean isSingleton() {
        return false;
    }

    public void setCodecFactory(CodecFactory<Req, Rep> codecFactory) {
        this.codecFactory = codecFactory;
    }

    public void setHosts(String hosts) {
        this.hosts = hosts;
    }

    public void setHostConnectionLimit(int hostConnectionLimit) {
        this.hostConnectionLimit = hostConnectionLimit;
    }

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(codecFactory);
        Assert.notNull(hosts);
        service = ClientBuilder.safeBuild(ClientBuilder.get()
                .codec(codecFactory).hosts(hosts)
                .hostConnectionLimit(hostConnectionLimit));
    }

    public void destroy() throws Exception {
        service.release();
    }

}
