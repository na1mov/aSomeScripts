package EasyTasks;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/*
Пример использования функциональных интерфейсов(содержат в себе ровно один метод) Consumer<T> и Predicate<T>
Пример:
@FunctionalInterface
public interface Consumer<T> {
    void accept(T value);
}
*/

public class FunctionInterfacesEx1 {
    static class FilteredSaver {
        private List<String> saved = new ArrayList<>();
        private List<Predicate<String>> filters = new ArrayList<>();
        private Consumer<String> onSaveListener;

        public void setOnSaveListener(Consumer<String> onSaveListener) {
            this.onSaveListener = onSaveListener;
        }

        public void addFilter(Predicate<String> filter) {
            filters.add(filter);
        }

        public void save(String line) {
            for (Predicate<String> filter : filters) {
                if (!filter.test(line)) {
                    return;
                }
            }
            if (onSaveListener != null) {
                onSaveListener.accept(line);
            }
            saved.add(line);
        }

        public List<String> getSaved() {
            return saved;
        }
    }

    public static class AnonTest {


        public static void main(String[] args) {
            FilteredSaver saver = new FilteredSaver();
            saver.setOnSaveListener(new Consumer<String>() {
                @Override
                public void accept(String line) {
                    System.out.println("СОХРАНЕНО: " + line);
                }
            });
            saver.addFilter(new Predicate<String>() {
                @Override
                public boolean test(String line) {
                    return line.contains("ВАЖНО");
                }
            });
            saver.addFilter(new Predicate<String>() {
                @Override
                public boolean test(String line) {
                    return line.endsWith("!");
                }
            });

            saver.save("Привет!");  // не сохранится
            saver.save("ВАЖНО - это важное слово"); // не сохранится
            saver.save("ВАЖНО! Не забывай его использовать для заметок!"); // сохранится и выведется на экран
            saver.save("Но и память тренировать тоже очень ВАЖНО!");    // сохранится и выведется на экран
            System.out.println(saver.getSaved());   // список из двух сохранённых сообщений
        }
    }
}
/*
Ещё примеры:
Supplier<T>
Интерфейс Supplier<T> (англ. «поставщик») содержит метод get(), который ничего не принимает, но возвращает значение
типа T. Такой интерфейс может использоваться в конструкторе без параметров. Так, в качестве поставщика значений типа
double может служить метод получения случайных дробных чисел Math.random():

import java.util.function.Supplier;

public class AnonTest {
    public static void main(String[] args) {
        Supplier<Double> randomDoubleSupplier = new Supplier<Double>() {
            @Override
            public Double get() {
                return Math.random(); // случайное число от 0 до 1
            }
        };

				Double supplied = randomDoubleSupplier.get();
        System.out.println(supplied);
    }
}
======
Function<T,R>
Интерфейс содержит метод apply(), который принимает объект типа T и возвращает объект типа R. Такой интерфейс
пригодится, например, для парсинга числа из строки.

import java.util.function.Function;

public class AnonTest {
    public static void main(String[] args) {
        Function<String, Integer> nameToLengthFunction = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };

        String name = "Марк";
        System.out.println("Сколько букв в имени " + name + "?");
        System.out.println("Ответ: " + nameToLengthFunction.apply(name));
    }
}
*/
