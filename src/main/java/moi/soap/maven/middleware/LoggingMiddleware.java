package moi.soap.maven.middleware;

import com.sun.net.httpserver.HttpExchange;
import moi.soap.maven.entity.Logging;
import moi.soap.maven.exception.ResponseException;
import moi.soap.maven.repository.RepositoryComp;
import org.apache.hc.core5.http.HttpStatus;

import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

public class LoggingMiddleware {
    private RepositoryComp repo;
    private final String HTTP_EXCHANGE_KEY = "com.sun.xml.internal.ws.http.exchange";
    public LoggingMiddleware (RepositoryComp repo) {
        this.repo = repo;
    }
    public void recordLogging (WebServiceContext wsCtx, String Endpoint, String exchangeKey) {
        // Build
        // Write to db
    }
    public void buildLoggingDescription(Logging log) {

    }
    public String getAddress(MessageContext ctx) throws ResponseException {
        try {
            HttpExchange httpExchange = (HttpExchange) ctx.get(this.HTTP_EXCHANGE_KEY);
            return httpExchange.getRemoteAddress().toString().replace("/", "");
        } catch (Exception exp) {
            throw new ResponseException("Internal Server Error", HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
