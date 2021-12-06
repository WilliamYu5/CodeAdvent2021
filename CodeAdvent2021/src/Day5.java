/*
Day 5: Hydrothermal Venture

https://adventofcode.com/2021/day/5
*/

import java.io.*;

public class Day5 {
    public static void main(String[] args) throws IOException {
        File file = new File("src/Resources/inputDay5");
        FileInputStream fis = new FileInputStream(file);
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));

        String aLine = null;
        final int FIELD_SIZE = 1000;
        int overlapCount = 0;
        int [][] field = new int [FIELD_SIZE][FIELD_SIZE];

        while ((aLine = in.readLine()) != null) {
            String [] tokens = aLine.split("[ ,\\->]+");

            // Part 1

            if (tokens[0].equals(tokens[2]) || tokens[1].equals(tokens[3])) {
                int minX = Math.min(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[2]));
                int maxX = Math.max(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[2]));
                int minY = Math.min(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[3]));
                int maxY = Math.max(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[3]));

                for (int x = minX; x <= maxX; x++) {
                    for (int y = minY; y <= maxY; y++) {
                        field[x][y]++;
                    }
                }

            // Part 2

            } else {
                // 1 if left to right, -1 if right to left
                int xDirection = Integer.parseInt(tokens[0]) - Integer.parseInt(tokens[2]) < 0 ? 1 : -1;
                // 1 if downward, -1 if upward
                int yDirection = Integer.parseInt(tokens[1]) - Integer.parseInt(tokens[3]) < 0 ? 1 : -1;

                int minX = Math.min(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[2]));
                int maxX = Math.max(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[2]));
                int minY = Math.min(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[3]));
                int maxY = Math.max(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[3]));

                if (xDirection * yDirection > 0) {
                    int y = minY;
                    for (int x = minX; x <= maxX; x++) {
                        field[x][y]++;
                        y++;
                    }
                } else {
                    int y = maxY;
                    for (int x = minX; x <= maxX; x++) {
                        field[x][y]++;
                        y--;
                    }
                }
            }
        }

        for (int x = 0; x < FIELD_SIZE; x++) {
            for (int y = 0; y < FIELD_SIZE; y++) {
                if (field[x][y] > 1) {
                    overlapCount++;
                }
            }
        }

        System.out.println("Overlapping vents: " + overlapCount);

        in.close();
    }
}
