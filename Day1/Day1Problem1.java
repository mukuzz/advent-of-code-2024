import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class Day1Problem1 {

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
    PriorityQueue<Integer> minHeap1 = new PriorityQueue<>();
    PriorityQueue<Integer> minHeap2 = new PriorityQueue<>();
    for (int[] pair : columns) {
      minHeap1.add(pair[0]);
      minHeap2.add(pair[1]);
    }

    int sum = 0;
    while (!minHeap1.isEmpty()) {
      sum += Math.abs(minHeap1.poll() - minHeap2.poll());
      System.out.println("Sum: " + sum);
    }

    System.out.println("Answer: " + sum);
  }
}
