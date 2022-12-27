import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import java.util.Vector;

public class MultiChatServer {
    static Vector<ClientHandler> arr = new Vector<>();
    static int i = 0;

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(1234);
            Socket s;
            while (true) {
                s = ss.accept();
                DataInputStream dis= new DataInputStream(s.getInputStream());
                DataOutputStream dos= new DataOutputStream(s.getOutputStream());
                ClientHandler ch = new ClientHandler(s,"client"+i, dis, dos);
                Thread t = new Thread(ch);
                arr.add(ch);
                t.start();
                i++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class ClientHandler implements Runnable {
    Socket s;
    DataInputStream dis;
    DataOutputStream dos;
    String name;
    boolean isLoggedin;

    ClientHandler(Socket s, String name, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.name = name;
        this.dis = dis;
        this.dos = dos;
        isLoggedin = true;
    }

    @Override
    public void run() {
        String recieved;
        while (true) {
            try {
                recieved = dis.readUTF();
                if (recieved.equals("logout")) {
                    System.out.println("Logging out......");
                    this.s.close();
                    this.isLoggedin = false;
                    break;
                }
                StringTokenizer st = new StringTokenizer(recieved, "#");
                String msg = st.nextToken();
                String to = st.nextToken();
                for (ClientHandler mc : MultiChatServer.arr) {
                    if (mc.name.equals(to) && mc.isLoggedin) {
                        mc.dos.writeUTF(this.name + ":" + msg);
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            this.dis.close();
            this.dos.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}