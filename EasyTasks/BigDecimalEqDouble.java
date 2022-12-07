package EasyTasks;

import java.math.BigDecimal;

/*
BigDecimal класс специально был создан для хранения очень больших чисел с дробной частью. В отличие от double и float,
при использовании BigDecimal сложение, вычитание и прочие математические операции выполняются не с помощью операторов
(+- и т.д.), а с помощью методов.

Вывод в консоль:
f1 = 1.1000000000000000610622663543836097232997417449951171875
f2 = 1.1000000000000000610622663543836097232997417449951171875
f1 и f2 равны
*/

public class BigDecimalEqDouble {

    public static void main(String[] args)  {

       /*Создаем два объекта BigDecimal - ноль и 0.1.
       Делаем то же самое что и раньше - прибавляем 0.1 к нулю 11 раз подряд
       В классе BigDecimal сложение осуществляется с помощью метода add()*/

        BigDecimal f1 = new BigDecimal(0.0);
        BigDecimal pointOne = new BigDecimal(0.1);
        for (int i = 1; i <= 11; i++) {
            f1 = f1.add(pointOne);
        }

       /*Здесь тоже ничего не изменилось: создаем два объекта BigDecimal
       и умножаем 0.1 на 11
       В классе BigDecimal умножение осуществляется с помощью метода multiply()*/

        BigDecimal f2 = new BigDecimal(0.1);
        BigDecimal eleven = new BigDecimal(11);
        f2 = f2.multiply(eleven);

        System.out.println("f1 = " + f1);
        System.out.println("f2 = " + f2);

       /*Еще одна особенность BigDecimal - объекты чисел нужно сравнивать между
       собой с помощью специального метода compareTo()*/

        if (f1.compareTo(f2) == 0)
            System.out.println("f1 и f2 равны");
        else
            System.out.println("f1 и f2 не равны");
    }
}

// никогда не используй == при сравнении чисел с плавающей точкой, пример

class EqualsFloatDouble {

    public static void main(String[] args)  {

        final double threshold = 0.0001;

        //прибавляем к нулю 0.1 одиннадцать раз подряд
        double f1 = .0;
        for (int i = 1; i <= 11; i++) {
            f1 += .1;
        }

        //Умножаем 0.1 на 11
        double f2 = .1 * 11;

        System.out.println("f1 = " + f1);
        System.out.println("f2 = " + f2);

        if (Math.abs(f1 - f2) < threshold)
            System.out.println("f1 и f2 равны");
        else
            System.out.println("f1 и f2 не равны");
    }
}
