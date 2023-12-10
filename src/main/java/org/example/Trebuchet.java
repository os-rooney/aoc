package org.example;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Trebuchet {
    public static String solveTrebuchtPart1(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            int sum = 0;
            for (String line: lines) {
                sum += getFirstDigit(line) * 10 + getLastDigit(line);
            }

            return String.valueOf(sum);
        } catch (Exception e) {
            e.printStackTrace();
            return String.valueOf(0);
        }

    }

    public static int getFirstDigit(String line) {
        for (char c: line.toCharArray()) {
            if (Character.isDigit(c)) {
                return Character.getNumericValue(c);
            }
        }

        return 0;
    }

    public static int getLastDigit(String line) {
        for (int i = line.length() - 1; i >= 0; i--) {
            char cAtI = line.charAt(i);
            if (Character.isDigit(cAtI)) {
                return Character.getNumericValue(cAtI);
            }
        }

        return 0;
    }
}
