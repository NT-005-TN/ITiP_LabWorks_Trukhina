package Laba4;

public class ArrayAverage {

    public static void main(String[] Args){
        int[] arr = {1,2,3,4,5};
        int sum = 0;
        int count = 0;
        try{
            int arrLength = 6;
            for(int i = 0; i < arrLength; i++){
                sum += arr[i];
                count += 1;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Ошибка: выход за границы массива — " + e.getMessage());
        } catch (NumberFormatException e){
            System.err.println("Ошибка: неверное значение в массиве — " + e.getMessage());
        } finally {
            if (count > 0){
                System.out.print("Среднее арифметическое без ошибочных значений: " + sum/count);
            } else {
                System.out.print("Нет значений, которые не вызывали бы ошибки");
            }
        }
    }
}
