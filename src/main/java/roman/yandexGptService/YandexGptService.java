package roman.yandexGptService;

import com.alibaba.fastjson2.JSON;
import roman.yandexGptService.entity.chat.ChatException;
import roman.yandexGptService.entity.chat.Completion;
import roman.yandexGptService.entity.chat.CompletionResponse;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class YandexGptService {
    private final static String API_HOST = "https://llm.api.cloud.yandex.net/foundationModels/v1/completion";
    private final String FOLDER_ID;
    private final String API_KEY;

    public YandexGptService(String folderId, String apiKey) {
        this.FOLDER_ID = folderId;
        this.API_KEY = apiKey;
    }

    public static YandexGptServiceBuilder builder() {
        return new YandexGptServiceBuilder();
    }

    public CompletionResponse chatCompletion(Completion completion) {
        String modelUri = "gpt://" + FOLDER_ID + "/" + completion.getModel().getName();
        completion.setModelUri(modelUri);

        String jsonResponse = doRequest(completion);

        return JSON.parseObject(jsonResponse, CompletionResponse.class);
    }

    private String doRequest(Completion completion) {
        String jsonResponse;
        try {
            String jsonBody = JSON.toJSONString(completion);
            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(new URI(API_HOST))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Api-Key " + API_KEY)
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();
            HttpClient httpClient = HttpClient.newBuilder().build();
            HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
            if (postResponse.statusCode() != HttpURLConnection.HTTP_OK) {
                throw new ChatException("не удалось выполнить запрос\nHTTP status: " + postResponse.statusCode() + "\n" + postResponse.body());
            }

            jsonResponse = postResponse.body();

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Не удалось выполнить запрос", e);
        }
        return jsonResponse;
    }

    public static class YandexGptServiceBuilder {
        private String folderId;
        private String apiKey;

        public YandexGptServiceBuilder folderId(String folderId) {
            this.folderId = folderId;
            return this;
        }

        public YandexGptServiceBuilder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public YandexGptService build() {
            if (folderId == null) throw new IllegalArgumentException("FOLDER_ID must be set!");
            if (apiKey == null) throw new IllegalArgumentException("API_KEY must be set!");
            return new YandexGptService(folderId, apiKey);
        }
    }
}