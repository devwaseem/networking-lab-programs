/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import static networking.FTPServer.inputDir;
import static networking.FTPServer.listFiles;

/**
 *
 * @author 1153
 */
public class FTPClient {
    
    
    
    public static void main(String args[]) throws IOException{
      
       
        //create the socket on port 5000
        Socket s=new Socket("localhost",5000);  
        DataInputStream din=new DataInputStream(s.getInputStream());  
        DataOutputStream dout=new DataOutputStream(s.getOutputStream()); 
        String outputDir = din.readUTF();
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  

        System.out.println("Send Get to start...");
        String str="",filename="";  

        while(!str.equals("start"))
                str=br.readLine(); 

                dout.writeUTF(str); 
                dout.flush();  

                filename=din.readUTF(); 
                System.out.println("Receving file: "+filename);
                filename=outputDir;
                System.out.println("Saving as file: "+filename);
        //
        long sz=Long.parseLong(din.readUTF());
        System.out.println ("File Size: "+(sz/(1024*1024))+" MB");

        byte b[]=new byte [1024];
        System.out.println("Receving file..");
        FileOutputStream fos=new FileOutputStream(new File(filename),true);
        long bytesRead;
        do
        {
        bytesRead = din.read(b, 0, b.length);
        fos.write(b,0,b.length);
        }while(!(bytesRead<1024));
        System.out.println("Comleted");
        fos.close(); 
        dout.close();  	
        s.close();  
     }
    
    public static void println(String s){
        System.out.println(s);
    }
    
    
}
