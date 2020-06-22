package FillWordApp.Model;


import java.util.ArrayList;
import java.util.HashSet;

import static FillWordApp.Model.Model.Direction.*;


public class Model {
    public HashSet<ArrayList<Pair>> wordsCoordinates = new HashSet<>();
    public HashSet<String> words = new HashSet<>();

    public enum Direction {
        TOP, RIGHT, BOTTOM, LEFT, NOWHERE
    }

    private static class WordLocation {
        Pair start;
        int straightLength;
        int turningLength;
        Direction straight;
        Direction turning;

        WordLocation(Pair start, int straightLength, int turningLength, Direction straight, Direction turning) {
            this.start = start;
            this.straightLength = straightLength;
            this.turningLength = turningLength;
            this.straight = straight;
            this.turning = turning;
        }
    }

    private final WordLocation[] table_6 = new WordLocation[]{
            new WordLocation(new Pair(2, 1), 2, 0, RIGHT, NOWHERE),
            new WordLocation(new Pair(3, 0), 2, 0, BOTTOM, NOWHERE),
            new WordLocation(new Pair(1, 4), 1, 1, BOTTOM, RIGHT),
            new WordLocation(new Pair(4, 3), 2, 1, RIGHT, TOP),
            new WordLocation(new Pair(2, 0), 1, 3, TOP, RIGHT),
            new WordLocation(new Pair(4, 2), 1, 3, BOTTOM, RIGHT),
            new WordLocation(new Pair(5, 1), 2, 3, TOP, RIGHT),
            new WordLocation(new Pair(1, 5), 1, 5, TOP, LEFT),
    };
    private final WordLocation[] table1_6 = new WordLocation[]{
            new WordLocation(new Pair(1, 2), 2, 0, RIGHT, NOWHERE),
            new WordLocation(new Pair(1, 1), 1, 3, BOTTOM, RIGHT),
            new WordLocation(new Pair(4, 5), 4, 0, LEFT, NOWHERE),
            new WordLocation(new Pair(4, 0), 1, 5, BOTTOM, RIGHT),
            new WordLocation(new Pair(3, 4), 4, 3, LEFT, TOP),
            new WordLocation(new Pair(3, 5), 3, 4, TOP, LEFT),
    };
    private final WordLocation[] table2_6 = new WordLocation[]{
            new WordLocation(new Pair(1, 4), 3, 0, BOTTOM, NOWHERE),
            new WordLocation(new Pair(4, 3), 3, 0, TOP, NOWHERE),
            new WordLocation(new Pair(4, 1), 1, 4, LEFT, TOP),
            new WordLocation(new Pair(5, 0), 2, 4, RIGHT, TOP),
            new WordLocation(new Pair(5, 3), 2, 4, RIGHT, TOP),
            new WordLocation(new Pair(3, 1), 3, 4, TOP, RIGHT),
    };
    private final WordLocation[] table_8 = new WordLocation[]{
            new WordLocation(new Pair(7, 5), 2, 0, TOP, NOWHERE),
            new WordLocation(new Pair(7, 2), 2, 1, LEFT, TOP),
            new WordLocation(new Pair(6, 1), 2, 1, RIGHT, BOTTOM),
            new WordLocation(new Pair(6, 6), 2, 1, TOP, LEFT),
            new WordLocation(new Pair(4, 0), 1, 3, BOTTOM, RIGHT),
            new WordLocation(new Pair(1, 6), 5, 0, LEFT, NOWHERE),
            new WordLocation(new Pair(7, 4), 3, 3, TOP, LEFT),
            new WordLocation(new Pair(2, 7), 5, 1, BOTTOM, LEFT),
            new WordLocation(new Pair(0, 1), 6, 1, RIGHT, BOTTOM),
            new WordLocation(new Pair(2, 5), 5, 2, LEFT, TOP),
            new WordLocation(new Pair(2, 6), 1, 6, BOTTOM, LEFT),
    };
    private final WordLocation[] table1_8 = new WordLocation[]{
            new WordLocation(new Pair(4, 5), 2, 0, BOTTOM, NOWHERE),
            new WordLocation(new Pair(6, 2), 2, 1, LEFT, BOTTOM),
            new WordLocation(new Pair(7, 7), 4, 0, TOP, NOWHERE),
            new WordLocation(new Pair(5, 2), 2, 3, TOP, RIGHT),
            new WordLocation(new Pair(4, 3), 3, 2, BOTTOM, LEFT),
            new WordLocation(new Pair(7, 6), 2, 3, LEFT, TOP),
            new WordLocation(new Pair(1, 1), 4, 1, BOTTOM, LEFT),
            new WordLocation(new Pair(2, 2), 1, 4, TOP, RIGHT),
            new WordLocation(new Pair(4, 0), 4, 2, TOP, RIGHT),
            new WordLocation(new Pair(2, 7), 2, 4, TOP, LEFT),
            new WordLocation(new Pair(2, 3), 3, 4, RIGHT, BOTTOM),
    };


    public char[][] fillModel(int size)  {
        ArrayList<WordLocation[]> options = new ArrayList<>();
        char[][] result = new char[size][size];
        int row, column;

        if (size == 6) {
            options.add(table_6);
            options.add(table1_6);
            options.add(table2_6);
        } else {
            options.add(table_8);
            options.add(table1_8);
        }

        WordLocation[] table = options.get((int) (Math.random() * options.size()));
        ArrayList<Integer> wordsLengths = new ArrayList<>();

        for (WordLocation location : table) wordsLengths.add(location.straightLength + location.turningLength + 1);
        ArrayList<String> wordsFromDB = WordsDB.getWords(wordsLengths);

        for (int i = 0; i < table.length; i++) {
            WordLocation location = table[i];
            String word = wordsFromDB.get(i);
            words.add(word);

            ArrayList<Pair> wordCoordinates = new ArrayList<>();


            row = location.start.row;
            column = location.start.column;
            result[row][column] = word.charAt(0);
            wordCoordinates.add(new Pair(row, column));


            int length = location.straightLength;
            Direction direction = location.straight;
            for (int j = 1; j < word.length(); j++) {
                if (length != 0) {
                    switch (direction) {
                        case TOP:
                            row--;
                            break;
                        case RIGHT:
                            column++;
                            break;
                        case BOTTOM:
                            row++;
                            break;
                        case LEFT:
                            column--;
                            break;
                    }
                    length--;
                    result[row][column] = word.charAt(j);
                    wordCoordinates.add(new Pair(row, column));
                } else {
                    length = location.turningLength;
                    direction = location.turning;
                    j--;
                }
            }
            wordsCoordinates.add(wordCoordinates);
        }
        return result;
    }
}