package Laba5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 {

    public static void main(String[] args) {
        String[] passwords = {
                "Password123",      // правильный
                "short1A",          // слишком короткий (7 символов)
                "nouppercase123",   // нет заглавной буквы
                "!NODIGITSAAA1",      // спец символ
                "GoodPass123!",     // содержит спецсимвол !
                "12345678",         // нет букв
                "ABCDEFGH1",        // правильный
                "A1B2C3D4E5",       // правильный
                "a".repeat(20) + "A1", // слишком длинный (22 символа)
        };
        Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,16}$");
        for(String password: passwords){
            Matcher matcher = pattern.matcher(password);
            while(matcher.find()){
                System.out.println(matcher.group());
            }
        }
    }
}







// ^ - смотрим с начала строки, как бы указатель что мы находимся в начале строки - после каждой проверки мы возвращаемся в начало строки, но на [A-Za-z\\d] мы уже действительно идем по строке
// ?= - просмотор вперед. .* - любые символы.
// ?= смотрит вперед любых символов .* на наличие заглавной буквы [A-Z]
