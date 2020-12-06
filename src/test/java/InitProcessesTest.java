import handlers.EventsHandler;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class InitProcessesTest {

   private static HashMap<String, Integer> wordsCnt;
   private static HashMap<String, Integer> eventsCnt;
   private static HashMap<Long, HashMap<String, Integer>> secondsWordsCnt;
   private static HashMap<Long, HashMap<String, Integer>> secondsEventsCnt;

   @Before
   public void init(){
      wordsCnt = new HashMap<>();
      eventsCnt = new HashMap<>();
      secondsWordsCnt = new HashMap<>();
      secondsEventsCnt = new HashMap<>();
   }

   @Test
    public void test2(){
       EventsHandler IP = new EventsHandler();
       IP.init();
   }
/*
   @Test
   public void test() {

      String []lines = {"{ \"event_type\": \"bar\", \"data\": \"lorem\", \"timestamp\": 1607194971 }",
      " { \"event_type\": \"bar\", \"data\": \"sit\", \"timestamp\": 1607194971 }\n",
      "{ \"event_type\": \"foo\", \"data\": \"lorem\", \"timestamp\": 1607194971 }\n",
      "{ \"event_type\": \"foo\", \"data\": \"sit\", \"timestamp\": 1607193962 }"};

      for(String line: lines){
         JSONObject jsonObject = new JSONObject(line);
         String eventType = jsonObject.getString("event_type");
         String word = jsonObject.getString("data");
         Long second = jsonObject.getLong("timestamp")/1000;

         int cnt = eventsCnt.containsKey(eventType)? eventsCnt.get(eventType):0;
         eventsCnt.put(eventType, cnt+1);
         DataHandler.updateSecondsHashMap(secondsEventsCnt,second,eventType);

         cnt = wordsCnt.containsKey(word)? wordsCnt.get(word):0;
         wordsCnt.put(word, cnt+1);
         DataHandler.updateSecondsHashMap(secondsWordsCnt, second, word);
      }

      Iterator it = secondsWordsCnt.entrySet().iterator();
      System.out.println("secondsWordsCnt");
      while (it.hasNext()) {
         Map.Entry pair = (Map.Entry)it.next();
         System.out.println(pair.getKey() + " = " + pair.getValue());
      }

      it = secondsEventsCnt.entrySet().iterator();
      System.out.println("secondsEventsCnt");
      while (it.hasNext()) {
         Map.Entry pair = (Map.Entry)it.next();
         System.out.println(pair.getKey() + " = " + pair.getValue());
      }

      long currentSec = System.currentTimeMillis();
      System.out.println("current = " + currentSec);
      currentSec /= 1000000;
      System.out.println("current = " + currentSec);
      for(long second = currentSec; second > currentSec-60; second--) {
         System.out.println("second = " + second);
      }

      //HashMap<String, Integer> map = DatasHandler.getMergedRecentMap(secondsEventsCnt, 1607194);
      //System.out.println("map\n" + map);




   }
*/
}
