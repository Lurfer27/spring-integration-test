package data.service;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class Service {

    private final int fromValue;
    private final int toValue;
    private int value;

    public Service(
        int fromValue,
        int toValue
    ) {
        this.fromValue = fromValue;
        this.toValue = toValue;
        this.value = fromValue;
    }

    public int fromValue() {
        return this.fromValue;
    }

    public int toValue() {
        return this.toValue;
    }

    public void resetValue() {
        this.value = this.fromValue;
    }

    public int getNextValue() {
        if (value > toValue) {
            throw new NoSuchElementException(String.valueOf(toValue));
        }
        return value++;
    }
}
