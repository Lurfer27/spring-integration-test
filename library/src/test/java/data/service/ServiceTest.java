package data.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;

@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = ServiceConfiguration.class,
    properties = {
        "service.message=Hello",
        "service.fromValue=-1",
        "service.toValue=10"
    },
    webEnvironment = SpringBootTest.WebEnvironment.NONE
)
public class ServiceTest {

    @Autowired
    private Service service;

    @Before
    public void setUp() {
        service.resetValue();
    }

    @Test
    public void fromValueInitialised() {
        Assert.assertEquals(-1, service.fromValue());
    }

    @Test
    public void toValueInitialised() {
        Assert.assertEquals(10, service.toValue());
    }

    @Test
    public void getNextValueAfterInit() {
        Assert.assertEquals(-1, service.getNextValue());
    }

    @Test
    public void canResetValue() {
        service.getNextValue();
        service.resetValue();
        Assert.assertEquals(-1, service.getNextValue());
    }

    @Test
    public void ensureValueIncremented() {
        service.getNextValue();
        Assert.assertEquals(0, service.getNextValue());
    }

    @Test
    public void ensureValueIncrementsUntilToValue() {
        for (int i=1; i<12; i++) {
            service.getNextValue();
        }
        Assert.assertEquals(10, service.getNextValue());
    }

    @Test(expected = NoSuchElementException.class)
    public void exceptionAtEndOfValueFetch() {
        for (int i=0; i<12; i++) {
            service.getNextValue();
        }
        service.getNextValue();
    }

    @Test(expected = NoSuchElementException.class)
    public void noValuesIfFromNotBeforeTo() {
        service = new Service(5,4);
        service.getNextValue();
    }
}