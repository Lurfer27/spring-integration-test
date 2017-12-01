package data.app.stream;

import org.springframework.integration.endpoint.AbstractMessageSource;

public class MyMessageSource extends AbstractMessageSource<Integer> {

    @Override
    protected Object doReceive() {
        return null;
    }

    @Override
    public String getComponentType() {
        return null;
    }
}
