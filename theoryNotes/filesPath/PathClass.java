package theoryNotes.filesPath;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathClass {
    public static void main(String[] args) {

        // paths = из переданной строки или URI получить объект типа Path
        Path testFilePath = Paths.get("C:\\Users\\Username\\Desktop\\testFile.txt");

        Path fileName = testFilePath.getFileName();
        System.out.println(fileName);

        Path parent = testFilePath.getParent();
        System.out.println(parent);

        Path root = testFilePath.getRoot();
        System.out.println(root);

        // endsWith() проверяет, заканчивается ли текущий путь на переданный путь. Именно на путь, а не набор символов.
        boolean endWithTxt = testFilePath.endsWith("Desktop\\testFile.txt");
        System.out.println(endWithTxt);

        boolean startsWithLalala = testFilePath.startsWith("lalalala");
        System.out.println(startsWithLalala);

        System.out.println("=".repeat(30));
        // boolean isAbsolute() — возвращает true, если текущий путь является абсолютным
        System.out.println(testFilePath.isAbsolute());  // true

        // Path normalize() — «нормализует» текущий путь, удаляя из него ненужные элементы
        Path path5 = Paths.get("C:\\Users\\Java\\.\\examples");
        System.out.println(path5.normalize());
        Path path6 = Paths.get("C:\\Users\\Java\\..\\examples");
        System.out.println(path6.normalize());

        // Path relativize() — вычисляет относительный путь между текущим и переданным путем
        Path testFilePath1 = Paths.get("C:\\Users\\Users\\Users\\Users");
        Path testFilePath2 = Paths.get("C:\\Users\\Users\\Users\\Users\\Username\\Desktop\\testFile.txt");
        System.out.println(testFilePath1.relativize(testFilePath2));    // Username\Desktop\testFile.txt
    }
}
