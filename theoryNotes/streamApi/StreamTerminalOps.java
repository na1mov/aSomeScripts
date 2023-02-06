package theoryNotes.streamApi;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTerminalOps {
    public static void main(String[] args) {
        System.out.println("\ncount()");
        long count = IntStream.of(1, 2, 3, 4, 5, 6).count();
        System.out.println(count);

        System.out.println("\nfindFirst()");
        Optional<Integer> optInteger = IntStream.of(1, 2, 3, 4, 5, 6).boxed().findFirst();
        System.out.println(optInteger.orElse(null));

        System.out.println("\nfindAny()");
        Optional<Integer> optInteger1 = IntStream.of(1, 2, 3, 4, 5, 6).boxed().findFirst();
        System.out.println(optInteger1.orElse(null));

        System.out.println("\nanyMatch(), allMatch(), noneMatch()");
        System.out.println(IntStream.of(1, 2, 3, 4, 5, 6).boxed().anyMatch(i -> i == 2));
        System.out.println(IntStream.of(1, 2, 3, 4, 5, 6).boxed().allMatch(i -> i == 3));
        System.out.println(IntStream.of(1, 2, 3, 4, 5, 6).boxed().noneMatch(i -> i == 7));

        System.out.println("\nmin(), max()");
        Optional<String> optInteger2 = Stream.of("1", "2", "3", "4", "5", "6")
                .min(Comparator.comparingInt(Integer::parseInt));
        System.out.println(optInteger2.orElse(null));

        System.out.println("\nreduce()");
        int factorial = Stream.iterate(1, i -> i <= 5, i -> i + 1)
                .peek(i -> System.out.println("peek: " + i))
                .reduce(1, (accumulator, current) -> accumulator * current);
        System.out.println(factorial);

        System.out.println("\ncollect()");
        List<Integer> collectedList = Stream.of(1, 2, 3, 4, 5, 6).collect(Collectors.toList());
        System.out.println(collectedList);

        Set<Integer> collectedSet = Stream.of(1, 2, 3, 4, 5, 6).collect(Collectors.toSet());
        System.out.println(collectedSet);

        Map<String, Integer> integerByString = Stream.of("1", "2", "3", "4", "5", "6")
                .collect(Collectors.toMap(Function.identity(), Integer::parseInt));
        System.out.println(integerByString);

        Map<Boolean, List<Integer>> collectedMap2 = Stream.of(1, 2, 3, 4, 5, 6)
                .collect(Collectors.groupingBy(i -> i % 2 == 0));
        System.out.println(collectedMap2);

        System.out.println("\ntoArray()");
        String[] strs = Stream.of("1", "2", "3", "4", "5", "6").toArray(String[]::new);
        System.out.println( strs);

        System.out.println("\nsum(), average(), summaryStatistics()");
        int sum = Stream.of(1, 2, 3, 4, 5, 6)
                .mapToInt(Integer::intValue)
                .sum();
        OptionalDouble optDouble = Stream.of(1, 2, 3, 4, 5, 6)
                .mapToInt(Integer::intValue)
                .average();
        IntSummaryStatistics iss = Stream.of(1, 2, 3, 4, 5, 6)
                .mapToInt(Integer::intValue)
                .summaryStatistics();
        System.out.println(sum + " and " + optDouble + " and " + iss);
    }
}
