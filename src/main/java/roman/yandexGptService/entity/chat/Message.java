package roman.yandexGptService.entity.chat;

import com.alibaba.fastjson2.annotation.JSONField;

import java.util.Objects;

@SuppressWarnings("unused")
public class Message {
    @JSONField(ordinal = 1)
    private String role;
    @JSONField(name = "text", ordinal = 2)
    private String content;

    public Message(String role, String content) {
        this.role = role;
        this.content = content;
    }

    /**
     * роль отправителя сообщения:
     * user — предназначена для отправки пользовательских сообщений к модели.
     * @param content — текстовое содержимое сообщения.
     * @return новое сообщение
     */
    public static Message of(String content) {
        return new Message(Role.USER.getName(), content);
    }

    /**
     * роль отправителя сообщения:
     * system — позволяет задать контекст запроса и определить поведение модели. (Prompt)
     * @param content — текстовое содержимое сообщения.
     * @return новое сообщение
     */
    public static Message ofSystem(String content) {
        return new Message(Role.SYSTEM.getName(), content);
    }

    public static Message ofAssistant(String content) {
        return new Message(Role.ASSISTANT.getName(), content);
    }


    public String getRole() {
        return role;
    }

    public String getContent() {
        return content;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "role='" + role + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (!role.equals(message.role)) return false;
        return Objects.equals(content, message.content);
    }

    @Override
    public int hashCode() {
        int result = role.hashCode();
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    public enum Role {
        USER("user"),
        SYSTEM("system"),
        ASSISTANT("assistant")
        ;

        private final String name;
        Role(String value) {
            this.name = value;
        }
        public String getName() {
            return this.name;
        }

    }
}
