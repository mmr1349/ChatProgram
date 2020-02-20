package ptpchat;

/***********************************************************
    Description: This class is the data class used to store
    a message. Has no way to modify the data because it will
    be created and then we only need to read from it not modify
    it.
***********************************************************/
public class Message {
    private String text;
    private double time;
    private String sender;

    public Message(String text, double time, String sender) {
        this.text = text;
        this.time = time;
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public double getTime() {
        return time;
    }

    public String getSender() {
        return sender;
    }
}