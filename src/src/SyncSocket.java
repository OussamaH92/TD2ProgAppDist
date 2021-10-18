import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class SyncSocket extends Socket {


    private Socket s;
    private BufferedReader in;
    private PrintWriter out;

    public SyncSocket(Socket s) throws IOException {
        this.s = s;
        in = new BufferedReader(new InputStreamReader(s.getInputStream())); out = new PrintWriter(s.getOutputStream(), true);
    }
    public SyncSocket(String host, int port) throws IOException{ this(new Socket(host, port));
    }

    public synchronized void send(String msg) throws IOException{

        out.println(msg);
        while(!in.readLine().equals(msg));
    }
    public synchronized void receive(String msg) throws IOException{

        String temp = "";

        while(!(temp = in.readLine()).equals(msg));

        out.println(in.readLine());

    }
    public synchronized String receive(Collection<String> msg) throws IOException {

        String temp = in.readLine();

        while(!msg.contains(temp)){
            temp = in.readLine();
        }

        out.println(temp);
        return temp;

    }

    }


