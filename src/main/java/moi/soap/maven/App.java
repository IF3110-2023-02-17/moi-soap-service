package moi.soap.maven;

import moi.soap.maven.utils.ConfigApp;
import javax.xml.ws.Endpoint;

public class App
{
    public static void main( String[] args )
    {
        try {

            ConfigApp conf = ConfigApp.getInstance();

            String url = "http://" + conf.get("server.host") + ":" + conf.get("server.port") + "/calculator";

            Endpoint.publish(url, new ServiceExample());

            System.out.println("Layanan web telah diterbitkan di " + url);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
