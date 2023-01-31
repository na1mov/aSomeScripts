package EasyTasks;

import java.util.*;

public class CollectionModifier {
    public static void main(String[] args) {
        // Iterable.forEach(Consumer<? super T> action)
        // Перебирает переданную коллекцию, и выполняет лямбда-выражение action для каждого ее элемента.
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7));
        numbers.forEach(s -> System.out.print(s + " "));
        System.out.println();
        System.out.println("=".repeat(30));

        // Collection.removeIf(Predicate<? super E> filter)
        // Метод перебирает коллекцию, и удаляет те элементы, которые соответствуют filter.
        List <Integer> numbers2 = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7));
        numbers2.removeIf(s -> s > 5);
        numbers2.forEach(s -> System.out.print(s + " "));
        System.out.println();
        System.out.println("=".repeat(30));

        // Map.forEach(BiConsumer<? super K, ? super V> action)
        // Метод forEach работает не только для классов, реализующих интерфейс Collection, но и для Map.
        Map<String, String> books = new HashMap<>();
        books.put("Война и мир", "Лев Толстой");
        books.put("Преступление и наказание", "Федор Достоевский");
        books.put("Философия Java", "Брюс Эккель");
        books.put("Братья Карамазовы", "Федор Достоевский");
        books.put("Властелин Колец", "Джон Толкин");
        books.forEach((a,b) -> System.out.println("Название книги: " + a + ". Автор: " + b));
        System.out.println("=".repeat(30));

        // Map.compute(K key,BiFunction<? super K, ? super V, ? extends V> remappingFunction)
        // Для ключа key этот метод устанавливает в качестве value результат выполнения функции remappingFunction.
        Map <String, String> books2 = new HashMap<>();
        books2.put("Война и мир", "Лев Толстой");
        books2.put("Преступление и наказание", "Федор Достоевский");
        books2.put("Философия Java", "Брюс Эккель");
        books2.put("Братья Карамазовы", "Федор Достоевский");
        books2.put("Властелин Колец", "Джон Толкин");
        books2.forEach((a,b) -> System.out.println("Название книги: " + a + ". Автор: " + b));

        books2.compute("Философия Java", (a,b) -> b+", крутой чувак");
        System.out.println("_".repeat(20));
        books2.forEach((a,b) -> System.out.println("Название книги: " + a + ". Автор: " + b));
        System.out.println("=".repeat(30));

        // Map.computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction)
        // Метод добавит новый элемент в Map, но только в том случае, если элемент с таким ключом там отсутствует.
        // В качестве value ему будет присвоен результат выполнения функции mappingFunction.
        // Если же элемент с таким ключом уже есть — он не будет перезаписан, а останется на месте.
        Map <String, String> books3 = new HashMap<>();
        books3.put("Война и мир", "Лев Толстой");
        books3.put("Преступление и наказание", "Федор Достоевский");
        books3.put("Философия Java", "Брюс Эккель");
        books3.put("Братья Карамазовы", "Федор Достоевский");
        books3.put("Властелин Колец", "Джон Толкин");

        books3.computeIfAbsent("Гарри Поттер и узник Азкабана", b -> getHarryPotterAuthor());
        books3.forEach((a,b) -> System.out.println("Название книги: " + a + ". Автор: " + b));

        // Map.computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)
        // Тот же принцип, что и у Map.compute(), но все вычисления будут выполнены только в случае,
        // если элемент с ключом key уже существует.
        Map <String, String> books4 = new HashMap<>();
        books4.put("Война и мир", "Лев Толстой");
        books4.put("Преступление и наказание", "Федор Достоевский");
        books4.put("Философия Java", "Брюс Эккель");
        books4.put("Братья Карамазовы", "Федор Достоевский");
        books4.put("Властелин Колец", "Джон Толкин");

        books4.computeIfPresent("Евгений Онегин", (a,b) -> b="Александр Пушкин");
        System.out.println("_".repeat(20));
        books4.forEach((a,b) -> System.out.println("Название книги: " + a + ". Автор: " + b));
        books4.computeIfPresent("Братья Карамазовы", (a,b) -> b="Александр Пушкин");
        System.out.println("_".repeat(20));
        books4.forEach((a,b) -> System.out.println("Название книги: " + a + ". Автор: " + b));
        System.out.println("=".repeat(30));

        // Map.getOrDefault(Object key, V defaultValue)
        // Возвращает значение, соответствующее ключу key.
        // Если такой ключ не существует — возвращает значение по умолчанию.
        Map <String, String> books5 = new HashMap<>();
        books5.put("Война и мир", "Лев Толстой");
        books5.put("Преступление и наказание", "Федор Достоевский");
        books5.put("Философия Java", "Брюс Эккель");
        books5.put("Братья Карамазовы", "Федор Достоевский");
        books5.put("Властелин Колец", "Джон Толкин");

        String igor = books5.getOrDefault("Слово о полку Игореве", "Неизвестный автор");
        System.out.println(igor);
        System.out.println("=".repeat(30));

        // Map.merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction)
        // - Если в вашей Map ключ key не существует, или value для этого ключа равно null —
        // метод добавляет в Map переданную пару key-value.
        // - Если ключ Key существует и его value != null —
        // метод меняет его value на результат выполнения переданной функции remappingFunction.
        // - Если remappingFunction возвращает null - key удаляется из коллекции.
        Map <String, String> books6 = new HashMap<>();
        books6.put("Война и мир", "Лев Толстой");
        books6.put("Преступление и наказание", "Федор Достоевский");
        books6.put("Философия Java", "Брюс Эккель");
        books6.put("Братья Карамазовы", "Федор Достоевский");
        books6.put("Властелин Колец", "Джон Толкин");

        books6.merge("Философия Java", "Брюс Эккель", (a, b) -> b +  " и кто-то там еще");
        books6.forEach((a,b) -> System.out.println("Название:" + a + ". Автор: " + b));
        System.out.println("=".repeat(30));

        // Map.putIfAbsent(K key, V value)
        // Добавить пару в Map, если ее там нет
        Map<String, String> map = new HashMap<>();
        map.putIfAbsent("Властелин Колец", "Джон Толкин");
        System.out.println(map);
        System.out.println("=".repeat(30));

        // Map.replace и Map.replaceAll()
        // Map.replace(K key, V newValue — заменяет значение ключа key на newValue, если такой ключ существует.
        // Если нет — ничего не происходит.
        // Map.replace(K key, V oldValue, V newValue) — делает то же самое,
        // но только если текущее значение key равно oldValue.
        // Map.replaceAll(BiFunction<? super K, ? super V, ? extends V> function) —
        // заменяет все значения value на результат выполнения функции function.
    }

    public static String getHarryPotterAuthor() {
        return "Джоан Роулинг";
    }
}
