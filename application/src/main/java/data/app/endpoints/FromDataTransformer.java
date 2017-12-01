package data.app.endpoints;

import data.app.model.FromData;
import data.app.model.ToData;
import org.springframework.integration.transformer.AbstractTransformer;
import org.springframework.messaging.Message;

public class FromDataTransformer extends AbstractTransformer {

    public ToData transform(FromData fromData) {
        return new ToData(fromData.getId());
    }

    @Override
    protected Object doTransform(Message<?> message) throws Exception {
        return transform((FromData)message.getPayload());
    }
}
