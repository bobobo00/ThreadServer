package TCP;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * @ClassName Threads_Servr
 * @Description TODO
 * @Auther danni
 * @Date 2019/12/31 17:14]
 * @Version 1.0
 **/

public class Threads_Server {
    private static  class TalkRunnable implements Runnable{
        private Socket client;

        public TalkRunnable(Socket client){
            this.client=client;
        }

        public void run(){
            Scanner scanner=new Scanner(System.in);
            DataInputStream dis=null;
            DataOutputStream dos=null;
            try {
                InetAddress clientAddress = client.getInetAddress();
                int port = client.getPort();
                System.out.printf("有客户端连接上来%s:%d%n", clientAddress.getHostAddress(), port);
                dis=new DataInputStream(client.getInputStream());
                dos=new DataOutputStream(client.getOutputStream());
                boolean flag=true;
                while(flag){
                    String line=dis.readUTF();
                    System.out.println("好友说："+line);
                    System.out.print("请回复->");
                    String response= scanner.nextLine();
                    dos.writeUTF(response);
                    dos.flush();
                }
                dos.close();

            }catch (IOException e){
                System.err.println("异常！");;
            }finally {
                try {
                    if(null!=dis){ dis.close();
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if(null!=dos){ dos.close();
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    public static void main(String[] args) throws IOException {
        ServerSocket server=new ServerSocket(8088);

        BlockingQueue<Runnable> queue=new LinkedBlockingDeque<>();
        ExecutorService pool=new ThreadPoolExecutor(100,100,0,TimeUnit.MILLISECONDS,queue);
        while(true){
            Socket client=server.accept();
            pool.execute(new TalkRunnable(client));
        }
    }
}
