package ptpchat;

import java.util.ArrayList;

public class MessagesArray {
    private ArrayList<Message> messages;

    public MessagesArray(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public MessagesArray() {
        messages = new ArrayList<Message>();
    }

    public void addMessage(Message toAdd) {
        messages.add(toAdd);
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public Message getMessageIndex(int index) {
        return messages.get(index);
    }

    public Message getMessageTime(double time) {
        for (Message m: messages) {
            if (m.getTime() == time) {
                return m;
            }
        }
        return null;
    }

    public Message searchForMessage(String searchFor) {
        for (Message m: messages) {
            if (m.getText().contains(searchFor)) {
                return m;
            }
        }
        return null;
    }

    public ArrayList<Message> getMessagesSender(String sender) {
        ArrayList<Message> sendersMessages = new ArrayList<Message>();
        for (Message m: messages) {
            if (m.getSender().compareTo(sender) == 0) {
                sendersMessages.add(m);
            }
        }
        return sendersMessages;
    }
}
