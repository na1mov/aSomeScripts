package theoryNotes.patterns.factory;

import theoryNotes.patterns.factory.female.FemaleFactory;
import theoryNotes.patterns.factory.male.MaleFactory;

public class FactoryProducer {
    public static AbstractFactory getFactory(HumanFactoryType type) {
        if (type == HumanFactoryType.MALE) {
            return new MaleFactory();
        } else {
            return new FemaleFactory();
        }
    }

    public enum HumanFactoryType {
        MALE,
        FEMALE
    }
}
