package theoryNotes.patterns.factory.male;

import theoryNotes.patterns.factory.Human;

public class KidBoy implements Human {
    public static final int MAX_AGE = 12;

    @Override
    public String toString() {
        return "KidBoy{}";
    }
}
