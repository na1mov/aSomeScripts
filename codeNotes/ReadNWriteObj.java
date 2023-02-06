package codeNotes;

import java.io.*;
import java.util.Arrays;

public class ReadNWriteObj {
    public static Person[] persons = {new Person(1, "Bob"),
            new Person(2, "Mike"), new Person(3, "Tom")};
    public static void main(String[] args) {
        writeObject();
        readObject();
    }

    public static void writeObject() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("someFile"))) {
            oos.writeObject(persons);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readObject() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("someFile"))) {
            Person[] people = (Person[]) ois.readObject();
            System.out.println(Arrays.toString(people));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

class Person implements Serializable {
    private final int id;
    private final String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return id + " : " + name;
    }
}
