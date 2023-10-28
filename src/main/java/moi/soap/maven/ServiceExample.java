package moi.soap.maven;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
@WebService(targetNamespace = "http://localhost:8080/calculator")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class ServiceExample {
    @WebMethod
    public int operation(int x, int y) {
        System.out.printf("Operation Run : %d + %d\n", x, y);
        return x + y;
    }

    @WebMethod
    public int times(int x, int y) {
        return x * y;
    }
}