package antarych.app.doubleadapter.models;

/**
 * Created by Antarych on 04.10.2016.
 */

public class MessageModel extends MessengerObject {
    public String sender;
    public String time;

    public MessageModel(String text, String sender, String time) {
        this.text = text;
        this.sender = sender;
        this.time = time;
    }
}
