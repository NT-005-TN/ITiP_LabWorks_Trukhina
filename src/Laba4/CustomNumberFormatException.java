package Laba4;

/**
 * Throwable
 *  ├── Error                → серьёзные системные ошибки (нельзя исправить)
 *  └── Exception            → ошибки, связанные с логикой программы (можно и нужно обрабатывать)
 *       ├── RuntimeException → непроверяемые исключения (например, NullPointerException)
 *       └── остальные        → проверяемые исключения (например, IOException)
 *
 * Только объекты классов, унаследованных от Throwable, можно использовать с throw, catch, throws.
 */

public class CustomNumberFormatException extends Exception {

    public CustomNumberFormatException(String invalidInput, Throwable cause) {
        super("Невозможно преобразовать строку '" + invalidInput + "' в целое число.", cause);
    }

    public static int parseStringToInt(String str) throws CustomNumberFormatException {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new CustomNumberFormatException(str, e);
        }
    }

    public static void main(String[] args) {
        String line = "123456";
        String number = "bacdef";

        try {
            int res = parseStringToInt(line);
            System.out.println("Итог: " + res);
        } catch (CustomNumberFormatException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        try {
            int res = parseStringToInt(number);
            System.out.println("Итог: " + res);
        } catch (CustomNumberFormatException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}