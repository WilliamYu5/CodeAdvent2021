/*
Day 8: Seven Segment Search

https://adventofcode.com/2021/day/7
*/

import java.io.*;
import java.util.Arrays;

public class Day8 {
    public static void main(String[] args) throws IOException {
        File file = new File("src/Resources/inputDay8");
        FileInputStream fis = new FileInputStream(file);
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));

        String aLine = null;
        int easyDigits = 0;
        int runningSum = 0;

        while ((aLine = in.readLine()) != null) {
            String [] patternsValues = aLine.split("\\|");
            String [] patterns = patternsValues[0].split(" ");
            String [] values = patternsValues[1].split(" ");
            String [] valueKey = new String [10];
            char signalA = 0;
            char signalB = 0;
            char signalC = 0;
            char signalD = 0;
            char signalE = 0;
            char signalF = 0;
            char signalG = 0;

            String [] length5Patterns = new String [3];
            int length5Count = 0;
            String [] length6Patterns = new String [3];
            int length6Count = 0;

            // Part 1

            /*
            for (int i = 0; i < values.length; i++) {
                if (values[i].length() == 2 || values[i].length() == 3 || values[i].length() == 4 || values[i].length() == 7) {
                    easyDigits++;
                }
            }
            */

            // Part 2

            for (int i = 0; i < patterns.length; i++) {
                if (patterns[i].length() == 2) {
                    valueKey[1] = patterns[i];
                } else if (patterns[i].length() == 3) {
                    valueKey[7] = patterns[i];
                } else if (patterns[i].length() == 4) {
                    valueKey[4] = patterns[i];
                } else if (patterns[i].length() == 7) {
                    valueKey[8] = patterns[i];
                } else if (patterns[i].length() == 5) {
                    length5Patterns[length5Count] = patterns[i];
                    length5Count++;
                } else if (patterns[i].length() == 6) {
                    length6Patterns[length6Count] = patterns[i];
                    length6Count++;
                }
            }

            // Signal A: The segment that 1 and 7 do not share.
            for (int i = 0; i < valueKey[7].length(); i++) {
                if (valueKey[1].indexOf(valueKey[7].charAt(i)) == -1) {
                    signalA = valueKey[7].charAt(i);
                }
            }

            // Signal D: The common segment between "4" and the 5-length segments.
            String sharedChars4 = valueKey[4];
            for (String length5Pattern : length5Patterns) {
                StringBuilder tempSharedChars4 = new StringBuilder();
                for (int i = 0; i < sharedChars4.length(); i++) {
                    if (length5Pattern.indexOf(sharedChars4.charAt(i)) > -1) {
                        tempSharedChars4.append(sharedChars4.charAt(i));
                    }
                }
                sharedChars4 = tempSharedChars4.toString();
            }
            if (sharedChars4.length() == 1) {
                signalD = sharedChars4.charAt(0);
            }

            // Signal F: The common segment between "1" and the 6-length segments.
            String sharedChars1 = valueKey[1];
            for (String length6Pattern : length6Patterns) {
                StringBuilder tempSharedChars1 = new StringBuilder();
                for (int i = 0; i < sharedChars1.length(); i++) {
                    if (length6Pattern.indexOf(sharedChars1.charAt(i)) > -1) {
                        tempSharedChars1.append(sharedChars1.charAt(i));
                    }
                }
                sharedChars1 = tempSharedChars1.toString();
            }
            if (sharedChars1.length() == 1) {
                signalF = sharedChars1.charAt(0);
            }

            // Signal C: The other segment from "1" after resolving Signal F.
            for (int i = 0; i < valueKey[1].length(); i++) {
                if (valueKey[1].charAt(i) != signalF) {
                    signalC = valueKey[1].charAt(i);
                }
            }

            // Signal B: The last segment from "4" after resolving Signal C, D, F.
            for (int i = 0; i < valueKey[4].length(); i++) {
                if (valueKey[4].charAt(i) != signalC &&
                        valueKey[4].charAt(i) != signalD &&
                        valueKey[4].charAt(i) != signalF) {
                    signalB = valueKey[4].charAt(i);
                }
            }

            // Signal G: The last segment from "9" after resolving Signal A, B, C, D, F.
            // First, find the 9 pattern.
            for (String length6Pattern : length6Patterns) {
                if (length6Pattern.indexOf(signalD) == -1) {
                    valueKey[0] = length6Pattern;
                } else if (length6Pattern.indexOf(signalC) == -1) {
                    valueKey[6] = length6Pattern;
                } else {
                    valueKey[9] = length6Pattern;
                }
            }

            for (int i = 0; i < valueKey[9].length(); i++) {
                if (valueKey[9].charAt(i) != signalA &&
                        valueKey[9].charAt(i) != signalB &&
                        valueKey[9].charAt(i) != signalC &&
                        valueKey[9].charAt(i) != signalD &&
                        valueKey[9].charAt(i) != signalF) {
                    signalG = valueKey[9].charAt(i);
                }
            }

            // Signal E: The last segment after resolving all other Signals.
            for (int i = 0; i < valueKey[8].length(); i++) {
                if (valueKey[8].charAt(i) != signalA &&
                        valueKey[8].charAt(i) != signalB &&
                        valueKey[8].charAt(i) != signalC &&
                        valueKey[8].charAt(i) != signalD &&
                        valueKey[8].charAt(i) != signalF &&
                        valueKey[8].charAt(i) != signalG) {
                    signalE = valueKey[8].charAt(i);
                }
            }

            for (String length5Pattern : length5Patterns) {
                if (length5Pattern.indexOf(signalC) == -1) {
                    valueKey[5] = length5Pattern;
                } else if (length5Pattern.indexOf(signalE) == -1) {
                    valueKey[3] = length5Pattern;
                } else {
                    valueKey[2] = length5Pattern;
                }
            }

            // Sort valueKeys alphabetically
            for (int i = 0; i < valueKey.length; i++) {
                String [] valueLetters = valueKey[i].split("");
                Arrays.sort(valueLetters);
                StringBuilder sortedValueKey = new StringBuilder();
                for (String letter : valueLetters) {
                    //System.out.print(letter);
                    sortedValueKey.append(letter);
                }
                valueKey[i] = sortedValueKey.toString();
                //System.out.println(valueKey[i]);
            }

            // Sort input values alphabetically
            for (int i = 0; i < values.length; i++) {
                String [] valueLetters = values[i].split("");
                Arrays.sort(valueLetters);
                StringBuilder sortedValues = new StringBuilder();
                for (String letter : valueLetters) {
                    //System.out.print(letter);
                    sortedValues.append(letter);
                }
                values[i] = sortedValues.toString();
                //System.out.println(values[i]);
            }

            StringBuilder output = new StringBuilder();
            for (String value : values) {
                for (int i = 0; i < valueKey.length; i++) {
                    if (value.equals(valueKey[i])) {
                        output.append(i);
                        break;
                    }
                }
            }

            int intOutput = Integer.parseInt(output.toString());
            runningSum += intOutput;


            /*
            System.out.println("Signal A: " + signalA +
                    "\nSignal B: " + signalB +
                    "\nSignal C: " + signalC +
                    "\nSignal D: " + signalD +
                    "\nSignal E: " + signalE +
                    "\nSignal F: " + signalF +
                    "\nSignal G: " + signalG);

            */
        }
        System.out.println(runningSum);
    }
}
