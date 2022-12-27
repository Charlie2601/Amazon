import java.io.*;
import java.net.*;
import java.util.Scanner;
public class MultiChatClient {
    static int port =1234;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        InetAddress ip = InetAddress.getByName("localhost");
        Socket s = new Socket(ip,port);
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        Thread sendmessage = new Thread(new Runnable() {
           @Override
           public void run()
           {
            while(true)
            {
                String msg = sc.nextLine();
                try {
                    dos.writeUTF(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
           } 
        });
        Thread readmessage = new Thread(new Runnable() {
            @Override
            public void run()
            {
                while(true)
                {
                    String msg;
                    try {
                        msg = dis.readUTF();
                        
                    System.out.println(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        sendmessage.start();
        readmessage.start();
    }
}
