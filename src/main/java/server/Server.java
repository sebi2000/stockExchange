package server;

import java.net.*;
import java.io.*;
class Server{
    public static void main(String args[])throws Exception{
        ServerSocket ss=new ServerSocket(3333);
        Socket s=ss.accept();
        DataInputStream din=new DataInputStream(s.getInputStream());
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));


        String str="",str2="";
        while(!str.equals("stop")){

            str=din.readUTF();
            if(str.equals( "Buy")){
                System.out.println("Am intrat in if");
                Thread thread = new Thread(Server.printWTF());
                dout.writeUTF("Buy in if");
            }
            if(str.equals("Sell")){
                Thread thread = new Thread(Server.printWTF2());
                dout.writeUTF("Sold");
            }
            System.out.println("client says: "+str);
            str2=br.readLine();
            dout.writeUTF(str2);
            dout.flush();
        }
        din.close();
        s.close();
        ss.close();
    }

    static String printWTF(){
        System.out.println("Buying");

        return "Buying";
    }

    static String printWTF2(){
        return "Selling";
    }
}