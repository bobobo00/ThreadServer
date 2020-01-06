package TCP;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @ClassName Server
 * @Description TODO
 * @Auther danni
 * @Date 2019/12/20 18:51]
 * @Version 1.0
 **/

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9997);
        Scanner scanner=new Scanner(System.in);
        Socket client=server.accept();
        InetAddress clientAddress=client.getInetAddress();
        int clientPort=client.getPort();
        System.out.println("客户端"+"("+clientAddress.getHostAddress()+":"+clientPort+")"+"连接成功！");
        //DataInputStream dis=new DataInputStream(client.getInputStream());
        //DataOutputStream dos=new DataOutputStream(client.getOutputStream());
        Scanner dis=new Scanner(client.getInputStream(),"utf-8");
        Writer dos=new OutputStreamWriter(client.getOutputStream());
        boolean flag=true;
        String line="";
        while(flag){
            System.out.println("好友说："+dis.nextLine());
            System.out.print("请回复->");
            String response= scanner.nextLine();
            dos.write(response);
            dos.write("\r\n");
            dos.flush();
        }
        dos.close();
        dis.close();
    }
}
