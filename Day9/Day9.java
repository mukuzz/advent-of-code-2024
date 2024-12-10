package Day9;

import java.util.*;
import java.io.*;

public class Day9 {

  private static class FreeSpace {
    int start;
    int len;

    public FreeSpace(int start, int len) {
      this.start = start;
      this.len = len;
    }
  }

  private static List<Integer> readInput(String filename) {
    List<Integer> numbers = new ArrayList<>();
    try {
      File file = new File(filename);
      Scanner scanner = new Scanner(file);
      boolean zeros = false;
      int id = 0;
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        for (char ch : line.toCharArray()) {
          int num = Character.getNumericValue(ch);
          for (int i = 0; i < num; i++) {
            if (zeros) {
              numbers.add(null);
            } else {
              numbers.add(id);
            }
          }
          if (!zeros) {
            id++;
          }
          zeros = !zeros;
        }
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return numbers;
  }

  private static List<FreeSpace> calculateFreeSpace(List<Integer> input) {
    int l = 0, r = input.size() - 1;
    List<FreeSpace> freeSpaces = new ArrayList<>();
    while (l <= r) {
      if (input.get(l) == null) {
        int start = l;
        while (l <= r && input.get(l) == null) {
          l++;
        }
        int len = l - start;
        freeSpaces.add(new FreeSpace(start, len));
      } else {
        l++;
      }
    }
    return freeSpaces;
  }

  private static void part2(List<Integer> input) {
    int r = input.size() - 1;
    List<FreeSpace> freeSpaces = calculateFreeSpace(input);
    while (r >= 0) {
      if (input.get(r) == null) {
        r--;
      } else {
        int fileId = input.get(r);
        int fileSize = 0;
        int fileEnd = r;
        while (r >= 0 && input.get(r) != null && input.get(r) == fileId) {
          fileSize++;
          r--;
        }
        for (FreeSpace freeSpace : freeSpaces) {
          if (freeSpace.start <= r && freeSpace.len >= fileSize) {
            for (; fileEnd > r; fileEnd--) {
              input.set(fileEnd, null);
            }
            for (int i = freeSpace.start; i < freeSpace.start + fileSize; i++) {
              input.set(i, fileId);
            }
            freeSpace.start += fileSize;
            freeSpace.len -= fileSize;
            if (freeSpace.len == 0) {
              freeSpaces.remove(freeSpace);
            }
            break;
          }
        }
      }
    }
    long checkSum = 0;
    for (int i = 0; i < input.size(); i++) {
      if (input.get(i) != null) {
        checkSum += input.get(i) * i;
      }
    }
    System.out.println("Checksum: " + checkSum);
  }

  private static void part1(List<Integer> input) {
    int l = 0, r = input.size() - 1;
    while (l < r) {
      if (input.get(l) != null) {
        l++;
      } else if (input.get(r) == null) {
        r--;
      } else {
        Integer temp = input.get(l);
        input.set(l, input.get(r));
        input.set(r, temp);
        l++;
        r--;
      }
    }
    long checkSum = 0;
    for (int i = 0; i < input.size(); i++) {
      if (input.get(i) != null) {
        checkSum += input.get(i) * i;
      }
    }
    System.out.println("Checksum: " + checkSum);
  }

  public static void main(String[] args) {
    List<Integer> input = readInput("Day9/input.txt");
    part1(input);
    input = readInput("Day9/input.txt");
    part2(input);
  }
}
