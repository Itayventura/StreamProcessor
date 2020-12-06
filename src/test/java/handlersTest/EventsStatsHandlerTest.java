package handlersTest;

import handlers.EventsStatsHandler;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class EventsStatsHandlerTest {

    static EventsStatsHandler eventsStatsHandler;
    static HashMap<Long, HashMap<String, Integer>> map;
    static long second;

    @Before
    public void setUpVariables(){
        map = new HashMap<>();
        second = System.currentTimeMillis()/1000;
        for(long i = second; i > second - 100; i--){
            HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
            stringIntegerHashMap.put("data", 1);
            stringIntegerHashMap.put("computer", 2);
            map.put(i, stringIntegerHashMap);
        }

        eventsStatsHandler = new EventsStatsHandler();
    }

    @Test
    public void updateSecondsHashMapTest(){
        long second = 1;
        int expected = 1;
        String str = "data";
        EventsStatsHandler.updateSecondsHashMap(map, second, str);
        Assert.assertTrue(expected == map.get(second).get(str));
        str = "newData";
        EventsStatsHandler.updateSecondsHashMap(map, second, str);
        EventsStatsHandler.updateSecondsHashMap(map, second, str);
        expected = 2;
        Assert.assertTrue(expected == map.get(second).get(str));
    }

    @Test
    public void getStatsTest(){
        String line = "{ \"event_type\": \"bar\", \"data\": \"lorem\", \"timestamp\": 1607194971 }";
        EventsStatsHandler.addEvent(line);
        String jsonString = EventsStatsHandler.getStats();
        JSONObject jsonObject = new JSONObject(jsonString);
        int loremCnt = jsonObject.getJSONObject("wordsCount").getInt("lorem");
        Assert.assertTrue(1 == loremCnt);
        int eventCnt = jsonObject.getJSONObject("eventsCount").getInt("bar");
        Assert.assertTrue(1 == eventCnt);
    }

    @Test
    public void getRecentStatsTest(){
        long currentSecond = System.currentTimeMillis()/1000;
        String []lines = {"{ \"event_type\": \"bar\", \"data\": \"lorem\", \"timestamp\": " + currentSecond +" }",
                " { \"event_type\": \"bar\", \"data\": \"sit\", \"timestamp\": 1607194971 }\n",
                "{ \"event_type\": \"foo\", \"data\": \"lorem\", \"timestamp\": 1607194971 }\n",
                "{ \"event_type\": \"foo\", \"data\": \"sit\", \"timestamp\": 1607193962 }"};
        for(String line: lines){
            EventsStatsHandler.addEvent(line);
        }
        String jsonString = EventsStatsHandler.getRecentStats();
        JSONObject jsonObject = new JSONObject(jsonString);
        int loremCnt = jsonObject.getJSONObject("recentWordsCount").getInt("lorem");
        Assert.assertTrue(1 == loremCnt);
        int eventCnt = jsonObject.getJSONObject("recentEventsCount").getInt("bar");
        Assert.assertTrue(1 == eventCnt);
    }

    @Test
    public void getMergeRecentMapTest(){
        HashMap<String, Integer> merged = EventsStatsHandler.getMergeRecentMap(map,second);
        Assert.assertTrue(60 == merged.get("data"));

    }
}
