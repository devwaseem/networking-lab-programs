
package networking.programs;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static networking.programs.SocketServer.isConnected;


public class SocketClient {
    static Boolean isConnected = false;
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
         Scanner scan = new Scanner(System.in);
        Socket socket = new Socket("172.20.104.43", 9876);
        print(socket.isConnected() + " status");
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF("CLIENT CONNECTED");
        print(dis.readUTF());
        isConnected = true;
        Thread inputThread = new Thread(new Runnable(){
            
            @Override
            public void run() {
               while(isConnected){
                  print("Enter Message: ");
                  String input = scan.nextLine();
                   try {
                       dos.writeUTF(input);
                       Thread.sleep(500);
                   } catch (IOException ex) {
                       Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                      Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
               }
            }
            
        });
        Thread outputThread = new Thread(new Runnable(){
            
            @Override
            public void run() {
              while(isConnected){
                  try {
                          String message = dis.readUTF();
                          print("Client: "+message);
                          if(message == "exit321"){
                              isConnected = false;
                              socket.close();
                          }
                           Thread.sleep(500);
                  } catch (InterruptedException ex) {
                      Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
                  } catch (IOException ex) {
                          Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
                  }
               }
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        inputThread.start();
        outputThread.start();   
    }
    
     public static void print(String s){
        System.out.println(s);
    }
    
}
