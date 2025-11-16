package Laba4;

import java.io.*;

public class Task2 {

    public static void openCloseException(){

    }

    public static void main(String[] Args){
        String path = "C:\\Users\\Владимир\\IdeaProjects\\javaLaba1\\src\\Laba4\\fileForText.txt";
        InputStream in = null;

        // Обработка открытия\закрытия файла - Вариант 1

        try {
            in = new FileInputStream(path);
            in.close();
        } catch (FileNotFoundException e){
            System.err.println("Файл по пути " + path + " не найден. \n Ошибка: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка (в данном случае) при закрытии файла: " + e.getMessage());
        }

        // Обработка чтения и записи - Вариант 2

        String writePath = "C:\\Users\\Владимир\\IdeaProjects\\javaLaba1\\src\\Laba4\\fileForWrite.txt";
        String text = "Hello world!";

        try(FileOutputStream output = new FileOutputStream(writePath)){
            byte[] buffer = text.getBytes();

            output.write(buffer, 0, buffer.length);
        } catch (FileNotFoundException e) {
            System.err.println("Файл по пути " + writePath + " не найден. \n Ошибка: " + e.getMessage());

        } catch (IOException e) {
            System.err.println("Произошла ошибка во время чтения или записи: " + e.getMessage());
        }

        String readPath = "C:\\Users\\Владимир\\IdeaProjects\\javaLaba1\\src\\Laba4\\fileForText.txt";
        try( FileInputStream input = new FileInputStream(readPath)){
            int i;
            while((i = input.read()) != -1){
                System.out.print((char) i);
            }
        } catch(FileNotFoundException e){
            System.err.println("Файл по пути " + readPath + " не найден. \n Ошибка: " + e.getMessage());
        } catch(IOException e){
            System.err.println("Произошла ошибка во время чтения или записи: " + e.getMessage());
        }
    }
}
