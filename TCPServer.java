import java.net.*;
import java.io.*;
public class TCPServer {
    private Socket  socket= null;
    private ServerSocket server =null;
    private DataInputStream inp =null; 
    private DataOutputStream out = null;
    TCPServer(int port)
    {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for the client");
            socket =server.accept();
            inp = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());
            String line="";
            while(!line.equals("end"))
            {
                line=inp.readUTF();
                System.out.println(line);
                out.writeUTF(line);
            }
            System.out.println("Closing Connection");
            socket.close();
            inp.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        
    }
    public static void main(String[] args) {
        TCPServer ser = new TCPServer(7879);
    }
}
