package data.app.endpoints;

import org.springframework.integration.splitter.AbstractMessageSplitter;
import org.springframework.messaging.Message;

public class FromDataSplitter extends AbstractMessageSplitter {

    @Override
    protected Object splitMessage(Message<?> message) {
        return message;
    }
}
