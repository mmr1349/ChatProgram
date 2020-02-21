package ptpchat;

import java.util.ArrayList;
import java.util.LinkedList;

public class MessagesArray {
    private LinkedList<Observer<MessagesArray, Message>> observers = new LinkedList<>();
    private ArrayList<Message> messages;

    public MessagesArray(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public MessagesArray() {
        messages = new ArrayList<Message>();
    }

    public void addObserver(Observer<MessagesArray, Message> observer) {
        observers.add(observer);
    }

    public void notifyObservers(Message message) {
        for (Observer o: observers) {
            o.update(this, message);
        }
    }

    public void addMessage(Message toAdd) {
        messages.add(toAdd);
        notifyObservers(toAdd);
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

    public ArrayList<Message> getMessagesAddress(String address) {
        ArrayList<Message> sendersMessages = new ArrayList<Message>();
        for (Message m: messages) {
            if (m.getAddress().compareTo(address) == 0) {
                sendersMessages.add(m);
            }
        }
        return sendersMessages;
    }

    public ArrayList<Message> getMessagesUsername(String username) {
        ArrayList<Message> sendersMessages = new ArrayList<Message>();
        for (Message m: messages) {
            if (m.getUsername().compareTo(username) == 0) {
                sendersMessages.add(m);
            }
        }
        return sendersMessages;
    }
}
