package EasyTasks.lambda;

import java.util.ArrayList;
import java.util.List;

public class LambdaReferences {
    public static void main(String[] args) {
        List<String> listString = new ArrayList<>();
        listString.add("one");
        listString.add("two");
        listString.add("three");

        listString.forEach(LambdaReferences::staticMethod);

        LambdaReferences instance = new LambdaReferences();
        listString.forEach(instance::nonStaticMethod);

        listString.forEach(String::toUpperCase);
    }

    private static void staticMethod(String s) {

    }

    private void nonStaticMethod(String s) {

    }
}
