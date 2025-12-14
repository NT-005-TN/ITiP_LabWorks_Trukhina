package Laba6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Task1 {

    public static void main(String[] args) {
        String path = "C:\\Users\\Владимир\\IdeaProjects\\javaLaba1\\src\\Laba6\\file.txt";
        File file = new File(path);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        Map <String, Integer> wordCount = new HashMap<>();
        while(sc.hasNext()){
            String word = sc.next();
            word = word.toLowerCase()
                    .replaceAll("[^A-Za-z]", "")
                    .trim();
            if(word.isEmpty())
                continue;

            wordCount.put(word, wordCount.getOrDefault(word, 0)+1);
        }
        sc.close();

        //wordCount.entrySet() возвращает Set<Map.Entry<String, Integer>>
        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordCount.entrySet());
        list.sort((a,b) -> b.getValue() - a.getValue());

        System.out.println("Топ-10 самых часто встречающихся слов:");
        int count = Math.min(10, list.size());
        for (int i = 0; i < count; i++) {
            Map.Entry<String, Integer> entry = list.get(i);
            System.out.println((i + 1) + ". " + entry.getKey() + " - " + entry.getValue());
        }
    }
}
