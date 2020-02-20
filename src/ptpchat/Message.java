package ptpchat;

import javax.print.DocFlavor;
import java.io.Serializable;

/***********************************************************
    Description: This class is the data class used to store
    a message. Has no way to modify the data because it will
    be created and then we only need to read from it not modify
    it.
***********************************************************/
public class Message implements Serializable {
    private String text;
    private long time;
    private String address;
    private String  username;

    public Message(String text, long time, String address, String username) {
        this.text = text;
        this.time = time;
        this.username = username;
        this.address = address;
    }

    public String getText() {
        return text;
    }

    public long getTime() {
        return time;
    }

    public String getAddress() {
        return address;
    }

    public String getUsername() {
        return username;
    }
}