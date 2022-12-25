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
        if (this == o)
            return true;
        if (o == null || o.getClass() != this.getClass())
            return false;

        EqualsAndHashEx solution1 = (EqualsAndHashEx) o;

        if (Double.compare(solution1.aDouble, aDouble) != 0)
            return false;
        if (anInt != solution1.anInt)
            return false;
        if (date != solution1.date && (date != null && !date.equals(solution1.date)))
            return false;
        if (solution != solution1.solution && (solution != null && !solution.equals(solution1.solution)))
            return false;
        if (string != solution1.string && (string != null && !string.equals(solution1.string)))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = anInt;
        temp = aDouble != +0.0d ? Double.doubleToLongBits(aDouble) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (solution != null ? solution.hashCode() : 0);
        result = 31 * result + (string != null ? string.hashCode() : 0);
        return result;
    }

    public static void main(String[] args) {

    }
}
