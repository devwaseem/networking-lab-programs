/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking.programs;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author 1153
 */
public class EchoServer {
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
        
    }
    
    public static void print(String s){
        System.out.println(s);
    }
}
