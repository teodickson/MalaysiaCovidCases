package utils;

import java.io.IOException;
import java.util.List;

public interface SendMessage {

    void sendToTelegram(String message) throws IOException, InterruptedException;

    void sendToTelegram(List<String> messages) throws IOException, InterruptedException;
}
