package Laba2;

public class Game extends Application {

    private String genre;
    private boolean isMultiplayer;
    private int currentLevel;

    public Game() {
        super();
    }

    public Game(String name, String description, int version, String genre, boolean isMultiplayer, int currentLevel) {
        super(name, description, version);
        this.genre = genre;
        this.isMultiplayer = isMultiplayer;
        this.currentLevel = currentLevel;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isMultiplayer() {
        return isMultiplayer;
    }

    public void setMultiplayer(boolean multiplayer) {
        isMultiplayer = multiplayer;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void startGame() {
        System.out.println("Запуск игры: " + getName());
        System.out.println("Жанр: " + genre + ", уровень: " + currentLevel);
    }

    public void saveProgress() {
        System.out.println("Прогресс сохранён. Текущий уровень: " + currentLevel);
    }

    @Override
    public void onStart() {
        System.out.println("Игра " + getName() + " запущена.");
    }

    @Override
    public void onStop() {
        System.out.println("Игра " + getName() + " приостановлена.");
    }

    @Override
    public void onDestroy() {
        System.out.println("Игра " + getName() + " завершена.");
    }

    public void displayInfo() {
        System.out.println("=== Информация об игре ===");
        System.out.println("Название: " + getName());
        System.out.println("Описание: " + getDescription());
        System.out.println("Версия: " + getVersion());
        System.out.println("Жанр: " + genre);
        System.out.println("Мультиплеер: " + (isMultiplayer ? "Да" : "Нет"));
        System.out.println("Текущий уровень: " + currentLevel);
        System.out.println("===============================");
    }
}