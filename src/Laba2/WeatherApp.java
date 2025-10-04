package Laba2;

public class WeatherApp extends Application {

    private static int instanceCount = 0;

    private String location;
    private double temperature;
    private boolean isCelsius;

    public WeatherApp() {
        super();
        instanceCount++;
    }

    public WeatherApp(String name, String description, int version,
                      String location, double temperature, boolean isCelsius) {
        super(name, description, version);
        this.location = location;
        this.temperature = temperature;
        this.isCelsius = isCelsius;
        instanceCount++;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public boolean isCelsius() {
        return isCelsius;
    }

    public void setCelsius(boolean celsius) {
        isCelsius = celsius;
    }

    public void updateWeather() {
        System.out.println("Обновление погоды в "
                + location + ": "
                + temperature + "°"
                + (isCelsius ? "C" : "F"));
    }

    public void showForecast() {
        System.out.println("Прогноз: погода в "
                + location + " на сегодня — "
                + temperature + "°");
    }

    @Override
    public void onStart() {
        System.out.println("Погодное приложение " + getName() + " запущено.");
    }

    @Override
    public void onStop() {
        System.out.println("Погода " + getName() + " приостановлена.");
    }

    @Override
    public void onDestroy() {
        System.out.println("Погодное приложение " + getName() + " завершено.");
    }

    public void displayInfo() {
        System.out.println("=== Информация о погодном приложении ===");
        System.out.println("Название: " + getName());
        System.out.println("Описание: " + getDescription());
        System.out.println("Версия: " + getVersion());
        System.out.println("Местоположение: " + location);
        System.out.println("Температура: " + temperature + "°" + (isCelsius ? "C" : "F"));
        System.out.println("=========================================");
    }

    public static int getInstanceCount() {
        return instanceCount;
    }
}