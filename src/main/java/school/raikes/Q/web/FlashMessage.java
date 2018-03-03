package school.raikes.Q.web;

public class FlashMessage {

    public FlashMessage(){}

    public FlashMessage(String content, MessageType type) {
        this.content = content;
        this.type = type;
    }

    private MessageType type;
    private String content;

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public enum MessageType {
        SUCCESS,
        FAILURE
    }
}

