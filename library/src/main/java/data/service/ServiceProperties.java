package data.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "service")
public class ServiceProperties {

    private int fromValue;
    private int toValue;

    public int getFromValue() {
        return fromValue;
    }

    public void setFromValue(int fromValue) {
        this.fromValue = fromValue;
    }

    public int getToValue() {
        return toValue;
    }

    public void setToValue(int toValue) {
        this.toValue = toValue;
    }
}
