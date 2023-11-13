package moi.soap.maven.middleware;

import moi.soap.maven.entity.Logging;
import moi.soap.maven.repository.RepositoryComp;

import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.spi.http.HttpExchange;
public class LoggingMiddleware {
    private RepositoryComp repo;
    public LoggingMiddleware (RepositoryComp repo) {
        this.repo = repo;
    }
    public void recordLogging (WebServiceContext wsCtx, String Endpoint, String exchangeKey) {
        // Build
        // Write to db
    }
    public void buildLoggingDescription(Logging log) {

    }
    public String getAddress(MessageContext ctx, String key) {
        try {
            HttpExchange httpExchange = (HttpExchange) ctx.get("com.sun.xml.internal.ws.api.message.Packet");
//            System.out.println(httpExchange);
            if (httpExchange != null) {
                String clientIpAddress = httpExchange.getRemoteAddress().getAddress().getHostAddress();
                System.out.println(clientIpAddress);
            } else {
                System.out.println("HttpExchange is null");
            }
            return httpExchange.getRemoteAddress().toString();
        }
        catch (Exception exp) {
            exp.printStackTrace();
        }
        return "";
    }
}
