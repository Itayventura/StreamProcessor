# StreamProcessor

## Prerequisites
 - [x] Windows
 
 - [x] Java 11

 - [x] maven (+JAVA_HOME set to jdk)
 
## HTTP Interface

##### stat
GET localhost:8080/stat

RESPONSE:
String of JSONObject

key: wordsCount

value: {key: word,
    value: number of times it appeared since app started}
    
key: eventsCount

value: 

{key: event_type, value: number of times it appeared since app started} 

```
{
  "wordsCount": {
    "lorem": 82,
    "dolor": 70,
    "amet": 53,
    "ipsum": 68,
    "sit": 71
  },
  "eventsCount": {
    "bar": 127,
    "foo": 112,
    "baz": 105
  }
}
```
##### recent
GET localhost:8080/recent

RESPONSE:
stats of last 60 seconds.
same structure as above.
keys are: recentWordsCount, recentEventsCount respectively.
```
{
  "recentEventsCount": {
    "bar": 14,
    "foo": 16,
    "baz": 13
  },
  "recentWordsCount": {
    "lorem": 11,
    "dolor": 11,
    "amet": 6,
    "ipsum": 3,
    "sit": 12
  }
}
```

## Installation
1. Clone the repository:

    ```sh
    $ git clone https://github.com/Itayventura/StreamProcessor.git
    ```
   
2. Build and run tests:
    - from git bash:
        ```sh
        $ cd StreamProcessor/
        $ mvn clean install
        ```
     
    - [x] All modules should be built successfully

3. Run app (from terminal):
    ```sh
    mvn compile exec:java -Dexec.mainClass="app.App"
    ```

