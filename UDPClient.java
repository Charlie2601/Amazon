import java.io.*;
import java.net.*;
import java.util.Scanner;
public class UDPClient {
    public static void main(String[] args) {
        try {
            InetAddress ip =InetAddress.getLocalHost();
            Scanner sc = new Scanner(System.in);
            DatagramSocket ds = new DatagramSocket();
            byte[] buff = null;
            while(true)
            {
                String str = sc.next();
                buff=str.getBytes();
                DatagramPacket Dpsend = new DatagramPacket(buff, buff.length,ip,5000);
                ds.send(Dpsend);
                if(str.equals("bye"))
                {
                    System.out.println("bye.......");
                    break;
                }
                ds.receive(Dpsend);
                System.out.println("from Server:"+data(buff));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    static String data(byte[] arr)
    {
        StringBuffer sb = new StringBuffer();
        int i=0;
        while(i<arr.length)
        {
            sb.append((char)arr[i++]);
        }
        return sb.toString();
    }
   
}
