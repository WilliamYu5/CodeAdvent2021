/*
Day 2: Dive!

https://adventofcode.com/2021/day/2
*/

import java.io.*;

public class Day2 {
    public static void main(String[] args) throws IOException {
        String input = File.separator + "C:\\Users\\willi\\IdeaProjects\\CodeAdvent2021\\src\\inputDay2";
        File file = new File(input);
        FileInputStream fis = new FileInputStream(file);
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));

        String aLine = null;
        int horizontal = 0;
        int depth = 0;

        /*
        // Part 1

        while ((aLine = in.readLine()) != null) {
            String[] tokens = aLine.split(" ");
            String direction = tokens[0];
            int value = Integer.parseInt(tokens[1]);

            switch (direction) {
                case "forward":
                    horizontal += value;
                    break;
                case "up":
                    depth -= value;
                    break;
                case "down":
                    depth += value;
                    break;
            }
        }
        System.out.println("Horizontal: " + horizontal + "\nDepth: " + depth);
        */

        // Part 2

        int aim = 0;

        while ((aLine = in.readLine()) != null) {
            String[] tokens = aLine.split(" ");
            String direction = tokens[0];
            int value = Integer.parseInt(tokens[1]);

            switch (direction) {
                case "forward":
                    horizontal += value;
                    depth += aim * value;
                    break;
                case "up":
                    aim -= value;
                    break;
                case "down":
                    aim += value;
                    break;
            }
        }
        System.out.println("Horizontal: " + horizontal + "\nDepth: " + depth);
        in.close();
    }
}
