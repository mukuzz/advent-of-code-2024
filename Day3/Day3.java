import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class Day3 {
  
  public static String readInput(String filePath) {
    String input = "";
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        input += line;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return input;
  }

  private static boolean isMul(AtomicInteger it, String input) {
    if (it.get() >= input.length() || input.charAt(it.getAndIncrement()) != 'm') return false;
    if (it.get() >= input.length() || input.charAt(it.getAndIncrement()) != 'u') return false;
    if (it.get() >= input.length() || input.charAt(it.getAndIncrement()) != 'l') return false;
    return true;
  }

  private static boolean isDo(int it, String input) {
    if (it >= input.length() || input.charAt(it++) != 'd') return false;
    if (it >= input.length() || input.charAt(it++) != 'o') return false;
    if (it >= input.length() || input.charAt(it++) != '(') return false;
    if (it >= input.length() || input.charAt(it++) != ')') return false;
    return true;
  }

  private static boolean isDont(int it, String input) {
    if (it >= input.length() || input.charAt(it++) != 'd') return false;
    if (it >= input.length() || input.charAt(it++) != 'o') return false;
    if (it >= input.length() || input.charAt(it++) != 'n') return false;
    if (it >= input.length() || input.charAt(it++) != '\'' ) return false;
    if (it >= input.length() || input.charAt(it++) != 't') return false;
    if (it >= input.length() || input.charAt(it++) != '(') return false;
    if (it >= input.length() || input.charAt(it++) != ')' ) return false;
    return true;
  }

  private static int getNum(AtomicInteger it, String input) {
    int num = 0;
    while (it.get() < input.length() && Character.isDigit(input.charAt(it.get()))) {
      num = num * 10 + (input.charAt(it.getAndIncrement()) - '0');
    }
    return num;
  }

  public static void part1(String input) {
    int len = input.length();
    AtomicInteger it = new AtomicInteger(0);
    int res = 0;
    while(it.get() < len) {
      if (!isMul(it, input)) continue;
      if (input.charAt(it.getAndIncrement()) != '(') continue;
      int num1 = getNum(it, input);
      if (num1 == 0) continue;
      if (input.charAt(it.getAndIncrement()) != ',') continue;
      int num2 = getNum(it, input);
      if (num2 == 0) continue;
      if (input.charAt(it.getAndIncrement()) != ')') continue;
      res += num1 * num2;
    }
    System.out.println("Answer: " + res);
  }

  public static void part2(String input) {
    int len = input.length();
    AtomicInteger it = new AtomicInteger(0);
    int res = 0;
    boolean enabled = true;
    while(it.get() < len) {
      if (isDo(it.get(), input)) enabled = true;
      if (isDont(it.get(), input)) enabled = false;
      if (!isMul(it, input)) continue;
      if (input.charAt(it.getAndIncrement()) != '(') continue;
      int num1 = getNum(it, input);
      if (num1 == 0) continue;
      if (input.charAt(it.getAndIncrement()) != ',') continue;
      int num2 = getNum(it, input);
      if (num2 == 0) continue;
      if (input.charAt(it.getAndIncrement()) != ')') continue;
      if (!enabled) continue;
      res += num1 * num2;
    }
    System.out.println("Answer: " + res);
  }

  public static void main(String []args) {
    String input = readInput("input.txt");
    part1(input);
    part2(input);
  }
}