package FillWordApp.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class WordsDB {

    public static ArrayList<String> getWords(List<Integer> wordsLengths) {
        char sep = File.separatorChar;
        ArrayList<String> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(
                        "src" + sep + "main" + sep + "resources" + sep + "wordsDataBase.txt"), UTF_8))) {
            String line = reader.readLine();
            for (int i = 0; i < wordsLengths.get(0) - 3; i++) line = reader.readLine();//отматываем сразу до нужной линии

            String[] wordsOfDefiniteLength = line.split(", ");
            for (int i = 0; i < wordsLengths.size(); i++) {
                if (i != 0) {
                    for (int j = 0; j < wordsLengths.get(i) - wordsLengths.get(i - 1); j++) line = reader.readLine();
                    wordsOfDefiniteLength = line.split(", ");
                }
                int size = wordsOfDefiniteLength.length;
                String word = wordsOfDefiniteLength[(int) (size * Math.random())];

                while (result.contains(word)) word = wordsOfDefiniteLength[(int) (size * Math.random())];
                result.add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
