
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day4 {

    private static List<List<Character>> getInput(String filePath) {
        List<List<Character>> input = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Character> list = new ArrayList<>();
                for (char ch: line.toCharArray()) {
                    list.add(ch);
                }
                input.add(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    private static Map<String, Integer> dx = new HashMap<>();
    static {
        dx.put("LEFT", -1);
        dx.put("RIGHT", 1);
        dx.put("UP", 0);
        dx.put("DOWN", 0);
        dx.put("UPLEFT", -1);
        dx.put("UPRIGHT", 1);
        dx.put("DOWNRIGHT", 1);
        dx.put("DOWNLEFT", -1);
    }
    private static Map<String, Integer> dy = new HashMap<>();
    static {
        dy.put("LEFT", 0);
        dy.put("RIGHT", 0);
        dy.put("UP", -1);
        dy.put("DOWN", 1);
        dy.put("UPLEFT", -1);
        dy.put("UPRIGHT", -1);
        dy.put("DOWNRIGHT", 1);
        dy.put("DOWNLEFT", 1);
    }

    private static boolean checkWord(List<List<Character>> input, String direction, 
            int x, int y, int R, int C, String wordToCheck) {
        String word = "";
        while(x >= 0 && y >= 0 && x < C && y < R) {
            if (word.length() < wordToCheck.length()) {
                word += input.get(y).get(x);
            } else {
                break;
            }
            x += dx.get(direction);
            y += dy.get(direction);
        }
        return word.equals(wordToCheck);
    }

    private static boolean checkX_MAS(List<List<Character>> input, int x, int y, int R, int C) {
        Character ch = input.get(y).get(x);
        if (ch != 'A') return false;
        if ((checkWord(input, "DOWNRIGHT", x - 1, y - 1, R, C, "MAS") 
                || checkWord(input, "UPLEFT", x + 1, y + 1, R, C, "MAS"))
                && (checkWord(input, "DOWNLEFT", x + 1, y - 1, R, C, "MAS") 
                || checkWord(input, "UPRIGHT", x - 1, y + 1, R, C, "MAS"))) {
            return true;
        }
        return false;
    }

    private static void part1(List<List<Character>> input) {
        int count = 0;
        int R = input.size();
        int C = input.get(0).size();
        for (int x = 0; x < C; x++) {
            for (int y = 0; y < R; y++) {
                for (String dir: dx.keySet()) {
                    if (checkWord(input, dir, x, y, R, C, "XMAS")) count++;
                }
            }
        }
        System.out.println("Answer: " + count);
    }

    private static void part2(List<List<Character>> input) {
        int count = 0;
        int R = input.size();
        int C = input.get(0).size();
        for (int x = 0; x < C; x++) {
            for (int y = 0; y < R; y++) {
                if (checkX_MAS(input, x, y, R, C)) count++;
            }
        }
        System.out.println("Answer: " + count);
    }

    public static void main(String args[]) {
        List<List<Character>> input = getInput("input.txt");
        part1(input);
        part2(input);
    }
}