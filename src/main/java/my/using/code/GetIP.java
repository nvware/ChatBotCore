package my.using.code;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetIP {
    public static void main(String[] args) {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            String hostAddress = inetAddress.getHostAddress();
            System.out.println("The IP address of the current machine is: " + hostAddress);
        } catch (UnknownHostException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

