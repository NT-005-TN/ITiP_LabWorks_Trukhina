package Laba1;

public class Palindrome {

    public static boolean isPalindrome(String line){
        String reversedString = reverseString(line);
        return line.equals(reversedString);
    }

    public static String reverseString(String s){
        String newString = "";
        for(int i = s.length()-1; i >= 0; i--){
            newString = s.charAt(i) + newString;
        }
        return newString;
    }

    public static void main(String[] args){
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            System.out.println(isPalindrome(s));
        }
    }
}
