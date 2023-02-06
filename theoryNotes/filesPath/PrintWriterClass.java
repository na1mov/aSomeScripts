package theoryNotes.filesPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PrintWriterClass {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("someFile");
        PrintWriter pw = new PrintWriter(file);

        pw.println("Some text");
        pw.println("Some test text");

        pw.close();
    }
}
