package data.app.stream;

import data.service.Service;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import java.util.NoSuchElementException;
import java.util.function.IntSupplier;

public class MySupplier implements IntSupplier {

    private Service service;

    public MySupplier(Service service) {
        this.service = service;
    }

    @Override
    public int getAsInt() {
        return service.getNextValue();
    }
}
