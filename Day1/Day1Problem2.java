import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class Day1Problem2 {

  public static List<int[]> readTwoColumns(String filePath) {
    List<int[]> columns = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split("\\s+");
        if (values.length >= 2) {
          int[] pair = new int[2];
          pair[0] = Integer.parseInt(values[0]);
          pair[1] = Integer.parseInt(values[1]);
          columns.add(pair);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return columns;
  }


  public static void main(String []args) {
    List<int[]> columns = readTwoColumns("Problem1Input.txt");
    Map<Integer, Integer> list2Freq = new HashMap<>();
    List<Integer> list1 = new ArrayList<>();
    for (int[] pair : columns) {
      list1.add(pair[0]);
      list2Freq.put(pair[1], list2Freq.getOrDefault(pair[1], 0) + 1);
    }

    int simScore = 0;

    for (int num: list1) {
      int numFreqInList2 = list2Freq.getOrDefault(num, 0);
      simScore += num * numFreqInList2;
    }

    System.out.println("Answer: " + simScore);
  }
}
