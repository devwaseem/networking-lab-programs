/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking.programs;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1156
 */
public class echoClient {
    static Boolean isConnected = false;
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
         Scanner scan = new Scanner(System.in);
        Socket socket = new Socket("172.20.104.43", 9876);
        print(socket.isConnected() + " status");
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        print("Pass message to server = ");
        dos.writeUTF("Echo = "+scan.nextLine());
        print(dis.readUTF());
    }
    
     public static void print(String s){
        System.out.println(s);
    }
}
