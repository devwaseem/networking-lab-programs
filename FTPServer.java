package networking;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import networking.ListFilesUtil;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 1153
 */
public class FTPServer {
    
    static String inputDir = "Z:\\algorithm\\input\\";
    static String outputDir = "Z:\\algorithm\\output\\";
    
    public static ArrayList<String> listFiles(String directoryName){
        ArrayList<String> files = new ArrayList<String>();
        File directory = new File(directoryName);
        File[] fList = directory.listFiles();
        for (File file : fList){
            if (file.isFile()){
                files.add(file.getName());
            }
        }
        return files;
    }
    
    
    public static void main(String args[]) throws IOException{
       
        
        while(true)
        {
                //create server socket on port 5000
        ServerSocket ss=new ServerSocket(5000); 
        System.out.println ("Waiting for request");
        Socket s=ss.accept();  
        System.out.println ("Connected With "+s.getInetAddress().toString());
        ArrayList<String> files = listFiles(inputDir);
        for(int i=1;i<=files.size();i++){
            println(i+" "+files.get(i-1));
        }
        println("Choose the file: ");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        String filename = inputDir+files.get(choice-1);
        DataInputStream din=new DataInputStream(s.getInputStream());  
        DataOutputStream dout=new DataOutputStream(s.getOutputStream()); 
        System.out.println("Starting FTP");
        dout.writeUTF(outputDir+files.get(choice-1));
        try{
        String str="";  

        str=din.readUTF();
        System.out.println("SendGet....Ok");

        if(!str.equals("stop")){  

        System.out.println("Sending File: "+filename);
        dout.writeUTF(filename);  
        dout.flush();  

        File f=new File(filename);
        FileInputStream fin=new FileInputStream(f);
        long sz=(int) f.length();

        byte b[]=new byte [1024];

        int read;

        dout.writeUTF(Long.toString(sz)); 
        dout.flush(); 

        System.out.println ("Size: "+sz);
        System.out.println ("Buf size: "+ss.getReceiveBufferSize());

        while((read = fin.read(b)) != -1){
            dout.write(b, 0, read); 
            dout.flush(); 
        }
        fin.close();

        System.out.println("..ok"); 
        dout.flush(); 
        }  
        dout.writeUTF("stop");  
        System.out.println("Send Complete");
        dout.flush();  
        }
        catch(Exception e)
        {
                e.printStackTrace();
                System.out.println("An error occured");
        }
        din.close();  
        s.close();  
        ss.close();  
        }
    }
    
    public static void println(String s){
        System.out.println(s);
    }
    
}

