package Laba2;

public abstract class Application {

    private String name;
    private String description;
    private int version;

    public Application(String name, String description, int version) {
        this.name = name;
        this.description = description;
        this.version = version;
    }

    public Application(){
    }

    public abstract void onStart();
    public abstract void onStop();
    public abstract void onDestroy();

    public int getVersion(){
        return this.version;
    }

    public void setVersion(int version){
        this.version = version;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

