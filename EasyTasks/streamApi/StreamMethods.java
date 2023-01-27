package EasyTasks.streamApi;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamMethods {
    public static void main(String[] args) {
        System.out.println("map()");
        Integer[] int1Array = {1, 2, 3, 4, 5};
        Arrays.stream(int1Array)
                .map(Object::toString) // переводим в Stream<String>
                .map(Integer::parseInt)  // переводим в Stream<Integer>
                .forEach(System.out::println);

        System.out.println("\nfilter()");
        Integer[] int2Array = {1, 2, 3, 4, 5};
        Arrays.stream(int2Array)
                .filter(str -> str > 3)
                .forEach(System.out::println);

        System.out.println("\nflatmap()");
        Integer[] int3Array = {1, 2, 3, 4, 5};
        Arrays.stream(int3Array)
                .map(Object::toString)
                .map(String::toCharArray)
                .map(charrr -> String.valueOf(charrr)
                        .chars()
                        .mapToObj(c -> (char)c)
                        .toArray(Character[]::new))
                .map(Arrays::asList)
                .flatMap(Collection::stream)
                .forEach(System.out::println);

        System.out.println("\npeek()");
        Stream.of(1, 1, 2, 2, 3, 3)
                .peek(i -> System.out.println("peek: "+i))  // вывод всех элементов в обработке
                .distinct() // отсев дублей
                .forEach(System.out::println);

        System.out.println("\nlimit() & sorted()");
        new Random().ints()
                .limit(10)
                .peek(i -> System.out.println("peek: "+i))
                .sorted()   // можно подставить свой компаратор
                .forEach(System.out::println);

        System.out.println("\nskip()");
        new Random().ints()
                .limit(10)
                .skip(5)
                .sorted()   // можно подставить свой компаратор
                .forEach(System.out::println);

        System.out.println("\ntakeWhile()");
        Integer[] int4Array = {1, 2, 3, 4, 5};
        Arrays.stream(int4Array)
                .takeWhile(str -> str < 3)
                .forEach(System.out::println);

        System.out.println("\ndropWhile()");
        Integer[] int5Array = {1, 2, 3, 4, 5};
        Arrays.stream(int5Array)
                .dropWhile(str -> str < 3)
                .forEach(System.out::println);

        System.out.println("\nboxed()");
        IntStream.of(1, 2, 3, 4, 5, 6)
                .boxed()
                .map(i -> (i % 2 == 0) ? null : i+1)
                .filter(i -> i != null)
                .forEach(System.out::println);
    }
}
