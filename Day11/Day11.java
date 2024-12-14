package Day11;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day11 {

  public static LinkedList<String> readInput(String filePath) {
    LinkedList<String> list = new LinkedList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] nums = line.split(" ");
        for (String num : nums) {
          list.add(num);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return list;
  }

  public static Map<String, Long> calc(Map<String, Long> map, int blinks) {
    for (int i = 0; i < blinks; i++) {
      Map<String, Long> newMap = new HashMap<>(map);
      for (Map.Entry<String, Long> entry : map.entrySet()) {
        String num = entry.getKey();
        long count = entry.getValue();
        newMap.put(num, newMap.getOrDefault(num, 0L) - count);
        if ("0".equals(num)) {
          newMap.put("1", newMap.getOrDefault("1", 0L) + count);
        } else if (num.length() % 2 == 0) {
          String num1 = String.valueOf(Long.parseLong(num.substring(0, num.length() / 2)));
          String num2 = String.valueOf(Long.parseLong(num.substring(num.length() / 2)));
          newMap.put(num1, newMap.getOrDefault(num1, 0L) + count);
          newMap.put(num2, newMap.getOrDefault(num2, 0L) + count);
        } else {
          String newNum = String.valueOf(Long.parseLong(num) * 2024);
          newMap.put(newNum, newMap.getOrDefault(newNum, 0L) + count);
        }
      }
      map = newMap;
      System.out.println("Blink count: " + i);
    }
    return map;
  }

  public static void main(String[] args) {
    LinkedList<String> list = readInput("Day11/input.txt");
    Map<String, Long> map = new HashMap<>();
    for (String num : list) {
      map.put(num, 1L);
    }
    System.out.println(map);
    map = calc(map, 75);
    long sum = 0;
    for (long count : map.values()) {
      sum += count;
    }
    System.out.println("Result: " + sum);
  }
}

// 512 -> 1
// 72 -> 1
// 2024 -> 1
// 2 -> 2
// 0 -> 1
// 4 -> 1
// 2867 -> 1
// 6032 -> 1