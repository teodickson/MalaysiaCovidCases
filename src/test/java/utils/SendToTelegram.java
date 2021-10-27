package utils;


import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

public class SendToTelegram implements SendMessage {

    //private static final String CHAT_ID = "1247269163";
    private static final String CHAT_ID = "-1001605268294"; // for channel
    private static final String TOKEN = "2066670954:AAFo3w3TzPKg1y6rGrlhIOEkeuK55VAFGJE";

    public void sendToTelegram(String message) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .version(HttpClient.Version.HTTP_2)
                .build();

        UriBuilder builder = UriBuilder
                .fromUri("https://api.telegram.org")
                .path("/{token}/sendMessage")
                .queryParam("chat_id", CHAT_ID)
                .queryParam("text", message);

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(builder.build("bot" + TOKEN))
                .timeout(Duration.ofSeconds(5))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
    }

    public void sendToTelegram(List<String> messages) throws IOException, InterruptedException {
        //sendToTelegram(messages.toString());
        String msg = String.join("\n",messages);
        sendToTelegram(msg);
    }
}
