package Laba5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task5 {

    public static void main(String[] args) {
        String[] testIPs = {
                "word",
                "wrong",
                "kwork",
                "epofds",
                "well",
                "jshdjwaksjdklw",
                "Workdj krspk kwrok anad,,bnwkk wowooowowok"
        };

        String x = "W";
        Pattern pattern = Pattern.compile("\\b(?i)" + x + "[A-Za-z]*");
        //?i игнорируем регистр

        for(String text: testIPs) {
            Matcher matcher = pattern.matcher(text);

            while (matcher.find()) {
                System.out.println(matcher.group());
            }
        }

    }

}
