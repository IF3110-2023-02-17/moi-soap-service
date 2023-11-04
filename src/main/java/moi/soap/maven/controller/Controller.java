package moi.soap.maven.controller;

import lombok.NoArgsConstructor;
import moi.soap.maven.service.Service;
import moi.soap.maven.service.ServiceComp;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

@NoArgsConstructor
public abstract class Controller {

    @Resource
    protected WebServiceContext ctx;
    private final String HTTP_EXCHANGE_KEY = "com.sun.xml.internal.ws.http.exchange";
    protected ServiceComp srv;

    Controller(ServiceComp srv) {
        this.srv = srv;
    }
}