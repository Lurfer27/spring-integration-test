package data.app.endpoints;

import data.app.model.ToData;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import java.util.List;

public class ToDataOutputRouter implements MessageHandler {

    private void handlePayload(ToData toData) {
        System.out.println(toData.toString());
    }

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        //System.out.println("HEADERS:" + message.getHeaders());
        handlePayload((ToData)message.getPayload());
    }
}
