package theoryNotes;

import java.time.LocalDateTime;
import java.util.*;

/*
Для случаев, когда объекты класса используются только в одном месте программы, в Java есть специальный инструмент —
анонимный класс (англ. anonymous class). Такой класс можно объявить прямо в момент создания его объекта. Для этого
нужно после объявления переменной и знака = написать ключевое слово new, затем указать родителя или имплементируемый
интерфейс, поставить круглые скобки, а после — в фигурных скобках — написать необходимую реализацию.

Все локальные переменные и параметры вне анонимного класса доступны ему только для чтения.
Если в реализации анонимного класса была использована переменная, значит, менять её после этого в коде уже нельзя.

Например, лямбдами можно реализовывать только функциональные интерфейсы, то есть те, которые содержат ровно один метод.
Если же абстрактных методов в интерфейсе много, лямбда с такой задачей не справится. В анонимных классах можно
объявлять поля и использовать их в реализуемых методах, в то время как в лямбдах полей быть не может.
*/

public class AnonymousClass {
    interface StringsSaverConfig {
        // как нужно преобразовать сохраняемую строку?
        String transform(String line);

        // дополнительное действие при сохранении
        void onSave(String line);
    }

    static class StringsSaver {
        public static final int MAX_SIZE = 10_000;

        private List<String> saved = new LinkedList<>();
        private StringsSaverConfig config;

        public StringsSaver(StringsSaverConfig config) {
            this.config = config;
        }

        public void save(String line) {
            if (saved.size() == MAX_SIZE) {
                saved.remove(0);
            }
            line = config.transform(line);
            saved.add(line);
            config.onSave(line);
        }

        public List<String> getSaved() {
            return saved;
        }
    }

    public static class AnonTest {

        public static void main(String[] args) {
            // Пример анонимного класса
            StringsSaverConfig config = new StringsSaverConfig() {
                List<String> errors = new ArrayList<>();

                @Override
                public String transform(String line) {
                    return "[" + LocalDateTime.now() + "] " + line;
                }

                @Override
                public void onSave(String line) {
                    if (line.contains("ERROR")) {
                        errors.add(line);
                        if (errors.size() == 2) {
                            System.out.println("[1] " + errors.get(0));
                            System.out.println("[2] " + errors.get(1));
                        } else if (errors.size() > 2) {
                            System.out.println("[" + errors.size() + "] " + errors.get(errors.size() - 1));
                        }
                    } else {
                        errors.clear();
                    }
                }
            };

            StringsSaver saver = new StringsSaver(config);
            saver.save("Пользователь залогинился");
            saver.save("ERROR Пользователь загрузил фото");     // ничего не выводим
            saver.save("ERROR Пользователь загрузил видео");    // вывод "2 ошибки подряд"
            saver.save("ERROR Пользователь загрузил аватар");   // вывод "3 ошибки подряд"
            saver.save("Пользователь пополнил счёт");
            saver.save("Пользователь купил артефакт");
            saver.save("ERROR Пользователь опубликовал пост");  // ничего не выводим
            saver.save("Пользователь продал артефакт");
            saver.save("ERROR Пользователь опубликовал пост");
            saver.save("ERROR Пользователь опубликовал пост");  // вывод "2 ошибки подряд"
            System.out.println(saver.getSaved());

        }
    }
}
