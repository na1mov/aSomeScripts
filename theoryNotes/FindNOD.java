package theoryNotes;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FindNOD {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int first = Integer.parseInt(reader.readLine());
        int second = Integer.parseInt(reader.readLine());

        System.out.println(getNOD(first, second));
    }

    private static int getNOD(int first, int second) {
        if (first < 1 || second < 1) {
            throw new IllegalArgumentException();
        }

        while (first != second) {
            if (first > second) {
                first -= second;
            }
            if (second > first) {
                second -= first;
            }
        }
        return first;
    }
}

class MyGetNOD {
    public static void main(String[] args) throws Exception {
        int firstNum = 0;
        int secondNum = 0;
        try (BufferedReader buff = new BufferedReader(new InputStreamReader(System.in))) {
            if (buff.ready()) {
                firstNum = Integer.parseInt(buff.readLine());
                secondNum = Integer.parseInt(buff.readLine());
            }
        }
        int min = firstNum < secondNum ? firstNum : secondNum;
        for (int i = min; i > 0; i--) {
            if ((firstNum % i == 0) && (secondNum % i == 0)) {
                System.out.println(i);
                break;
            }
        }
    }
}
