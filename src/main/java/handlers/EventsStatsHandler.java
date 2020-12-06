package handlers;

import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class EventsStatsHandler {

    private static final long RECENT_TIME_IN_SECONDS = 60;

    private static HashMap<String, Integer> wordsCnt;
    private static HashMap<String, Integer> eventsCnt;
    private static HashMap<Long, HashMap<String, Integer>> secondsWordsCnt;
    private static HashMap<Long, HashMap<String, Integer>> secondsEventsCnt;

    public EventsStatsHandler(){
        wordsCnt = new HashMap<>();
        eventsCnt = new HashMap<>();
        secondsWordsCnt = new HashMap<>();
        secondsEventsCnt = new HashMap<>();
    }

    public static void addEvent(String line){
        /**
         * @param line a valid json line output by the exe file.
         * updates the statistics after processing the line
         */
        JSONObject jsonObject = new JSONObject(line);
        String eventType = jsonObject.getString("event_type");
        String word = jsonObject.getString("data");
        Long second = jsonObject.getLong("timestamp");

        int cnt = eventsCnt.containsKey(eventType)? eventsCnt.get(eventType):0;
        eventsCnt.put(eventType, cnt+1);
        updateSecondsHashMap(secondsEventsCnt,second,eventType);

        cnt = wordsCnt.containsKey(word)? wordsCnt.get(word):0;
        wordsCnt.put(word, cnt+1);
        updateSecondsHashMap(secondsWordsCnt, second, word);

    }

    public static void updateSecondsHashMap(HashMap<Long, HashMap<String, Integer>> map,
                                            Long second, String str) {
        HashMap<String, Integer> currentCnt;
        if(map.containsKey(second)){
            currentCnt = map.get(second);
            int cnt = currentCnt.containsKey(str)? currentCnt.get(str):0;
            currentCnt.put(str, cnt+1);
        } else{
            currentCnt = new HashMap<>();
            currentCnt.put(str, 1);
        }
        map.put(second, currentCnt);
    }

    /**
     * @return json string of statistics
     */
    public static String getStats(){
        HashMap<Object, Object> statistics = new HashMap<>();

        statistics.put("eventsCount",eventsCnt);
        statistics.put("wordsCount",wordsCnt);

        return (new JSONObject(statistics)).toString();
    }

    /**
     * @return json string of recent statistics
     */
    public static String getRecentStats(){
        long currentSec = System.currentTimeMillis()/1000;

        HashMap<String, Integer> recentEventsCnt = getMergeRecentMap(secondsEventsCnt, currentSec);
        HashMap<String, Integer> recentWordsCnt = getMergeRecentMap(secondsWordsCnt, currentSec);

        HashMap<Object, Object> recentStatistics = new HashMap<>();

        recentStatistics.put("recentEventsCount",recentEventsCnt);
        recentStatistics.put("recentWordsCount",recentWordsCnt);

        return (new JSONObject(recentStatistics)).toString();
    }

    public static HashMap<String, Integer> getMergeRecentMap(HashMap<Long, HashMap<String, Integer>> map,
                                                              long currentSec){
        HashMap<String, Integer> recentStatCnt = new HashMap<>();
        for(long second = currentSec; second > currentSec-RECENT_TIME_IN_SECONDS; second--) {
            if (map.containsKey(second)) {
                HashMap<String, Integer> secondStatCnt = map.get(second);
                for (Map.Entry<String, Integer> pair : secondStatCnt.entrySet()) {
                    int cnt = recentStatCnt.containsKey(pair.getKey()) ? recentStatCnt.get(pair.getKey()) : 0;
                    recentStatCnt.put(pair.getKey(), cnt + pair.getValue());
                }
            }
        }
        return recentStatCnt;
    }

}
