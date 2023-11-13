package moi.soap.maven.controller;

import lombok.NoArgsConstructor;
import moi.soap.maven.middleware.MiddlewareComp;
import moi.soap.maven.service.ServiceComp;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;

@NoArgsConstructor
public abstract class Controller {

    @Resource
    protected WebServiceContext ctx;
    protected final String HTTP_EXCHANGE_KEY = "com.sun.xml.internal.ws.http.exchange";
    protected ServiceComp srv;
    protected MiddlewareComp middleware;

    Controller(ServiceComp srv, MiddlewareComp middleware) {
        this.srv = srv;
        this.middleware = middleware;
    }
}