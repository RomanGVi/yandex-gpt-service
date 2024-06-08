package roman.yandexGptService.entity.chat;

public class ResultAlternatives {
    private Message message;
    private String status;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
