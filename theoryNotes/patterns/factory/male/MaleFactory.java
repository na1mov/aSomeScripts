package theoryNotes.patterns.factory.male;

import theoryNotes.patterns.factory.AbstractFactory;
import theoryNotes.patterns.factory.Human;

public class MaleFactory implements AbstractFactory {

    public Human getPerson(int age) {
        Human human = null;
        if (age <= KidBoy.MAX_AGE) {
            human = new KidBoy();
        } else if (age <= TeenBoy.MAX_AGE) {
            human = new TeenBoy();
        } else
            human = new Man();
        return human;
    }
}
