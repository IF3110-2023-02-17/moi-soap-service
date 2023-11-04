package moi.soap.maven.utils;

import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.util.Map;

public class ContextHandler {
    public static Map<String, Object>  getCtxHeader(WebServiceContext wsCtx) {
        MessageContext ctx = wsCtx.getMessageContext();
        return (Map<String, Object>) ctx.get(ctx.HTTP_REQUEST_HEADERS);
    }
}
