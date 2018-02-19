package com.yodel.integration.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;

import java.io.StringReader;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class StringCsvReaderTest {

    @Mock
    private BeanFactory mockBeanFactory;

    @Test
    public void toListWorks() throws Exception {
        // Given
        StringReader stringReader = new StringReader("World,Cup");
        StringReader spyStringReader = Mockito.spy(stringReader);
        Mockito.doReturn(spyStringReader).when(this.mockBeanFactory).getBean("stringReaderBean", "World,Cup");
        Mockito.doReturn(new StringBuilder())
                .doReturn(new StringBuilder())
                .when(this.mockBeanFactory).getBean("stringBuilderBean", StringBuilder.class);

        StringCsvReader stringCsvReader = new StringCsvReader();
        stringCsvReader.setBeanFactory(this.mockBeanFactory);

        // When
        List<?> actual = stringCsvReader.toList("World,Cup");

        // Then
        Assert.assertNotNull("Not Null", actual);
        Assert.assertEquals("Count", 2, actual.size());
        Assert.assertEquals("1st", "World", actual.get(0));
        Assert.assertEquals("2nd", "Cup", actual.get(1));
    }
}
