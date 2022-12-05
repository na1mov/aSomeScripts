package EasyTasks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;

/*
Прочесть с консоли имя файла.
Считывать строки с консоли, пока пользователь не введет строку "exit".
Записать абсолютно все введенные строки в файл: каждую строчку — с новой строки.
*/

public class ConsoleToFile {
    public static void main(String[] args) throws Exception {

        try (InputStreamReader reader = new InputStreamReader(System.in);
             BufferedReader buff = new BufferedReader(reader))
        {
            String filePath = buff.readLine();
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath)))
            {
                boolean isTrue = false;
                do {
                    String nextLine = buff.readLine();
                    bufferedWriter.write(nextLine+"\n");
                    if(nextLine.equals("exit")) {
                        isTrue = true;
                    }
                } while (!isTrue);
            }
        }
    }
}
