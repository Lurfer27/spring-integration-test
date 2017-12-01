package data.app.endpoints;

import data.app.model.FromData;
import org.springframework.core.convert.converter.Converter;
import org.springframework.integration.config.IntegrationConverter;
import org.springframework.stereotype.Component;

@Component
@IntegrationConverter
public class IntegerToDataConverter implements Converter<Integer, FromData> {

    public FromData convert(Integer source) {
        return new FromData(source);
    }
}
