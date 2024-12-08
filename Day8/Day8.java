import java.util.*;
import java.io.*;

public class Day8 {

  private static class Node {
    int x;
    int y;

    Node(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null || getClass() != obj.getClass())
        return false;
      Node node = (Node) obj;
      return x == node.x && y == node.y;
    }

    @Override
    public String toString() {
      return "Node [x=" + x + ", y=" + y + "]";
    }
  }

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

  private static void part1(List<List<Character>> input) {
    Map<Character, List<Node>> map = new HashMap<>();
    for (int y = 0; y < input.size(); y++) {
      for (int x = 0; x < input.get(0).size(); x++) {
        if (Character.isLetterOrDigit(input.get(y).get(x))) {
          map.putIfAbsent(input.get(y).get(x), new ArrayList<>());
          map.get(input.get(y).get(x)).add(new Node(x, y));
        }
      }
    }
    Set<Node> antiNode = new HashSet<>();
    for (Map.Entry<Character, List<Node>> entry : map.entrySet()) {
      char ch = entry.getKey();
      List<Node> positions = entry.getValue();
      for (int i = 0; i < positions.size(); i++) {
        for (int j = 0; j < positions.size(); j++) {
          if (i != j) {
            Node pos1 = positions.get(i);
            Node pos2 = positions.get(j);
            Node delta = new Node(pos1.x - pos2.x, pos1.y - pos2.y);
            Node newPos = new Node(pos2.x - delta.x, pos2.y - delta.y);
            if (newPos.x >= 0 && newPos.y >= 0 && newPos.x < input.get(0).size() &&
                newPos.y < input.size()) {
              antiNode.add(newPos);
            }
          }
        }
      }
    }
    System.out.println("Anti node count: " + antiNode.size());
  }

  private static void part2(List<List<Character>> input) {
    Map<Character, List<Node>> map = new HashMap<>();
    for (int y = 0; y < input.size(); y++) {
      for (int x = 0; x < input.get(0).size(); x++) {
        if (Character.isLetterOrDigit(input.get(y).get(x))) {
          map.putIfAbsent(input.get(y).get(x), new ArrayList<>());
          map.get(input.get(y).get(x)).add(new Node(x, y));
        }
      }
    }
    Set<Node> antiNode = new HashSet<>();
    for (Map.Entry<Character, List<Node>> entry : map.entrySet()) {
      char ch = entry.getKey();
      List<Node> positions = entry.getValue();
      for (int i = 0; i < positions.size(); i++) {
        for (int j = 0; j < positions.size(); j++) {
          if (i != j) {
            Node pos1 = positions.get(i);
            Node pos2 = positions.get(j);
            antiNode.add(pos1);
            Node delta = new Node(pos1.x - pos2.x, pos1.y - pos2.y);
            Node newPos = new Node(pos2.x - delta.x, pos2.y - delta.y);
            while (newPos.x >= 0 && newPos.y >= 0 && newPos.x < input.get(0).size() &&
                newPos.y < input.size()) {
              antiNode.add(newPos);
              newPos = new Node(newPos.x - delta.x, newPos.y - delta.y);
            }
          }
        }
      }
    }
    System.out.println("Anti node count: " + antiNode.size());
  }

  public static void main(String[] args) {
    List<List<Character>> input = getInput("input.txt");
    part1(input);
    part2(input);
  }
}
