package EasyTasks;

import java.util.Comparator;
import java.util.function.Consumer;

public class FromAnonToLambda {
    public class AnonTest {

        public static void main(String[] args) {
            Consumer<String> greeter = new Consumer<>() {
                @Override
                public void accept(String name) {
                    System.out.println("Привет, " + name + "!");
                }
            };

            greeter.accept("Дарья");
        }
    }
    // заменяем анонимный класс на лямбда выражение
    public class AnonTest1 {
        public static void main(String[] args) {
            Consumer<String> greeter = (String name) -> {
                System.out.println("Привет, " + name + "!");
            };

            greeter.accept("Дарья");
        }
    }
    // убираем лишнее
    public class AnonTest2 {
        public static void main(String[] args) {
            Consumer<String> consumer = name -> System.out.println("Привет, " + name + "!");
            consumer.accept("Дарья");
        }
    }
    // ещё пример
    public class AnonTest3 {
        public static void main(String[] args) {
            Comparator<String> comparator = (s1, s2) -> s1.length() - s2.length();
            System.out.println(comparator.compare("Привет", "Дарья!"));
        }
    }
}
