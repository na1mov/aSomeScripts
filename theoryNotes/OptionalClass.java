package theoryNotes;

import java.util.List;
import java.util.Optional;

/*
Класс Optional (англ. «опциональный») был разработан специально для проверки ошибок вида NullPointerException
на этапе компиляции.

Узнать, что хранится в ячейке — строковое значение или null — можно с помощью метода isPresent. А чтобы получить
значение, если оно там есть, применяется метод get. Или метод orElse, который в случае отсутствия значения в обёртке
вернёт то, что вы передадите в него параметром. Для создания объекта-обёртки используют метод
Optional.of(оборачиваемоеЗначение), а для создания пустого объекта-обёртки — Optional.empty().
*/

public class OptionalClass {

    public static Optional<String> longestName(List<Optional<String>> maybeNames) {
        if (maybeNames.isEmpty()) {
            //return null;
            return Optional.empty(); // создание пустой обёртки
        }
        String max = maybeNames.get(0).get();
        boolean allEmpty = false;
        for (Optional<String> name : maybeNames) {
            if (name.isPresent()) {
                if (name.get().length() > max.length())
                    max = name.get();
                allEmpty = true;
            }
        }
        //return max
        if (!allEmpty) {
            return Optional.empty();
        }
        return Optional.of(max); // создание обёртки со значением max
    }

    public static void main(String[] args) {
        Optional<String> longestOptional = longestName(List.of(
                Optional.of("Max"),
                Optional.empty(),
                Optional.of("Alexey"),
                Optional.empty(),
                Optional.empty(),
                Optional.of("Alex")
        ));
        if (longestOptional.isPresent()) {
            String longestName = longestOptional.get();
            System.out.println("Самое длинное имя состоит из "
                    + longestName.length() + " символов.");
        } else {
            System.out.println("Такого имени нет.");
        }
    }
}
