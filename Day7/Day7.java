import java.util.*;
import java.io.*;

public class Day7 {

  private static boolean isCalibrated(long res, long[] nums, int pos, long soln) {
    if (pos == nums.length) {
      return res == soln;
    }
    long stitch = Long.parseLong(String.valueOf(soln) + nums[pos]);
    return isCalibrated(res, nums, pos + 1, soln + nums[pos]) ||
        isCalibrated(res, nums, pos + 1, soln * nums[pos]) || isCalibrated(res, nums, pos + 1, stitch);
  }

  public static void main(String[] args) {
    try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
      String line;
      long calibratedSum = 0;
      while ((line = br.readLine()) != null) {
        String[] inputs = line.split("\\:");
        System.out.println("Inputs: " + inputs[0]);
        long res = Long.parseLong(inputs[0]);
        String[] numsStrArr = inputs[1].trim().split(" ");
        long[] nums = new long[numsStrArr.length];
        for (int i = 0; i < numsStrArr.length; i++) {
          nums[i] = Long.parseLong(numsStrArr[i]);
        }
        if (isCalibrated(res, nums, 1, nums[0])) {
          calibratedSum += res;
        }
      }
      System.out.println("Calibrated sum: " + calibratedSum);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
