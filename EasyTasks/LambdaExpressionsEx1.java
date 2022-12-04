package EasyTasks;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/*
Лямбда-выражение (англ. lambda expression) — это специальный инструмент языка Java, который позволяет быстро
и компактно имплементировать функциональный интерфейс. Часто — буквально одной строкой.

Таким образом, лямбда-выражение состоит из двух частей, разделённых оператором '->':
(список входных параметров) -> { блок реализации функционального интерфейса};

Правила синтаксиса лямбда-выражений:

-- У лямбды может быть 00 и более входных параметров.
-- Параметры заключаются в круглые скобки и разделяются запятыми. Например, (String country, int size).
-- Если Java может получить тип параметра из контекста, его можно не указывать. Например, (country, size).
-- Если параметр один и его тип явно не указывается, скобки можно не писать. Например, эта лямбда вычисляет, за
сколько часов мы проедем указанную дистанцию при скорости 100 км/ч: distance -> return distance / 100.
-- Если параметров нет, нужно поставить пустые круглые скобки. Например, следующая лямбда возвращает информацию об
открытости магазина: () -> "магазин закрыт".
-- Тело лямбды может состоять из 00 и более выражений.
-- Если тело состоит из одного оператора, его можно не заключать в фигурные скобки. Возвращаемое значение в этом
случае можно указать без ключевого слова return.
-- Во всех остальных случаях фигурные скобки обязательны, а в конце блока кода необходимо указать возвращаемое
значение с использованием ключевого слова return (иначе типом возвращаемого значения будет void).

Например, лямбдами можно реализовывать только функциональные интерфейсы, то есть те, которые содержат ровно один метод.
Если же абстрактных методов в интерфейсе много, лямбда с такой задачей не справится. В анонимных классах можно
объявлять поля и использовать их в реализуемых методах, в то время как в лямбдах полей быть не может.
*/

public class LambdaExpressionsEx1 {
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
            saver.setOnSaveListener(line -> System.out.println("СОХРАНЕНО: " + line));
            saver.addFilter(line -> line.contains("ВАЖНО"));
            saver.addFilter(line -> line.endsWith("!"));

            saver.save("Привет!");  // не сохранится
            saver.save("ВАЖНО - это важное слово"); // не сохранится
            saver.save("ВАЖНО! Не забывай его использовать для заметок!"); // сохранится и выведется на экран
            saver.save("Но и память тренировать тоже очень ВАЖНО!");    // сохранится и выведется на экран
            System.out.println(saver.getSaved());   // список из двух сохранённых сообщений
        }
    }
}
