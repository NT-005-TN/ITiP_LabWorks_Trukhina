package Laba6;

import java.util.Arrays;

public class Stack<T> {
    private T[] data;
    private int size;
    private int capacity;

    public Stack(int capacity){
        this.capacity = capacity;
        this.data = (T[]) new Object[capacity];
        this.size = 0;
    }

    public void push(T element){
        if(size == capacity)
            resize(capacity * 2);
        data[size] = element;
        size++;
    }

    public T pop() {
        if (isEmpty())
            throw new IllegalStateException("Стек пуст");
        size--;
        T element = data[size];
        data[size] = null;
        return element;
    }

    public T peek(){
        if(isEmpty())
            throw new IllegalStateException("Стек пуст");
        return data[size-1];
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    private void resize(int newCapacity){
        T[] newData = (T[]) new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
        capacity = newCapacity;
    }

    public void clear(){
        for(int i = 0; i < size; i++)
            data[i] = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>(3);

        System.out.println("=== Базовое тестирование стека ===");

        // Добавление элементов
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("После push 1,2,3: " + stack);

        // Проверка peek
        System.out.println("Peek: " + stack.peek());

        // Удаление элементов
        System.out.println("Pop: " + stack.pop());
        System.out.println("После pop: " + stack);

        // Добавление еще
        stack.push(4);
        System.out.println("После push 4: " + stack);

        // Удаление всех элементов
        System.out.println("Pop: " + stack.pop());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Pop: " + stack.pop());

        // Проверка пустого стека
        System.out.println("Стек пуст? " + stack.isEmpty());
    }
}
