package moi.soap.maven.middleware;

import com.sun.net.httpserver.HttpExchange;
import moi.soap.maven.entity.Logging;
import moi.soap.maven.exception.ResponseException;
import moi.soap.maven.repository.RepositoryComp;
import org.apache.hc.core5.http.HttpStatus;

import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.sql.Timestamp;
import java.util.*;

public class LoggingMiddleware {
    private RepositoryComp repo;
    private final String HTTP_EXCHANGE_KEY = "com.sun.xml.internal.ws.http.exchange";
    public LoggingMiddleware (RepositoryComp repo) {
        this.repo = repo;
    }
    public void recordLogging (WebServiceContext wsCtx, String endpoint, String clientName, Map<String, Object> params) throws ResponseException {
        MessageContext ctx = wsCtx.getMessageContext();
        Logging log = new Logging();

        log.setEndpoint(endpoint);
        log.setIp(this.getAddress(ctx));
        log.setRequestedAt(new Timestamp(new Date().getTime()));
        if (clientName == null) {
            clientName = "Unknown";
        }
        log.setDescription(this.buildLoggingDescription(log, clientName, params));
        System.out.println(log);

        this.repo.logging.insert(log);
    }
    public String buildLoggingDescription(Logging log, String client, Map<String, Object> params) throws ResponseException {
        StringBuilder desc = new StringBuilder();
        desc.append(String.format("[ Client %s (%s)] ", client, log.getIp()));
        desc.append(String.format("Invoke Method : [ %s ] ", log.getEndpoint()));
        desc.append(String.format("Send Parameters : [ %s ]", this.buildDescriptionParams(params)));
        return desc.toString();
    }
    public String getAddress(MessageContext ctx) throws ResponseException {
        try {
            HttpExchange httpExchange = (HttpExchange) ctx.get(this.HTTP_EXCHANGE_KEY);
            return httpExchange.getRemoteAddress().toString().replace("/", "");
        } catch (Exception exp) {
            throw new ResponseException("Internal Server Error", HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
    }
    public String buildDescriptionParams(Map<String, Object> params) throws ResponseException {
        StringBuilder str = new StringBuilder();

        for (Map.Entry<String, Object> param : params.entrySet()) {
            str.append(String.format("[ %s : %s ]", param.getKey(), param.getValue()));
        }

        return str.toString();
    }
}
