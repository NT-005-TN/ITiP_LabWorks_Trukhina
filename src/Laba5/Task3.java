package Laba5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {


    public static void main(String[] args) {
        String text = "Stroka anDsm asd D asndBBB NDm dK";
        Pattern pattern = Pattern.compile("([a-z])([A-Z])");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()){
            System.out.println(matcher.group());
        }

        String res = matcher.replaceAll("$1!$2");
        System.out.println(res);

    }
}
