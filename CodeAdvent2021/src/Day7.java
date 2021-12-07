/*
Day 7: The Treachery of Whales

https://adventofcode.com/2021/day/7
*/

import java.io.*;
import java.util.Arrays;
import java.util.Collections;

public class Day7 {
    public static void main(String[] args) throws IOException {
        File file = new File("src/Resources/inputDay7");
        FileInputStream fis = new FileInputStream(file);
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));

        String aLine = null;
        String [] tokens = new String[0];

        // Part 1

        /*
        while ((aLine = in.readLine()) != null) {
            tokens = aLine.split(",");
        }

        int [] numTokens = new int[tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            numTokens[i] = Integer.parseInt(tokens[i]);
        }
        Arrays.sort(numTokens);

        int median = numTokens[numTokens.length/2];
        int fuel = 0;

        for (int i = 0; i < numTokens.length; i++) {
            fuel += Math.abs(numTokens[i] - median);
        }
        System.out.println(numTokens.length/2 + ": " + numTokens[numTokens.length/2]);
        System.out.println(fuel);
        */

        // Part 2

        while ((aLine = in.readLine()) != null) {
            tokens = aLine.split(",");
        }

        int [] numTokens = new int[tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            numTokens[i] = Integer.parseInt(tokens[i]);
        }

        int runningSum = 0;
        int fuel = 0;

        for (int i = 0; i < numTokens.length; i++) {
            runningSum += numTokens[i];
        }

        int mean = (int) Math.floor((float)runningSum / numTokens.length);

        for (int i = 0; i < numTokens.length; i++) {
            int distance = Math.abs(numTokens[i] - mean);
            int test = (distance*(distance+1))/2;
            //System.out.println(test);
            fuel += (distance*(distance+1))/2;
        }
        System.out.println(fuel);
    }
}
