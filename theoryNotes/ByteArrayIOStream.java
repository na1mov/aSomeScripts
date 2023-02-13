package theoryNotes;

import java.io.*;

public class ByteArrayIOStream {
    public static void main(String[] args) throws Exception {
        String test = "Hi!\n My name is Richard\n I'm a photographer\n";
        InputStream inputStream = new ByteArrayInputStream(test.getBytes());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        executor(inputStream, outputStream);

        String result = new String(outputStream.toByteArray());
        System.out.println("Результат: " + result);
    }

    public static void executor(InputStream inputStream, OutputStream outputStream) throws Exception {
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        while (bis.available() > 0) {
            int data = bis.read();
            outputStream.write(data);
        }
    }
}
