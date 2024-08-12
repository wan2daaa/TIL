# Array

## Arrays

```java
import java.util.Arrays;
import java.util.List;

public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5};


    Arrays.sort(arr);
    Arrays.fill(arr, 0);
    Arrays.fill(arr, 0, 2, 1);
    int findOne = Arrays.binarySearch(arr, 0);

    List<int[]> list = Arrays.asList(arr);// List<Integer>로 변환 불가능
    List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
}
```

### Array to Stream

```java
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5};

    IntStream stream = Arrays.stream(arr);
    OptionalInt max = stream.max();
    OptionalInt min = stream.min();

    int asInt = max.getAsInt();
}
```
# List

## List To Array

```java
import java.util.*;

public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(2);
    list.add(3);

    int[] intArray = list.stream().mapToInt(Integer::intValue).toArray();
    int[] intArray = list.stream().mapToInt(i -> i).toArray();
}
```

# String

```java
public static void main(String[] args) {
    String str = "Hello";
    str.charAt(0); // 'H'
    str.length(); // 5

    boolean isStartsWith = str.startsWith("H");// true
    boolean isEndsWith = str.endsWith("o");// true
    String[] s = str.split(" ");

    char[] charArray = str.toCharArray();

    String charArrayToString = new String(charArray);
}

```

# Map

```java
import java.util.HashMap;

public static void main(String[] args) {
    HashMap<String, String> map = new HashMap<>();

    map.put("key", "value");
    map.get("key"); // value

    boolean key = map.containsKey("key");// true

    String orDefault = map.getOrDefault("key", "default");

}
```

### Map Stream

```java

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public static void main(String[] args) {
    HashMap<Integer, Integer> map = new HashMap<>();

    Stream<Map.Entry<Integer, Integer>> stream = map.entrySet().stream(); // 각 엔트리, entry.getKey(), getValue() 가능
    Stream<Map.Entry<Integer, Integer>> sorted = stream.sorted();
    Stream<Map.Entry<Integer, Integer>> filter = sorted.filter(e -> e.getValue() > 0);
    int[] array = filter.mapToInt(Map.Entry::getKey)
            .toArray();
}
```

# Set 

TreeSet

```java
import java.util.TreeSet;

public static void main(String[] args) {
    TreeSet<Integer> set = new TreeSet<>();

    set.add(1);

    set.pollFirst();
    set.pollLast();

    boolean contains = set.contains(1);// true
    
}
```


# Stack

```java
import java.util.ArrayDeque;
import java.util.Stack;

public static void main(String[] args) {
    ArrayDeque<Integer> stack = new ArrayDeque<>();

    stack.push(1);
    Integer peek = stack.peek(); // pop 하지 않고 꺼내기
    int pop = stack.pop(); // 꺼내기

    boolean isEmpty = stack.isEmpty();

    Stack<Integer>[] stackArr = new Stack[3]; //null로 초기화

    for(int i = 0 ; i < 3 ; i++) {
        stackArr[i] = new Stack<>();
    }
}
```

# 큐

```java
import java.util.*;

public static void main(String[] args) {
    String[] cards1 = {"1", "2", "3", "4", "5"};
    ArrayDeque<String> deque = new ArrayDeque<>(Arrays.asList(cards1));

    deque.pollFirst();
    deque.pollLast();
    
    boolean isEmpty = deque.isEmpty();

    deque.peekFirst();
    deque.peekLast();
    
    
}   
```