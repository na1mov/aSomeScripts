package theoryNotes;

// Создание объекта

public class ObjectCreation {
    public static void main(String[] args) {
        Child child = new Child();
    }
}
class Parent {
    public static Name name = new Name("статическая переменная name Parent");
    public Age age = new Age("переменная age Parent");

    static { System.out.println("статический блок инициализации Parent"); }

    { System.out.println("блок инициализации Parent"); }

    public Parent() { System.out.println("конструктор Parent()"); }
}
class Child extends Parent {
    public static Name name_ = new Name("статическая переменная name_ Child");
    public Age age_ = new Age("переменная age_ Child");

    static { System.out.println("статический блок инициализации Child"); }

    { System.out.println("блок инициализации Child"); }

    public Child() { System.out.println("конструктор Child()"); }
}

class Name {
    public Name(String str) { System.out.println(str); }
}
class Age {
    public Age(String str) { System.out.println(str); }
}
