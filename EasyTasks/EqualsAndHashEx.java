package EasyTasks;

import java.util.*;

/*
Ошибка в equals/hashCode
*/

public class EqualsAndHashEx {
    private int anInt;
    private String string;
    private double aDouble;
    private Date date;
    private EqualsAndHashEx solution;

    public EqualsAndHashEx(int anInt, String string, double aDouble, Date date, EqualsAndHashEx solution) {
        this.anInt = anInt;
        this.string = string;
        this.aDouble = aDouble;
        this.date = date;
        this.solution = solution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) {
            return false;
        }
        if (!(o instanceof EqualsAndHashEx)) return false;

        EqualsAndHashEx solution1 = (EqualsAndHashEx) o;
        if (Double.compare(solution1.aDouble, aDouble) != 0) return false;
        if (anInt != solution1.anInt) return false;
        if (date != null ? !date.equals(solution1.date) : solution1.date == null) return false;
        if (solution != null ? !solution.equals(solution1.solution) : solution1.solution == null) return false;
        if (string != null ? !string.equals(solution1.string) : solution1.string == null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(anInt,string,aDouble,date,solution);
    }

    public static void main(String[] args) {

    }
}
