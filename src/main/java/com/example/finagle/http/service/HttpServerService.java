package com.example.finagle.http.service;

import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpHeaders;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;
import org.jboss.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.twitter.finagle.Service;
import com.twitter.util.Future;

@org.springframework.stereotype.Service
public class HttpServerService extends Service<HttpRequest, HttpResponse> {
    private static final Logger logger = LoggerFactory
            .getLogger(HttpServerService.class);

    @Override
    public Future<HttpResponse> apply(HttpRequest request) {
        logger.debug("received: {}", request);
        HttpResponse res = new DefaultHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK);
        StringBuilder sb = new StringBuilder();
        sb.append("foo");
        res.setContent(ChannelBuffers.copiedBuffer(sb, CharsetUtil.UTF_8));
        res.setHeader(HttpHeaders.Names.CONTENT_TYPE,
                "text/plain; charset=UTF-8");
        return Future.value(res);
    }
}
