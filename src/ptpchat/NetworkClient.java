package ptpchat;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class NetworkClient {
    private String reciever;
    private int port;
    private MessagesArray messages;
    private Socket sock;
    private ObjectInputStream networkIn;
    private ObjectOutputStream networkOut;
    private String username;
    private Boolean running;

    public NetworkClient(String reciever, int port, String username, MessagesArray messages) throws IOException {
        running = true;
        this.reciever = reciever;
        this.port = port;
        this.username = username;
        this.messages = messages;

        this.sock = new Socket(reciever, port);
        this.networkIn = new ObjectInputStream(this.sock.getInputStream());
        this.networkOut = new ObjectOutputStream(this.sock.getOutputStream());
        startListener();
    }

    private void startListener() {
        Thread netThread = new Thread(() -> this.run() );
        netThread.start();
    }

    public String getUsername() {
        return username;
    }

    public void sendMessage(Message message) throws IOException {
        messages.addMessage(message);
        networkOut.writeUnshared(message);
        networkOut.flush();
    }

    public void sendMessage(String text) throws IOException{
        System.out.println("Sending message: " + text + " out local address is: " + sock.getLocalAddress().toString());
        Message toSend = new Message(text, System.currentTimeMillis(), sock.getLocalAddress().toString(), username);
        messages.addMessage(toSend);
        networkOut.writeUnshared(toSend);
        networkOut.flush();
    }

    public void closeSocket() throws IOException {
        networkOut = null;
        networkOut = null;
        sock.close();
    }

    private void run() {
        while (running) {
            try {
                Message message = (Message) networkIn.readUnshared();
                System.out.println("Recieved message from: " + message.getUsername() + " message is: " + message.getText());
                messages.addMessage(message);
            } catch (IOException ioe) {

            } catch (ClassNotFoundException cnfe) {

            }
        }
        try {
            closeSocket();
        } catch (IOException ioe) {

        }
    }
}
