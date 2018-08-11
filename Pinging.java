/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking.programs;

/**
 *
 * @author 1153
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;
 
class Pinging
{
  public static void sendPingRequest(String ipAddress)
              throws UnknownHostException, IOException
  {
    InetAddress add = InetAddress.getByName(ipAddress);
    System.out.println("Sending Ping Request to " + ipAddress);
    if (add.isReachable(2000))
      System.out.println("Host is reachable");
    else
      System.out.println("Sorry ! We can't reach to this host");
  }
 
  // Driver code
  public static void main(String[] args)
          throws UnknownHostException, IOException
  {
    Scanner scan = new Scanner(System.in);
    while(true){
        print("1.Ping\n2.Exit\n");
        if(scan.nextInt() != 1){
            print("bye!");
            return;
        }
        print("Enter hostname: ");
        scan.nextLine();
        String host = scan.nextLine();      
        sendPingRequest(host);
        print("\n");
    }
  }
  
  public static void print(String s){
      System.out.println(s);
  }
}
