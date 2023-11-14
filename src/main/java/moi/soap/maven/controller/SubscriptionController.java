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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            Map<String, Object> params = new HashMap<>();
            params.put("input", input);

            this.middleware.handlerMiddleware(this.ctx, "Subscription.Example", params);

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

            Map<String, Object> params = new HashMap<>();
            params.put("studioID", studioID);
            params.put("status", status);
            this.middleware.handlerMiddleware(this.ctx, "Subscription.getSubscriptionByStatusStudio", params);

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
    @WebMethod
    @WebResult(name = "result", targetNamespace = "Subscription")
    public List<Subscription> getSubscriptionByStatusSubscriber(@WebParam(name="subscriberID")int subscriberID, @WebParam(name="status") String status) throws Exception {
        try{

            Map<String, Object> params = new HashMap<>();
            params.put("subscriberID", subscriberID);
            params.put("status", status);
            this.middleware.handlerMiddleware(this.ctx, "Subscription.getSubscriptionByStatusSubscriber", params);

            SubsStatus subsStatus = SubsStatus.valueOf(status);

            List<Subscription> result = this.srv.subscription.getSubscriptionByStatusSubscriber(subscriberID, subsStatus);

            return  result;
        } catch (ResponseException exp) {
            exp.printStackTrace();
            throw new Exception(exp.toJSONString());
        } catch (Exception exp) {
            throw new Exception(new ResponseException("Internal Server Error", HttpStatus.SC_INTERNAL_SERVER_ERROR).toJSONString());
        }
    }


    @Override
    @WebMethod
    @WebResult(name = "result", targetNamespace = "Subscription")
    public Subscription subscribe(@WebParam(name="studioID") int studioID, @WebParam(name="subscriberID") int subscriberID) throws Exception {
        try{

            Map<String, Object> params = new HashMap<>();
            params.put("studioID", studioID);
            params.put("subscriberID", subscriberID);
            this.middleware.handlerMiddleware(this.ctx, "Subscription.subscribe", params);

            Subscription result = this.srv.subscription.subscribe(studioID, subscriberID);

            return result;
        } catch (ResponseException exp) {
            exp.printStackTrace();
            throw new Exception(exp.toJSONString());

        } catch (Exception exp) {
            throw new Exception(new ResponseException("Internal Server Error", HttpStatus.SC_INTERNAL_SERVER_ERROR).toJSONString());

        }
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
    @WebMethod
    @WebResult(name = "result", targetNamespace = "Subscription")
    public List<Subscription> checkStatus(@WebParam(name="subscriberIDs") List<Integer> subscriberIDs, @WebParam(name="studioIDs") List<Integer> studioIDs) throws Exception {
        try{

            Map<String, Object> params = new HashMap<>();
            params.put("subscriberIDs", subscriberIDs);
            params.put("studioIDs", studioIDs);
            this.middleware.handlerMiddleware(this.ctx, "Subscription.checkStatus", params);

            if (studioIDs.size() != subscriberIDs.size()) {
                throw new ResponseException("Param Invalid", HttpStatus.SC_BAD_GATEWAY);
            }

            List<Subscription> result = this.srv.subscription.checkStatus(subscriberIDs, studioIDs);

            return result;
        } catch (ResponseException exp) {
            exp.printStackTrace();
            throw new Exception(exp.toJSONString());

        } catch (Exception exp) {
            throw new Exception(new ResponseException("Internal Server Error", HttpStatus.SC_INTERNAL_SERVER_ERROR).toJSONString());

        }
    }
}
