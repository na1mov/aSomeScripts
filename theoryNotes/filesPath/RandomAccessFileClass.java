package theoryNotes.filesPath;

/*
int read()	Читает один байт и возвращает его
int read(byte b[], int off, int len)	Читает массив байт
int read(byte b[])	Читает массив байт
void readFully(byte b[])	Читает массив байт, ждет, пока добавятся новые байты,
если их не хватает для заполнения массива
int skipBytes(int n)	Пропускает n байт. Т.е. перемещает курсор на n байт вперед
void write(int b)	Пишет один байт в то место, где стоит курсор
void write(byte b[])	Пишет массив байт в то место, где стоит курсор
void write(byte b[], int off, int len)	Пишет массив байт в то место, где стоит курсор
long getFilePointer()	Возвращает номер байта, на который указывает «курсор». Может быть от 0 до «длины файла»
void seek(long pos)	Перемещает «курсор», используемый для чтения/записи, в указанное место
long length()	Возвращает длину файла
void setLength(long newLength)	Устанавливает новую длину файла.
Если файл был больше – он обрезается, если меньше – расширяется и новое место заполняется нулями
void close()	Закрывает файл
boolean readBoolean()	Читает boolean с текущей позиции курсора в файле
byte readByte()	Читает byte с текущей позиции курсора в файле
char readChar()	Читает char с текущей позиции курсора в файле
int readInt()	Читает int с текущей позиции курсора в файле
long readLong()	Читает long с текущей позиции курсора в файле
float readFloat()	Читает float с текущей позиции курсора в файле
double readDouble()	Читает double с текущей позиции курсора в файле
String readLine()	Читает строку из файла и возвращает ее
void writeBoolean(boolean v)	Пишет boolean в файл (начиная с позиции курсора)
void writeByte(int v)	Пишет byte в файл (начиная с позиции курсора)
void writeChar(int v)	Пишет char в файл (начиная с позиции курсора)
void writeInt(int v)	Пишет int в файл (начиная с позиции курсора)
void writeLong(long v)	Пишет long в файл (начиная с позиции курсора)
void writeFloat(float v)	Пишет float в файл (начиная с позиции курсора)
void writeDouble(double v)	Пишет double в файл (начиная с позиции курсора)
void writeBytes(String s)	Пишет строку в файл (начиная с позиции курсора)
void writeChars(String s)	Пишет строку в файл (начиная с позиции курсора)
*/

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileClass {
    public static void main(String[] args) throws IOException {
        //r- read, файл открыт только для чтения (rw - чтение/запись)
        RandomAccessFile raf = new RandomAccessFile("input.txt", "r");
        //перемещаем «курсор» на 100-й символ.
        raf.seek(100);
        //читаем строку, начиная с текущего положения курсора и до конца строки
        String text = raf.readLine();
        //закрываем файл
        raf.close();
        System.out.println("=".repeat(30));
    }

    // байты не вставляются в середину файла, а заменяют те, которые там были
    public static void writeExample() throws IOException {
        //rw- read/write, файл открыт и для чтения и для записи
        RandomAccessFile raf = new RandomAccessFile("seek.txt", "rw");
        //пишем в файл строку, начиная с 0-го байта
        raf.writeBytes("It is a string");
        //ставим курсор на 8-й символ
        raf.seek(8);
        //печатаем в файл строку surprise!
        raf.writeBytes("surprise!");
        //закрываем файл
        raf.close();
    }

    /*
    Считать текст с файла начиная с позиции number, длиной такой же как и длина переданного текста в третьем параметре.
    Если считанный текст такой же как и text, то записать в конец файла строку 'true', иначе записать 'false'.
    */
    public class Solution {
        public void example(String... args) {
            try (RandomAccessFile raf = new RandomAccessFile(args[0], "rw")) {
                long number = Long.parseLong(args[1]);
                String text = args[2];
                long fileLength = raf.length();

                raf.seek(number);
                byte[] readBytes = new byte[text.length()];
                raf.read(readBytes, 0, text.length());

                String readString = new String(readBytes);

                raf.seek(fileLength);
                if (readString.equals(text)) {
                    raf.write("true".getBytes());
                } else {
                    raf.write("false".getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
