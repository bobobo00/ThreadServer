package TCP;

import com.sun.xml.internal.txw2.output.DataWriter;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * @ClassName Client
 * @Description TODO
 * @Auther danni
 * @Date 2019/12/20 18:51]
 * @Version 1.0
 **/

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner scanner=new Scanner(System.in);
        Socket client=new Socket();
        byte[] ipv4={127,0,0,1};
        InetAddress serverAddress=InetAddress.getByAddress(ipv4);
        SocketAddress serverSocketAddress=new InetSocketAddress(serverAddress,9997);
        client.connect(serverSocketAddress);
        //DataOutputStream dos=null;
        Writer dos=null;
        //DataInputStream dis=null;
        Reader dis=null;
        boolean flag=true;
        while(flag){
            System.out.print("请输入->");
            String request=scanner.nextLine();
            dos=new OutputStreamWriter(client.getOutputStream());
            dos.write(request);
            dos.write("\r\n");
            dos.flush();

            dis=new InputStreamReader(client.getInputStream());
            BufferedReader br=new BufferedReader(dis);
            String message=br.readLine();
            System.out.println(message);
        }
        dos.close();
        dis.close();
    }
}
