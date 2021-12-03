import java.io.*;
import java.util.ArrayList;

public class Day3 {
    public static void main(String[] args) throws IOException {
        String input = File.separator + "C:\\Users\\willi\\IdeaProjects\\CodeAdvent2021\\src\\inputDay3";
        File file = new File(input);
        FileInputStream fis = new FileInputStream(file);
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));

        String aLine = null;

        /*
        // Part 1

        int lineCount = 0;
        int [] tokenCounter = new int[0];
        boolean initialized = false;

        while ((aLine = in.readLine()) != null) {
            if (!initialized) {
                tokenCounter = new int[aLine.length()];
                initialized = true;
            }
            char [] tokens = aLine.toCharArray();
            for (int i = 0; i < tokens.length; i++) {
                if (tokens[i] == '1') {
                    tokenCounter[i]++;
                }
            }
            lineCount++;
        }

        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();

        for (int i = 0; i < tokenCounter.length; i++) {
            if (tokenCounter[i] > lineCount / 2) {
                gamma.append("1");
                epsilon.append("0");
            } else {
                gamma.append("0");
                epsilon.append("1");
            }
        }

        System.out.println(
            "Gamma: " + Integer.parseInt(gamma.toString(),2) +
            "\nEpsilon: " + Integer.parseInt(epsilon.toString(),2) +
            "\nPower Consumption: " + (Integer.parseInt(gamma.toString(),2) * Integer.parseInt(epsilon.toString(),2))
        );
        */

        // Part 2

        ArrayList<String> inputs = new ArrayList<>();
        boolean initialized = false;
        int inputLength = 0;

        while ((aLine = in.readLine()) != null) {
            if (!initialized) {
                inputLength = aLine.length();
                initialized = true;
            }
            inputs.add(aLine);
        }

        String [] inputArray = inputs.toArray(new String[0]);
        int oneCount;
        int zeroCount;
        int totalCount;
        StringBuilder O2 = new StringBuilder();
        String CO2 = "";

        // O2 Rating

        for (int j = 0; j < inputLength; j++) {
            oneCount = 0;
            zeroCount = 0;
            totalCount = 0;

            for (int i = 0; i < inputArray.length; i++) {
                if (inputArray[i].charAt(j) == '1') {
                    oneCount++;
                } else if (inputArray[i].charAt(j) == '0') {
                    zeroCount++;
                }
                totalCount++;
            }

            char keepChar;
            int maxCount = Math.max(oneCount, zeroCount);
            String[] tempArray = new String[maxCount];
            int tempIndex = 0;

            if (oneCount > zeroCount) {
                O2.append("1");
                keepChar = '1';
            } else if (oneCount < zeroCount) {
                O2.append("0");
                keepChar = '0';
            } else {
                O2.append("1");
                keepChar = '1';
            }

            for (int i = 0; i < inputArray.length; i++) {
                if (inputArray[i].charAt(j) == keepChar) {
                    tempArray[tempIndex] = inputArray[i];
                    tempIndex++;
                }
            }
            inputArray = tempArray;
        }

        // CO2 Rating

        inputArray = inputs.toArray(new String[0]);

        for (int j = 0; j < inputLength; j++) {
            oneCount = 0;
            zeroCount = 0;
            totalCount = 0;

            for (int i = 0; i < inputArray.length; i++) {
                if (inputArray[i].charAt(j) == '1') {
                    oneCount++;
                } else if (inputArray[i].charAt(j) == '0') {
                    zeroCount++;
                }
                totalCount++;
            }

            System.out.println(oneCount + " " + zeroCount);

            char keepChar;
            int minCount = Math.min(oneCount, zeroCount);
            String[] tempArray = new String[minCount];
            int tempIndex = 0;

            if (totalCount == 1) {
                CO2 = inputArray[0];
                break;
            } else if (oneCount > zeroCount) {
                CO2 += "0";
                keepChar = '0';
            } else if (oneCount < zeroCount) {
                CO2 += "1";
                keepChar = '1';
            } else {
                CO2 += "0";
                keepChar = '0';
            }

            for (int i = 0; i < inputArray.length; i++) {
                if (inputArray[i].charAt(j) == keepChar) {
                    tempArray[tempIndex] = inputArray[i];
                    tempIndex++;
                }
            }
            inputArray = tempArray;
        }
        
        System.out.println(
                "Oxygen Generator Rating: " + Integer.parseInt(O2.toString(),2) +
                "\nCO2 Scrubber Rating: " + Integer.parseInt(CO2,2) +
                "\nLife Support Rating: " + (Integer.parseInt(O2.toString(),2) * Integer.parseInt(CO2,2))
        );

        in.close();
    }
}
