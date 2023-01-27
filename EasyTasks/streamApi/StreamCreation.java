package EasyTasks.streamApi;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class StreamCreation {
    public static void main(String[] args) {
        System.out.println("Создание стримов:");
        System.out.println("\ncollection.stream()");
        Collection<Integer> integerCollection = List.of(-2, -1, 0, 1, 2);
        integerCollection.stream()
                .forEach(System.out::println);

        System.out.println("\nArrays.stream()");
        Integer[] intArray = {1, 2, 3, 4, 5};
        Arrays.stream(intArray)
                .forEach(System.out::println);

        Stream<Integer> numbers = Stream.empty();

        numbers = Stream.of(1, 2, 3);
        numbers.forEach(System.out::println);

        System.out.println("\nofNullable()");
        Integer n = null;

        System.out.println("ofNullable: ");
        Stream.ofNullable(n)
                .forEach(System.out::println);
        System.out.println("Stream.of(): ");
        Stream.of(n)
                .forEach(System.out::println);

        System.out.println("\ngenerate()");
        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("\niterate()");
        Stream.iterate(0, v -> v + 1)   // infinite
                .limit(10)
                .forEach(System.out::println);

        System.out.println("\niterate()");
        Stream.iterate(0, v -> v < 5, v -> v + 1)   // with condition
                .forEach(System.out::println);

        System.out.println("\nRandom.ints()");
        Random rnd = new Random();
        rnd.ints()  // double, longs
                .limit(10)
                .forEach(System.out::println);

        System.out.println("\nString.chars()");
        String str = "Hello world!";
        str.chars()
                .mapToObj(value -> (char) value)
                .forEach(System.out::println);
    }
}
