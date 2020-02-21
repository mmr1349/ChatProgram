package ptpchat;

import java.io.IOException;
import java.util.Scanner;

public class ChatPTUI implements Observer<MessagesArray, Message> {
    private MessagesArray model;
    private NetworkClient netClient;
    private Thread inputThread;


    @Override
    public void update(MessagesArray messagesArray, Message message) {
        System.out.println(message.getText());
    }

    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Use: UserName hostName port server");
            return;
        }
        System.out.println(args[0]);
        System.out.println(args[1]);
        System.out.println(args[2]);
        System.out.println(args[3]);
        System.out.println("I'm skipping for some reason");
        ChatPTUI ptui = new ChatPTUI(args[0], args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        System.out.println("And I dont know why");
    }

    public ChatPTUI(String username, String hostName, int port, int server) {
        model = new MessagesArray();
        boolean s = server == 1;
            netClient = new NetworkClient(hostName, port, username, model, s);
            while (true) {
                System.out.println("Enter message to send: ");
                Scanner scan = new Scanner(System.in);
                String message = scan.nextLine();
                System.out.println(message);
                try {
                    netClient.sendMessage(message);
                } catch (IOException IOE) {

                }
            }
    }
}
