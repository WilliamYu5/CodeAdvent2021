import java.io.*;

public class Day4 {
    public static void main(String[] args) throws IOException {
        File file = new File("src/inputDay4");
        FileInputStream fis = new FileInputStream(file);
        BufferedReader in = new BufferedReader(new InputStreamReader(fis));

        String aLine = null;

        // Part 1

        String [] drawNumbers = new String[0];
        int drawCount;
        int minDrawCount = 100;
        int maxDrawCount = 0;
        int maxScore = 0;
        int minScore = 0;
        String [][] currentBoard = new String[5][5];

        if ((aLine = in.readLine()) != null) {
            drawNumbers = aLine.split(",");
        }

        // Do every bingo board one at a time.
        while ((aLine = in.readLine()) != null) {
            // Set up a bingo board (2D array)
            for (int i = 0; i < 5; i++) {
                aLine = in.readLine();
                String [] boardNumbers = aLine.trim().split(" +");
                currentBoard[i] = boardNumbers;
            }

            // Call bingo numbers starting at the 5th until the current board wins.
            for (int i = 4; i < drawNumbers.length; i++) {
                drawCount = i;
                String drawNumber = drawNumbers[i];
                boolean match = false;
                boolean winner = false;
                int matchRow = 0;
                int matchCol = 0;

                // A called number is found on the bingo board.
                for (int row = 0; row < 5; row++) {
                    for (int col = 0; col < 5; col++) {
                        if (currentBoard[row][col].equals(drawNumber)) {
                            match = true;
                            matchRow = row;
                            matchCol = col;
                        }
                    }
                }

                // Check for a bingo after every found bingo number.
                if (match) {
                    boolean winnerC = false;
                    boolean winnerR = false;

                    // Check the column.
                    boolean [] found = new boolean[5];
                    for (int row = 0; row < 5; row++) {
                        for (int j = 0; j <= drawCount; j++) {
                            if (currentBoard[row][matchCol].equals(drawNumbers[j])) {
                                found[row] = true;
                                break;
                            }
                        }
                        if (found[0] && found[1] && found[2] && found[3] && found[4]) {
                            winnerC = true;
                        }
                    }

                    // Check the row.
                    found = new boolean[5];
                    for (int col = 0; col < 5; col++) {
                        for (int j = 0; j <= drawCount; j++) {
                            if (currentBoard[matchRow][col].equals(drawNumbers[j])) {
                                found[col] = true;
                                break;
                            }
                        }
                        if (found[0] && found[1] && found[2] && found[3] && found[4]) {
                            winnerR = true;
                        }
                    }
                    if (winnerC || winnerR) {
                        winner = true;
                    }
                }

                // In the scenario where the called number led to bingo:
                // 1. Check whether it is the earliest bingo so far.
                // 2. Calculate and record the score if so.
                if (match && winner) {
                    boolean newMin = false;

                    if (drawCount < minDrawCount) {
                        minDrawCount = drawCount;
                        newMin = true;
                    }

                    if (newMin) {
                        int unmarkedSum = 0;
                        for (int row = 0; row < 5; row++) {
                            for (int col = 0; col < 5; col++) {
                                boolean marked = false;
                                for (int j = 0; j <= drawCount; j++) {
                                    if (drawNumbers[j].equals(currentBoard[row][col])) {
                                        marked = true;
                                        break;
                                    }
                                }
                                if (!marked) {
                                    unmarkedSum += Integer.parseInt(currentBoard[row][col]);
                                }
                            }
                        }
                        minScore = unmarkedSum * Integer.parseInt(drawNumbers[drawCount]);
                    }

                    boolean newMax = false;

                    if (drawCount > maxDrawCount) {
                        maxDrawCount = drawCount;
                        newMax = true;
                    }

                    if (newMax) {
                        int unmarkedSum = 0;
                        for (int row = 0; row < 5; row++) {
                            for (int col = 0; col < 5; col++) {
                                boolean marked = false;
                                for (int j = 0; j <= drawCount; j++) {
                                    if (drawNumbers[j].equals(currentBoard[row][col])) {
                                        marked = true;
                                        break;
                                    }
                                }
                                if (!marked) {
                                    unmarkedSum += Integer.parseInt(currentBoard[row][col]);
                                }
                            }
                        }
                        maxScore = unmarkedSum * Integer.parseInt(drawNumbers[drawCount]);
                    }
                    break;
                }
            }
        }
        System.out.println("Fastest Win Score: " + minScore);
        System.out.println("Slowest Win Score: " + maxScore);
        in.close();
    }
}
