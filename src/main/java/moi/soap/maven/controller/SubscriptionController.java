package moi.soap.maven.controller;


import lombok.NoArgsConstructor;
import moi.soap.maven.entity.Subscription;
import moi.soap.maven.service.ServiceComp;

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
    public SubscriptionController (ServiceComp srv) {
        super(srv);
    }
    @WebMethod
    @WebResult(name = "result", targetNamespace = "Subscription")
    public List<Subscription> getSubscriptions(@WebParam(name="sortBy") int sortBy) {
        try {

            List<Subscription> result = this.srv.subscription.getAllSubscribers(sortBy);

            return result;

        } catch (Exception err) {
            return null;
        }
    }

    @WebMethod
    @WebResult(name = "result", targetNamespace = "Subscription")
    public void test() throws Exception {
        throw new Exception("Ini Exception");
    }
    @WebMethod
    @WebResult(name = "result", targetNamespace = "Subscription")
    public List<Integer> httpClientTest(@WebParam(name="input") List<Integer>input) throws Exception {
        try {
            System.out.println(input.size());
            for (int i = 0; i < input.size(); i++){
                System.out.println(input.get(i));
            }

            return input;
        } catch (Exception exp) {
            exp.printStackTrace();
            throw new Exception("200");
        }
    }
}
