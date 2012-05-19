package com.example.finagle.spring;

import com.twitter.finagle.Service;
import com.twitter.util.Future;
import com.twitter.util.FutureEventListener;

public abstract class AbstractClientService<Req, Res> implements
        ClientService<Req, Res>, FutureEventListener<Res> {

    protected Service<Req, Res> client;

    public Future<Res> execute(Req request) {
        return client.apply(request).addEventListener(this);
    };

    public void setClient(Service<Req, Res> client) {
        this.client = client;
    }

    public void release() {
        System.out.println("release");
        client.release();
    }
}
