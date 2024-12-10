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
    if (num == 9) {
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

  private static void dfs2(List<List<Integer>> input, int x, int y, AtomicInteger count) {
    int num = input.get(y).get(x);
    if (num == 9) {
      count.incrementAndGet();
      return;
    }
    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];
      if (nx >= 0 && ny >= 0 && nx < input.get(0).size() && ny < input.size()
          && input.get(ny).get(nx) == num + 1) {
        dfs2(input, nx, ny, count);
      }
    }
  }

  public static void main(String[] args) {
    List<List<Integer>> input = readInput("Day10/input.txt");

    AtomicInteger count1 = new AtomicInteger(0);
    AtomicInteger count2 = new AtomicInteger(0);
    for (int y = 0; y < input.size(); y++) {
      for (int x = 0; x < input.get(0).size(); x++) {
        if (input.get(y).get(x) == 0) {
          boolean[][] visited = new boolean[input.size()][input.get(0).size()];
          dfs(input, x, y, count1, visited);
          dfs2(input, x, y, count2);
        }
      }
    }
    System.out.println("Result: " + count1);
    System.out.println("Result: " + count2);
  }
}
