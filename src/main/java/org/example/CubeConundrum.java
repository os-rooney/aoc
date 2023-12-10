package org.example;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CubeConundrum {
    private static final Logger logger = Logger.getLogger(CubeConundrum.class.getName());
    private static final int MAX_RED = 12;
    private static final int MAX_GREEN = 13;
    private static final int MAX_BLUE = 14;

    public static void solveCubeConundrum(String filePath) {

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            part1(lines);
            part2(lines);

        } catch (Exception e) {
            logger.severe("An error occurred while processing the file: " + e.getMessage());
        }
    }

    private static void part1(List<String> input) {
        List<Game> games = input.stream().map(CubeConundrum::parse).toList();

        int sum = games.stream()
                .filter(game -> game.turns.stream()
                        .allMatch(turn -> turn.red <= MAX_RED && turn.green <= MAX_GREEN && turn.blue <= MAX_BLUE))
                .mapToInt(game -> game.id)
                .sum();

        System.out.println("part1: " + sum);
    }

    private static void part2(List<String> input) {
        List<Game> games = input.stream().map(CubeConundrum::parse).toList();

        int power = 0;

        for (Game game : games) {
            power += game.turns.stream().map(turn -> turn.red).reduce(Math::max).orElse(0)
                    * game.turns.stream().map(turn -> turn.green).reduce(Math::max).orElse(0)
                    * game.turns.stream().map(turn -> turn.blue).reduce(Math::max).orElse(0);

        }

        System.out.println("part2: " + power);
    }

    static Game parse(String in) {
        Game game = new Game();
        String[] parts = in.split(":");
        game.id = Integer.parseInt((parts[0].split(" "))[1]);
        String[] turnStrings = parts[1].split(";");

        for (String turnString : turnStrings) {
            Turn turn = new Turn();
            game.turns.add(turn);

            String[] cubes = turnString.split(",");

            for (String cube : cubes) {
                String[] cubeData = cube.trim().split(" ");
                int number = Integer.parseInt(cubeData[0]);
                String color = cubeData[1].trim();
                switch (color) {
                    case "red" -> turn.red = number;
                    case "blue" -> turn.blue = number;
                    case "green" -> turn.green = number;
                }
            }
        }
        return game;
    }

    static class Game {
        int id;
        List<Turn> turns = new ArrayList<>();
    }

    static class Turn {
        int red;
        int green;
        int blue;
    }
}