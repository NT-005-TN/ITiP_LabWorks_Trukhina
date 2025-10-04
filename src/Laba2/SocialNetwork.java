package Laba2;

import java.util.Scanner;

public class SocialNetwork extends Application {

    private String login;
    private String password;
    private String email;

    public SocialNetwork(String name, String description, int version,
                         String email, String password, String login){
        super(name, description, version);
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public SocialNetwork(){
        super();
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public String getLogin(){
        return this.login;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void LogInWithEmail(){
        System.out.println("[ " + getName() + "]" +
                "пользователь " + getLogin() +
                " вошел с почтой " + getEmail());
    }

    public void AddFriend(){
        System.out.println("Пользователь " + getLogin() + "добавил друга");
    }

    @Override
    public void onStart() {
        System.out.println("Соцсеть " + getName() +  "запущена");
        if (!login.isEmpty() && (login != null)){
            System.out.println("Пользователь " + login + " выполнил вход");
        } else {
            System.out.println("Пользователь " + login + " не выполнял вход");
        }
    }

    @Override
    public void onStop() {
        System.out.println("Соцсеть приостановлена");
    }

    @Override
    public void onDestroy() {
        System.out.println("Приложение " + getName() + " заверашет работу");
    }

    public void displayInfo() {
        System.out.println("=== Информация о приложении ===");
        System.out.println("Название: " + getName());
        System.out.println("Описание: " + getDescription());
        System.out.println("Версия: " + getVersion());
        System.out.println("Логин: " + login);
        System.out.println("Email: " + email);
        System.out.println("===============================");
    }

}
