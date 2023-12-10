package org.example;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

public class Trebuchet {

    private static final Logger logger = Logger.getLogger(Trebuchet.class.getName());

    public static String[] digits = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    public static String solveTrebuchtPart1(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            int sum = 0;
            for (String line : lines) {
                sum += getFirstDigit(line) * 10 + getLastDigit(line);
            }

            return String.valueOf(sum);
        } catch (Exception e) {
            logger.severe("An error occurred while processing the file: " + e.getMessage());
            return String.valueOf(0);
        }

    }


    public static int turnWordToDigit(String num) {

        return switch (num) {
            case "one" -> 1;
            case "two" -> 2;
            case "three" -> 3;
            case "four" -> 4;
            case "five" -> 5;
            case "six" -> 6;
            case "seven" -> 7;
            case "eight" -> 8;
            case "nine" -> 9;
            default -> 0;
        };
    }

    public static int getFirstDigit(String line) {

        for (int i = 0; i < line.length(); i++) {
            if (isDigit(line, i)) {
                return Character.getNumericValue(line.charAt(i));
            }

            if (startsWithAnyPrefix(line) > 0) {
                return startsWithAnyPrefix(line);
            }

            for (String digit : digits) {
                String newLine = line.substring(i);

                if (newLine.startsWith(digit)) {
                    return turnWordToDigit(digit);
                }
            }
        }

        return 0;
    }

    public static int getLastDigit(String line) {

        for (int i = line.length() - 1; i >= 0; i--) {
            if (isDigit(line, i)) {
                return Character.getNumericValue(line.charAt(i));
            }

            if (endsWithAnyPrefix(line) > 0) {
                return endsWithAnyPrefix(line);
            }

            for (String digit : digits) {
                String newLine = line.substring(0, i);

                if (newLine.endsWith(digit)) {
                    return turnWordToDigit(digit);
                }
            }
        }

        return 0;
    }

    public static boolean isDigit(String word, int index) {
        return Character.isDigit(word.charAt(index));
    }

    public static int startsWithAnyPrefix(String str) {
        for (String digit : digits) {
            if (str.startsWith(digit)) {
                return turnWordToDigit(digit);
            }
        }
        return 0;
    }

    public static int endsWithAnyPrefix(String str) {
        for (String digit : digits) {
            if (str.endsWith(digit)) {
                return turnWordToDigit(digit);
            }
        }
        return 0;
    }
}
