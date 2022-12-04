package EasyTasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Отсортируйте этот список по возрастанию длины фамилии, реализовав интерфейс компаратора через анонимный класс
*/

public class ComparatorViaAnonClass {
    public static void main(String[] args) throws Exception {
        List<String> people = new ArrayList<>(List.of(
                "Мария Зуева",
                "Анна Дарк",
                "Кирилл Филимонов",
                "Ева Пинк"
        ));

        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String[] s1Lines = s1.split(" ");
                String[] s2Lines = s2.split(" ");
                return s1Lines[1].length() - s2Lines[1].length();
            }
        };

        Collections.sort(people, comparator);
        System.out.println(people);
    }
}
