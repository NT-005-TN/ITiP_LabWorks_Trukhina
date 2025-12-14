package Laba5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {



    public static void main(String[] args) {
        String testLine = "Some characters.. -12, -12.3, 3234, 23, 43, 234.34, 122222.0000, 122.";
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        // можно добавить \\b как границу слова по краям, чтобы находить отдельно стоящие числа

        Matcher matcher = pattern.matcher(testLine);
        while(matcher.find()){
            System.out.println(matcher.group());
        }
    }
}
