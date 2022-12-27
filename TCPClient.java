import java.net.*;
import java.io.*;

public class TCPClient {
    private Socket socket = null;
    private DataInputStream inp = null;
    private DataOutputStream out = null;
    private DataInputStream from = null;

    TCPClient(String addr, int port) {
        try {
            socket = new Socket(addr, port);
            System.out.println("Connected");
            inp = new DataInputStream(System.in);
            out = new DataOutputStream(socket.getOutputStream());
            from = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String line="";
        while(!line.equals("end"))
        {
            line =inp.readLine();
            out.writeUTF(line);
            System.out.println("From Server:"+from.readUTF());
        }
        inp.close();
        socket.close();
        from.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
        
    }
    public static void main(String[] args) {
        TCPClient cli = new TCPClient(" 1", 7879);
    }
}
