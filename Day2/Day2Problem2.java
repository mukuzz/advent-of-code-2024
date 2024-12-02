import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class Day2Problem2 {

  public static List<List<Integer>> readReports(String filePath) {
    List<List<Integer>> reports = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        List<Integer> report = new ArrayList<>();
        String[] values = line.split("\\s+");
        for (String val: values) {
            report.add(Integer.parseInt(val));
        }
        reports.add(report);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return reports;
  }

  private static boolean isDiffWithinRange(int diff) {
    diff = Math.abs(diff);
    return diff >= 1 && diff <= 3;
  }

  private static boolean allIncreasingWithinRange(List<Integer> report) {
    int prev = report.get(0);
    for (int i = 1; i < report.size(); i++) {
        int cur = report.get(i);
        int diff = cur - prev;
        if (diff <= 0 ) return false;
        if (!isDiffWithinRange(diff)) {
            return false;
        }
        prev = cur;
    }
    return true;
  }

  private static boolean allDecreasingWithinRange(List<Integer> report) {
    int prev = report.get(0);
    for (int i = 1; i < report.size(); i++) {
        int cur = report.get(i);
        int diff = cur - prev;
        if (diff >= 0 ) return false;
        if (!isDiffWithinRange(diff)) {
            return false;
        }
        prev = cur;
    }
    return true;
  }

  public static void main(String []args) {
    List<List<Integer>> reports = readReports("Input.txt");

    int safeCount = 0;
    
    for (List<Integer> report : reports) {
      if (allIncreasingWithinRange(report) || allDecreasingWithinRange(report)) {
        safeCount++;
      } else {
        for (int i = 0; i < report.size(); i++) {
          List<Integer> modifiedReport = new ArrayList<>(report);
          modifiedReport.remove(i);
          if (allIncreasingWithinRange(modifiedReport) || allDecreasingWithinRange(modifiedReport)){
            safeCount++;
            break;
          }
        }
      }
    }

    System.out.println("Answer: " + safeCount);
  }
}
