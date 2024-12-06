import java.util.*;
import java.io.*;

public class Day6 {

  private static List<List<Character>> getInput(String fileName) {
    List<List<Character>> input = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        List<Character> row = new ArrayList<>();
        for (char c : line.toCharArray()) {
          row.add(c);
        }
        input.add(row);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return input;
  }

  private static int getPathCount(List<List<Character>> input, int y, int x) {
    int count = 0;
    int n = input.size();
    int m = input.get(0).size();
    int dir = 0;
    int[] dx = { 0, 1, 0, -1 };
    int[] dy = { -1, 0, 1, 0 };
    while (x >= 0 && y >= 0 && x < m && y < n) {
      if (input.get(y).get(x) != 'X')
        count++;
      input.get(y).set(x, 'X');
      int nx = x + dx[dir];
      int ny = y + dy[dir];
      if (nx >= 0 && ny >= 0 && nx < m && ny < n && input.get(ny).get(nx) == '#') {
        dir = (dir + 1) % 4;
      }
      x += dx[dir];
      y += dy[dir];
    }
    return count;
  }

  private static boolean isInLoop(List<List<Character>> input, int y, int x) {
    int n = input.size();
    int m = input.get(0).size();
    int dir = 0;
    int[] dx = { 0, 1, 0, -1 };
    int[] dy = { -1, 0, 1, 0 };
    Map<String, Integer> visitedCount = new HashMap<>();
    while (x >= 0 && y >= 0 && x < m && y < n) {
      int nx = x + dx[dir];
      int ny = y + dy[dir];
      while (nx >= 0 && ny >= 0 && nx < m && ny < n &&
          (input.get(ny).get(nx) == '#' || input.get(ny).get(nx) == 'O')) {
        String key = x + "," + y + "," + nx + "," + ny;
        int visitedCountForPos = visitedCount.getOrDefault(key, 0) + 1;
        visitedCount.put(key, visitedCountForPos);
        if (visitedCount.get(key) == 6) {
          return true;
        }
        dir = (dir + 1) % 4;
        nx = x + dx[dir];
        ny = y + dy[dir];
      }
      x += dx[dir];
      y += dy[dir];
    }
    return false;
  }

  private static int getLoopCount(List<List<Character>> input, int y, int x) {
    int count = 0;
    int n = input.size();
    int m = input.get(0).size();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (input.get(j).get(i) == 'X') {
          input.get(j).set(i, 'O');
          if (isInLoop(input, y, x)) {
            count++;
          }
          input.get(j).set(i, 'X');
        }
      }
    }
    return count;
  }

  public static void main(String[] args) {
    List<List<Character>> input = getInput("input.txt");
    for (int i = 0; i < input.size(); i++) {
      for (int j = 0; j < input.get(i).size(); j++) {
        if (input.get(i).get(j) == '^') {
          System.out.println("Answer: " + getPathCount(input, i, j));
          System.out.println("Anster: " + getLoopCount(input, i, j));
          return;
        }
      }
    }
  }
}
