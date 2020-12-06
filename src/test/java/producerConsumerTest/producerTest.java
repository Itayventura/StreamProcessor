package producerConsumerTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import producerConsumer.Producer;

public class producerTest {

    public static Producer producer;

    @Before
    public void setUp(){
        producer = new Producer();
    }

    @Test
    public void isValidJsonTest(){
        String text = " { \"event_type\": \"bar\", \"data\": \"sit\", \"timestamp\": 1607194971 }\n";
        Assert.assertTrue(producer.isValidJson(text));
        Assert.assertFalse(producer.isValidJson(text.substring(2)));
        text = "דגכחלךמדג";
        Assert.assertFalse(producer.isValidJson(text));
        text = "{event_type}";
        Assert.assertFalse(producer.isValidJson(text));
        text = " { \"event_type\":";
        Assert.assertFalse(producer.isValidJson(text));
    }

    @Test
    public void isValidStructureTest(){
        String text = " { \"event_type\": \"bar\", \"data\": \"sit\", \"timestamp\": 1607194971 }\n";
        Assert.assertTrue(producer.isValidJson(text));
        Assert.assertFalse(producer.isValidJson(text.substring(10)));
        text = "דגכחלךמדג";
        Assert.assertFalse(producer.isValidJson(text));
        text = "{event_type}";
        Assert.assertFalse(producer.isValidJson(text));
        text = " { \"event_type\":";
        Assert.assertFalse(producer.isValidJson(text));
    }
}
