package theoryNotes.patterns;

/*
Класс является синглтоном (реализует паттерн Singleton), если позволяет создать всего один объект своего типа.3
Ленивая инициализация.
Потокобезопасность
Высокая производительность в многопоточной среде
Не поддерживается на версиях Java ниже 1.5 (в версии 1.5 исправили работу ключевого слова volatile)
*/

public class SingletonSynchronized {
    private static volatile SingletonSynchronized INSTANCE;

    private SingletonSynchronized() {
    }

    public static SingletonSynchronized getInstance() {
        if (INSTANCE == null) {
            synchronized (SingletonSynchronized.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SingletonSynchronized();
                }
            }
        }
        return INSTANCE;
    }
}

