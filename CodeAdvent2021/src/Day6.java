/*
Day 6: Lanternfish

https://adventofcode.com/2021/day/6
*/

import java.io.*;

public class Day6 {
    public static void main(String[] args) throws IOException {
        File file = new File("src/Resources/inputDay6");
        FileInputStream fis = new FileInputStream(file);
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));

        String aLine = null;

        // Part 1

        /*
        while ((aLine = in.readLine()) != null) {
            final int DAYS = 80;

            for (int i = 0; i < DAYS; i++) {
                int newFishCount = 0;
                String [] tokens = aLine.split(",");
                String lanternFish = "";

                for (String token : tokens) {
                    int timer = Integer.parseInt(token);
                    if (timer > 0) {
                        timer -= 1;
                    } else {
                        timer = 6;
                        newFishCount++;
                    }
                    if (lanternFish.equals("")) {
                        lanternFish = lanternFish.concat(String.valueOf(timer));
                    } else {
                        lanternFish = lanternFish.concat("," + timer);
                    }
                }
                for (int j = 0; j < newFishCount; j++) {
                    lanternFish = lanternFish.concat(",8");
                }
                aLine = lanternFish;
            }
            String [] totalFish = aLine.split(",");
            System.out.println(totalFish.length);
        }
        */

        // Part 2

        while ((aLine = in.readLine()) != null) {
            final int DAYS = 256;

            long [] currentTimers = new long [9];
            String [] tokens = aLine.split(",");

            for (String token : tokens) {
                int timer = Integer.parseInt(token);
                currentTimers[timer]++;
            }

            for (int i = 1; i <= DAYS; i++) {
                long [] nextTimers = new long [9];
                long newFishCount = 0;
                for (int j = 0; j < currentTimers.length; j++) {
                    if (j != 0) {
                        nextTimers[j-1] += currentTimers[j];
                    } else {
                        nextTimers[6] += currentTimers[0];
                        newFishCount = currentTimers[0];
                    }
                }

                nextTimers[8] = newFishCount;
                long totalFish = 0;

                for (long t : nextTimers) {
                    // System.out.print(t + " ");
                    totalFish += t;
                }

                if (i == DAYS) {
                    System.out.println(totalFish);
                }

                currentTimers = nextTimers;
            }
        }
    }
}
