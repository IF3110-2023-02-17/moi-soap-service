package moi.soap.maven;

import moi.soap.maven.client.HttpClientComp;
import moi.soap.maven.client.HttpRestClient;
import moi.soap.maven.database.Database;
import moi.soap.maven.controller.SubscriptionController;
import moi.soap.maven.middleware.MiddlewareComp;
import moi.soap.maven.repository.RepositoryComp;
import moi.soap.maven.service.ServiceComp;
import moi.soap.maven.utils.Config;

import javax.xml.ws.Endpoint;

public class App
{
    public static void main( String[] args )
    {
        try {

            Config conf = Config.getInstance();
            Database db = Database.getInstance();
            HttpClientComp http = HttpClientComp.getInstance();
            RepositoryComp repo = new RepositoryComp(db);
            ServiceComp srv = new ServiceComp(repo, http);
            MiddlewareComp middleware = new MiddlewareComp(repo);

            String url = "http://" + conf.getProp("server.host") + ":" + conf.getProp("server.port") + "/ws/api";
            Endpoint.publish(url, new SubscriptionController(srv, middleware));
            System.out.println("Layanan web telah diterbitkan di " + url);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
