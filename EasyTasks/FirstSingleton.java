package EasyTasks;

/*
Класс является синглтоном (реализует паттерн Singleton), если позволяет создать всего один объект своего типа.
*/

public class FirstSingleton {
    private static FirstSingleton instance;

    private FirstSingleton() {
    }

    public static FirstSingleton getInstance() {
        if (instance == null) {
            instance = new FirstSingleton();
        }
        return instance;
    }
}
