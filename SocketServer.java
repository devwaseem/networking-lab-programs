/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking.programs;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static networking.programs.SocketClient.isConnected;

/**
 *
 * @author 1153
 */
public class SocketServer {

    private static ServerSocket server;
    private static int port = 9876;
    static Boolean isConnected = false;
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        Scanner scan = new Scanner(System.in);
        server = new ServerSocket(port);
        System.out.println("Waiting for client request");
        Socket socket = server.accept();
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        print(dis.readUTF());
        dos.writeUTF("SERVER CONNECTED");
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
