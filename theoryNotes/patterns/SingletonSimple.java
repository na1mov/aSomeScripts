package theoryNotes.patterns;

/*
Ленивая инициализация.
Не потокобезопасно
*/

public class SingletonSimple {
    private static SingletonSimple INSTANCE;

    private SingletonSimple() {}

    public static SingletonSimple getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SingletonSimple();
        }
        return INSTANCE;
    }
}
