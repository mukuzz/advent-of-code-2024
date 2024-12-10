package Day10;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.*;

public class Day10 {

  private static List<List<Integer>> readInput(String filename) {
    List<List<Integer>> numbers = new ArrayList<>();
    try {
      File file = new File(filename);
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        List<Integer> row = new ArrayList<>();
        for (char ch : line.toCharArray()) {
          row.add(Character.getNumericValue(ch));
        }
        numbers.add(row);
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return numbers;
  }

  private static int[] dx = { 0, 1, 0, -1 };
  private static int[] dy = { -1, 0, 1, 0 };

  private static void dfs(List<List<Integer>> input, int x, int y, AtomicInteger count,
      boolean[][] visited) {
    int num = input.get(y).get(x);
    System.out.print(x + "," + y + ":" + num + " - ");
    if (num == 9) {
      System.out.println("");
      if (!visited[y][x]) {
        count.incrementAndGet();
      }
      visited[y][x] = true;
      return;
    }
    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];
      if (nx >= 0 && ny >= 0 && nx < input.get(0).size() && ny < input.size()
          && input.get(ny).get(nx) == num + 1) {
        dfs(input, nx, ny, count, visited);
      }
    }
  }

  public static void main(String[] args) {
    List<List<Integer>> input = readInput("Day10/sampleInput.txt");
    System.out.println(input);

    AtomicInteger count = new AtomicInteger(0);
    for (int y = 0; y < input.size(); y++) {
      for (int x = 0; x < input.get(0).size(); x++) {
        if (input.get(y).get(x) == 0) {
          boolean[][] visited = new boolean[input.size()][input.get(0).size()];
          dfs(input, x, y, count, visited);
        }
      }
    }
    System.out.println("Result: " + count);
  }
}
