package theoryNotes.filesPath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class FilesClass {
    // Files — это утилитный класс, куда были вынесены статические методы из класса File.
    // Files — это примерно то же, что и Arrays или Collections
    public static void main(String[] args) throws IOException {

        // создание файла
        Path testFile1 = Files.createFile(Paths.get("C:\\Users\\Username\\Desktop\\testFile111.txt"));
        System.out.println("Был ли файл успешно создан?");
        System.out.println(Files.exists(Paths.get("C:\\Users\\Username\\Desktop\\testFile111.txt")));

        // создание директории
        Path testDirectory = Files.createDirectory(Paths.get("C:\\Users\\Username\\Desktop\\testDirectory"));
        System.out.println("Была ли директория успешно создана?");
        System.out.println(Files.exists(Paths.get("C:\\Users\\Username\\Desktop\\testDirectory")));

        // перемещаем файл с рабочего стола в директорию testDirectory. Перемещать надо с указанием имени файла в папке!
        testFile1 = Files.move(testFile1,
                Paths.get("C:\\Users\\Username\\Desktop\\testDirectory\\testFile111.txt"), REPLACE_EXISTING);

        System.out.println("Остался ли наш файл на рабочем столе?");
        System.out.println(Files.exists(Paths.get("C:\\Users\\Username\\Desktop\\testFile111.txt")));

        System.out.println("Был ли наш файл перемещен в testDirectory?");
        System.out.println(Files.exists(
                Paths.get("C:\\Users\\Username\\Desktop\\testDirectory\\testFile111.txt")));

        // удаление файла
        Files.delete(testFile1);
        System.out.println("Файл все еще существует?");
        System.out.println(Files.exists(
                Paths.get("C:\\Users\\Username\\Desktop\\testDirectory\\testFile111.txt")));

        System.out.println("=".repeat(30));
        // создание файла
        testFile1 = Files.createFile(Paths.get("C:\\Users\\Username\\Desktop\\testFile111.txt"));
        System.out.println("Был ли файл успешно создан?");
        System.out.println(Files.exists(Paths.get("C:\\Users\\Username\\Desktop\\testFile111.txt")));

        // создание директории
        Path testDirectory2 = Files.createDirectory(Paths.get("C:\\Users\\Username\\Desktop\\testDirectory2"));
        System.out.println("Была ли директория успешно создана?");
        System.out.println(Files.exists(Paths.get("C:\\Users\\Username\\Desktop\\testDirectory2")));

        // копируем файл с рабочего стола в директорию testDirectory2.
        testFile1 = Files.copy(testFile1,
                Paths.get("C:\\Users\\Username\\Desktop\\testDirectory2\\testFile111.txt"), REPLACE_EXISTING);

        System.out.println("Остался ли наш файл на рабочем столе?");
        System.out.println(Files.exists(Paths.get("C:\\Users\\Username\\Desktop\\testFile111.txt")));

        System.out.println("Был ли наш файл скопирован в testDirectory?");
        System.out.println(Files.exists(
                Paths.get("C:\\Users\\Username\\Desktop\\testDirectory2\\testFile111.txt")));

        System.out.println("=".repeat(30));
        // весь файл, строку за строкой, можно, например, вывести в консоль в обычном цикле for
        List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Username\\Desktop\\pushkin.txt"), UTF_8);
        for (String s : lines) {
            System.out.println(s);
        }

        // найти в файле все строки, которые начинаются со слова «Как», привести их к UPPER CASE и вывести в консоль
        Stream<String> stream = Files.lines(Paths.get("C:\\Users\\Username\\Desktop\\pushkin.txt"));
        List<String> result = stream
                .filter(line -> line.startsWith("Как"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        result.forEach(System.out::println);
    }
}
