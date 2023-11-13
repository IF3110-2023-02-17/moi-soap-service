package moi.soap.maven.middleware;

import moi.soap.maven.exception.ResponseException;
import moi.soap.maven.repository.RepositoryComp;
import org.apache.hc.core5.http.HttpStatus;

import javax.xml.ws.WebServiceContext;
import java.util.Map;

public class MiddlewareComp {
    private ApiKeyMiddleware apiKey;
    private LoggingMiddleware log;
    public MiddlewareComp(RepositoryComp repo) {
        this.apiKey = new ApiKeyMiddleware();
        this.log = new LoggingMiddleware(repo);
    }
    public void handlerMiddleware (WebServiceContext ctx, String endpoint, Map<String, Object> paramMap) throws ResponseException {
        try {
            String client = this.apiKey.verifyAPIKey(ctx);
            this.log.recordLogging(ctx, endpoint, client, paramMap);
        } catch (ResponseException exp) {
            this.log.recordLogging(ctx, endpoint, null, paramMap);
            throw exp;
        }
    }
}
