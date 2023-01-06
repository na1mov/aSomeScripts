package CodeParts;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ReadFileLines {
    public  void readFileLines1() throws IOException {
        //создаем список для хранения строк
        List<String> list = new ArrayList<String>();

        // открываем файл
        File file = new File("c:/document.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        //пока файл не пуст – читаем из него
        while(reader.ready()) {
            list.add(reader.readLine());
        }

        //закрываем файл
        reader.close();
    }

    public  void readFileLines2() throws IOException {
        File file = new File("c:/document.txt");
        List<String> list = Files.readAllLines(file.toPath(), Charset.defaultCharset());
    }
}
