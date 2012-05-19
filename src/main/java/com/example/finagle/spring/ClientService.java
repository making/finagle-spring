package com.example.finagle.spring;

import com.twitter.util.Future;

public interface ClientService<Req, Res> {

    Future<Res> execute(Req request);
}
