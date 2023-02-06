package theoryNotes.filesPath;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class FindInFileTree {
    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Paths.get("C:\\Users\\Username\\Desktop\\testFolder"), new MyFileVisitor());
    }
}

class MyFileVisitor extends SimpleFileVisitor<Path> {

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        List<String> lines = Files.readAllLines(file);
        for (String s : lines) {
            if (s.contains("This is the file we need")) {
                System.out.println("Нужный файл обнаружен!");
                System.out.println(file.toAbsolutePath());
                break;
            }
        }
        return FileVisitResult.CONTINUE;
    }
}
