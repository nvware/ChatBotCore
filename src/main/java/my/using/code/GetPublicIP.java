package my.using.code;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class GetPublicIP {
    public static void main(String[] args) {
        try {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));

            String publicIP = in.readLine();
            System.out.println("The public IP address is: " + publicIP);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
