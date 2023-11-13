package moi.soap.maven.middleware;

import moi.soap.maven.exception.ResponseException;
import moi.soap.maven.repository.RepositoryComp;
import org.apache.hc.core5.http.HttpStatus;

import javax.xml.ws.WebServiceContext;

public class MiddlewareComp {
    private ApiKeyMiddleware apiKey;
    private LoggingMiddleware log;
    public MiddlewareComp(RepositoryComp repo) {
        this.apiKey = new ApiKeyMiddleware();
        this.log = new LoggingMiddleware(repo);
    }
    public void handlerMiddleware (WebServiceContext ctx) throws ResponseException {
        try {
            String client = this.apiKey.verifyAPIKey(ctx);
            System.out.println(client);
        } catch (ResponseException exp) {
            throw exp;
        }
    }
//    public void loggingRemoteAddr(WebServiceContext ctx, String key) {
//        System.out.println(log.getAddress(ctx.getMessageContext(), key));
//        System.out.println("PASS 2.1");
//    }

}
