import java.io.*;
import java.net.*;
public class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket(5000);
            byte[] recieve = new byte[65535];
            DatagramPacket dpreceive = null;
            while(true)
            {
                dpreceive = new DatagramPacket(recieve, recieve.length);
                ds.receive(dpreceive);
                System.out.println("msg:"+data(recieve));
                if(data(recieve).equals("bye"))
                {
                    System.out.println("Exitting....");
                    break;
                }
                ds.send(dpreceive);
                recieve=new byte[65535];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    static String data(byte[] arr)
    {
        StringBuilder sb = new StringBuilder();
        int i=0;
        while(arr[i]!=0)
        {
            sb.append((char)arr[i]);
            i++;
        }
        return sb.toString();
    }
}
