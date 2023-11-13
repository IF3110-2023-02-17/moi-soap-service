package moi.soap.maven.controller;


import lombok.NoArgsConstructor;
import moi.soap.maven.entity.Subscription;
import moi.soap.maven.enums.SubsStatus;
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
public class SubscriptionController extends Controller implements ISubscriptionController{
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

    @Override
    public List<Subscription> getSubscriptionStudio(int studioID) throws Exception {
        return null;
    }

    @Override
    public List<Subscription> getSubscriptionSubscriber(int subscriberID) throws Exception {
        return null;
    }

    @Override
    @WebMethod
    @WebResult(name = "result", targetNamespace = "Subscription")
    public List<Subscription> getSubscriptionByStatusStudio(@WebParam(name="studioID")int studioID, @WebParam(name="status") String status) throws Exception {
        try{
            this.middleware.handlerMiddleware(this.ctx);

            SubsStatus subsStatus = SubsStatus.valueOf(status);

            List<Subscription> result = this.srv.subscription.getSubscriptionByStatusStudio(studioID, subsStatus);

            return  result;
        } catch (ResponseException exp) {
            exp.printStackTrace();
            throw new Exception(exp.toJSONString());
        } catch (Exception exp) {
            throw new Exception(new ResponseException("Internal Server Error", HttpStatus.SC_INTERNAL_SERVER_ERROR).toJSONString());
        }
    }

    @Override
    public List<Subscription> getSubscriptionByStatusSubscriber(int subscriberID, String status) throws Exception {
        return null;
    }

    @Override
    public Subscription subscribe(int studioID, int subscriberID) throws Exception {
        return null;
    }

    @Override
    public Subscription acceptSubscription(int studioID, int subscriberID) throws Exception {
        return null;
    }

    @Override
    public Subscription rejectSubscription(int studioID, int subscriberID) throws Exception {
        return null;
    }

    @Override
    public List<Subscription> checkStatus(int subscriberID) throws Exception {
        return null;
    }
}
