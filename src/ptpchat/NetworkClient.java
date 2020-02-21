package ptpchat;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class NetworkClient {
    private String reciever;
    private int port;
    private MessagesArray messages;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectInputStream networkIn;
    private ObjectOutputStream networkOut;
    private String username;
    private Boolean running = false;

    public NetworkClient(String reciever, int port, String username, MessagesArray messages, boolean server) {
        System.out.println("Trying to start the network client");
        this.reciever = reciever;
        this.port = port;
        this.username = username;
        this.messages = messages;

        try {
            if (server) {
                this.serverSocket = new ServerSocket(port);
                this.clientSocket = this.serverSocket.accept();
            } else {
                this.clientSocket = new Socket(reciever, port);
            }
            this.networkOut = new ObjectOutputStream(this.clientSocket.getOutputStream());
            this.networkIn = new ObjectInputStream(this.clientSocket.getInputStream());

            System.out.println("Or maybe its here");
            startListener();
        } catch (IOException ioe) {

        }


    }

    private void startListener() {
        System.out.println("Listening");
        Thread netThread = new Thread(() -> this.run() );
        netThread.start();
        running = true;
    }

    public String getUsername() {
        return username;
    }

    public void sendMessage(Message message) throws IOException {
        if (running) {
            messages.addMessage(message);
            networkOut.writeUnshared(message);
            networkOut.flush();
        }
    }

    public void sendMessage(String text) throws IOException {
        if (running) {
            System.out.println("Sending message: " + text + " out local address is: " + clientSocket.getLocalAddress().toString());
            Message toSend = new Message(text, System.currentTimeMillis(), clientSocket.getLocalAddress().toString(), username);
            messages.addMessage(toSend);
            networkOut.writeUnshared(toSend);
            networkOut.flush();
        }
    }

    public void closeSocket() throws IOException {
        networkOut = null;
        networkOut = null;
        clientSocket.close();
        serverSocket.close();
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
