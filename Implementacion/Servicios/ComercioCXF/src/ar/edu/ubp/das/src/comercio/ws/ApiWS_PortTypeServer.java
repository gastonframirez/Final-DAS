
package ar.edu.ubp.das.src.comercio.ws;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 3.2.4
 * 2019-02-04T23:28:01.060-03:00
 * Generated source version: 3.2.4
 *
 */

public class ApiWS_PortTypeServer{

    protected ApiWS_PortTypeServer() throws Exception {
        System.out.println("Starting Server");
        Object implementor = new ar.edu.ubp.das.src.comercio.ws.ApiWS();
        String address = "http://localhost:9090/ApiWSPort";
        Endpoint.publish(address, implementor);
    }

    public static void main(String args[]) throws Exception {
        new ApiWS_PortTypeServer();
        System.out.println("Server ready...");
//
//        Thread.sleep(5 * 60 * 1000);
//        System.out.println("Server exiting");
//        System.exit(0);
    }
}
