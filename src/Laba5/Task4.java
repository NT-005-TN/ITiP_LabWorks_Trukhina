package Laba5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task4 {

    public static void main(String[] args) {
        String[] testIPs = {
                "192.168.1.1",      // ✓ правильный
                "01.02.03.04",      // ✗ ведущие нули
                "255.255.255.255",  // ✓ максимальный
                "192.168.01.1",     // ✗ ведущий ноль
                "0.0.0.0",          // ✓ нулевой адрес
                "0.0.0.00",         // ✗ ведущие нули
                "10.0.0.1",         // ✓ правильный
                "256.0.0.1"         // ✗ >255
        };
        Pattern pattern = Pattern.compile(
                "^" +
                        "((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])\\.){3}" +
                        "(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])" +
                        "$"
        );


        for(String text: testIPs) {
            Matcher matcher = pattern.matcher(text);

            while (matcher.find()) {
                System.out.println(matcher.group());
            }
        }

    }
}
