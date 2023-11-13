package moi.soap.maven.controller;


import lombok.NoArgsConstructor;
import moi.soap.maven.exception.ResponseException;
import moi.soap.maven.middleware.MiddlewareComp;
import moi.soap.maven.service.ServiceComp;
import org.apache.hc.core5.http.HttpStatus;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@NoArgsConstructor
@WebService(targetNamespace = "Subscription")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class SubscriptionController extends Controller {
    public SubscriptionController (ServiceComp srv, MiddlewareComp middleware) {
        super(srv, middleware);
    }
    @WebMethod
    @WebResult(name = "result", targetNamespace = "Subscription")
    public List<Integer> Example(@WebParam(name="input") List<Integer> input) throws Exception {
        try {
            this.middleware.handlerMiddleware(this.ctx);

            System.out.println(input.size());
            for (int i = 0; i < input.size(); i++){
                System.out.println(input.get(i));
            }
            return input;
        } catch (ResponseException exp) {
            exp.printStackTrace();
            throw new Exception(exp.toJSONString());
        } catch (Exception exp) {
            throw new Exception(new ResponseException("Internal Server Error", HttpStatus.SC_INTERNAL_SERVER_ERROR).toJSONString());
        }
    }
}
