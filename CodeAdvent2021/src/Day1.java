/*
Day 1: Sonar Sweep

https://adventofcode.com/2021/day/1
*/

import java.io.*;

public class Day1 {
    public static void main(String[] args) throws IOException {
        File file = new File("src/inputDay1");
        FileInputStream fis = new FileInputStream(file);
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));

        /*
        // Part 1

        String aLine = null;
        int previous = -1;
        int current;
        int count = 0;

        while ((aLine = in.readLine()) != null) {
            int aNum = Integer.parseInt(aLine);
            if (previous == -1) {
                previous = aNum;
            } else {
                current = aNum;
                if (current > previous) {
                    count++;
                }
                previous = current;
            }
            System.out.println(aLine);
        }
        System.out.println(count);
        */

        // Part 2

        String aLine = null;
        int first = -1;
        int second = -1;
        int third;
        int previousSum = -1;
        int currentSum;
        int count = 0;

        while ((aLine = in.readLine()) != null) {
            int aNum = Integer.parseInt(aLine);
            if (first == -1) {
                first = second;
                second = aNum;
            } else {
                third = aNum;
                currentSum = first + second + third;
                if (previousSum != -1) {
                    if (currentSum > previousSum) {
                        count++;
                    }
                }
                previousSum = currentSum;
                first = second;
                second = third;
            }
            System.out.println(aLine);
        }
        System.out.println("Increase count: " + count);
        in.close();
    }
}
