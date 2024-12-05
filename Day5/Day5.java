import java.util.*;
import java.io.*;

public class Day5 {

  private static Map<Integer, Integer> getInOrders(Map<Integer, List<Integer>> adjList,
      List<Integer> ordering) {
    Map<Integer, Integer> inOrders = new HashMap<>();
    for (int node: adjList.keySet()) {
      if (!ordering.contains(node)) continue;
      for (int neighbor: adjList.get(node)) {
        if (!ordering.contains(neighbor)) continue;
        inOrders.put(neighbor, inOrders.getOrDefault(neighbor, 0) + 1);
      }
    }
    return inOrders;
  }

  private static boolean checkInorder(Map<Integer, List<Integer>> adjList, 
      Map<Integer, Integer> inOrders, List<Integer> ordering) {
    for (int node: ordering) {
      if (inOrders.getOrDefault(node, 0) != 0) {
        return false;
      }
      for (int neighbor: adjList.getOrDefault(node, new ArrayList<>())) {
        if (!ordering.contains(neighbor)) continue;
        inOrders.put(neighbor, inOrders.get(neighbor) - 1);
      }
    }
    return true;
  }
  
  private static List<Integer> getTopologicalOrder(Map<Integer, List<Integer>> adjList,
      List<Integer> ordering) {
    Map<Integer, Integer> inOrders = new HashMap<>();
    for (int node: adjList.keySet()) {
      if (!ordering.contains(node)) continue;
      for (int neighbor: adjList.get(node)) {
        if (!ordering.contains(neighbor)) continue;
        inOrders.put(neighbor, inOrders.getOrDefault(neighbor, 0) + 1);
      }
    }
    System.out.println("Inorders: " + inOrders);
    Queue<Integer> queue = new LinkedList<>();
    for (int node: adjList.keySet()) {
      if (!ordering.contains(node)) continue;
      if (inOrders.getOrDefault(node, 0) == 0) {
        queue.offer(node);
      }
    }

    List<Integer> topoOrder = new ArrayList<>();
    while(!queue.isEmpty()) {
      int node = queue.poll();
      topoOrder.add(node);
      for (int neighbor: adjList.getOrDefault(node, new ArrayList<>())) {
        if (!ordering.contains(neighbor)) continue;
        inOrders.put(neighbor, inOrders.get(neighbor) - 1);
        if (inOrders.get(neighbor) == 0) {
          queue.offer(neighbor);
        }
      }
    }

    for (int node: inOrders.keySet()) {
      if (inOrders.get(node) != 0) {
        System.out.println("Cycle detected");
      }
    }
    return topoOrder;
  }

  private static void solve(Map<Integer, List<Integer>> adjList, List<List<Integer>> orderings) {
    int ans1 = 0, ans2 = 0;
    for (List<Integer> ordering: orderings) {
      Map<Integer, Integer> inOrders = getInOrders(adjList, ordering);
      if (checkInorder(adjList, inOrders, ordering)) {
        int middleValue = ordering.get(ordering.size() / 2);
        ans1 += middleValue;
      } else {
        List<Integer> correctOrder = getTopologicalOrder(adjList, ordering);
        System.out.println("Correct order: " + correctOrder);
        ans2 += correctOrder.get(correctOrder.size() / 2);
      }
    }
    System.out.println("Answer1: " + ans1);
    System.out.println("Answer2: " + ans2);
  }

  public static void main(String args[]) {
    Map<Integer, List<Integer>> adjList = new HashMap<>();
    List<List<Integer>> orderings = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
      String line;
      while ((line = br.readLine()) != null) {
        if (line.isEmpty()) break;
        String[] values = line.split("\\|");
        int node1 = Integer.parseInt(values[0]);
        int node2 = Integer.parseInt(values[1]);
        adjList.putIfAbsent(node1, new ArrayList<>());
        adjList.get(node1).add(node2);
      }
      while ((line = br.readLine()) != null) {
        if (line.isEmpty()) break;
        String[] values = line.split(",");
        List<Integer> ordering = new ArrayList<>();
        for (String val: values) {
          ordering.add(Integer.parseInt(val));
        }
        orderings.add(ordering);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    solve(adjList, orderings);
  }
}