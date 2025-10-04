package Laba2;

public class Run {
    public static void main(String[] args) {
        SocialNetwork sn = new SocialNetwork("VK", "Социальная сеть", 10, "test@example.com", "password123", "user123");
        Game game = new Game("Minecraft", "Игра", 1, "Sandbox", true, 3);
        WeatherApp weather = new WeatherApp("AccuWeather", "Погода", 5, "Moscow", 18.5, true);

        System.out.println("=== Социальная сеть ===");
        sn.setLogin("newUser");
        sn.setEmail("new@example.com");
        sn.setPassword("newPassword");
        System.out.println("Логин: " + sn.getLogin());
        System.out.println("Email: " + sn.getEmail());
        System.out.println("Пароль: " + sn.getPassword());
        sn.LogInWithEmail();
        sn.AddFriend();
        sn.onStart();
        sn.onStop();
        sn.onDestroy();
        sn.displayInfo();

        System.out.println("\n=== Игра ===");
        game.setGenre("RPG");
        game.setMultiplayer(false);
        game.setCurrentLevel(10);
        System.out.println("Жанр: " + game.getGenre());
        System.out.println("Мультиплеер: " + game.isMultiplayer());
        System.out.println("Уровень: " + game.getCurrentLevel());
        game.startGame();
        game.saveProgress();
        game.onStart();
        game.onStop();
        game.onDestroy();
        game.displayInfo();

        System.out.println("\n=== Погодное приложение ===");
        weather.setLocation("London");
        weather.setTemperature(15.0);
        weather.setCelsius(false);
        System.out.println("Местоположение: " + weather.getLocation());
        System.out.println("Температура: " + weather.getTemperature());
        System.out.println("Цельсий: " + weather.isCelsius());
        weather.updateWeather();
        weather.showForecast();
        weather.onStart();
        weather.onStop();
        weather.onDestroy();
        weather.displayInfo();

        System.out.println("\n=== Счётчик ===");
        System.out.println("Создано погодных приложений: " + WeatherApp.getInstanceCount());
    }
}