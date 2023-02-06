package theoryNotes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarClass {
    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar(2017, 0 , 25);
        Date date = calendar.getTime();
        System.out.println(date);
        System.out.println("Год: " + calendar.get(Calendar.YEAR));
        System.out.println("Месяц: " + calendar.get(Calendar.MONTH));
        //порядковый номер недели в месяце
        System.out.println("Порядковый номер недели в месяце: " + calendar.get(Calendar.WEEK_OF_MONTH));

        System.out.println("Число: " + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("Часы: " + calendar.get(Calendar.HOUR));
        System.out.println("Минуты: " + calendar.get(Calendar.MINUTE));
        System.out.println("Секунды: " + calendar.get(Calendar.SECOND));
        System.out.println("Миллисекунды: " + calendar.get(Calendar.MILLISECOND));

        Calendar calendar1 = new GregorianCalendar();
        calendar1.set(Calendar.YEAR, 2017);
        calendar1.set(Calendar.MONTH, 0);
        calendar1.set(Calendar.DAY_OF_MONTH, 25);
        calendar1.set(Calendar.HOUR_OF_DAY, 19);
        calendar1.set(Calendar.MINUTE, 42);
        calendar1.set(Calendar.SECOND, 12);
        System.out.println(calendar1.getTime());

        // add() - прибавление и вычитание значений в классе Calendar
        calendar1.add(Calendar.MONTH, -2); //чтобы отнять значение - в метод нужно передать отрицательное число
        System.out.println(calendar1.getTime());

        // roll() может прибавлять и убавлять значения, не затрагивая при этом  остальные значения
        calendar1.roll(Calendar.MONTH, -2);
        System.out.println(calendar1.getTime());

        // Для создания даты “до нашей эры” нужно использовать поле Calendar.Era
        GregorianCalendar cannes = new GregorianCalendar(216, Calendar.AUGUST, 2);
        cannes.set(Calendar.ERA, GregorianCalendar.BC);

        DateFormat df = new SimpleDateFormat("dd MMM yyy GG");
        System.out.println(df.format(cannes.getTime()));
    }
}
