package EasyTasks.lambda;

import java.util.Comparator;
import java.util.function.Consumer;

public class LambdaFromAnon {
    public static class AnonTest {

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
    public static class AnonTest1 {
        public static void main(String[] args) {
            Consumer<String> greeter = (String name) -> {
                System.out.println("Привет, " + name + "!");
            };

            greeter.accept("Дарья");
        }
    }
    // убираем лишнее
    public static class AnonTest2 {
        public static void main(String[] args) {
            Consumer<String> consumer = name -> System.out.println("Привет, " + name + "!");
            consumer.accept("Дарья");
        }
    }
    // ещё пример
    public static class AnonTest3 {
        public static void main(String[] args) {
            Comparator<String> comparator = (s1, s2) -> s1.length() - s2.length();
            System.out.println(comparator.compare("Привет", "Дарья!"));
        }
    }
}
