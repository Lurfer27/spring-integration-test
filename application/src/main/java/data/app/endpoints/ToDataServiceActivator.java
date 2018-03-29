package data.app.endpoints;

import data.app.model.ToData;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
public class ToDataServiceActivator {

    @ServiceActivator
    public ToData setToOk(ToData toData) {
        toData.setHandled(true);
        return toData;
    }
}
