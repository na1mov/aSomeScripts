package theoryNotes;

import java.util.Arrays;
import java.util.HashMap;

public class IsPremutation {
    public static void main(String[] args) {
        String left = "onetwo";
        String right = "twoone";
        System.out.println(isPermutation(left, right));
        System.out.println(isPermutationTwo(left, right));
    }

    public static boolean isPermutation(String left, String right) {
        // учесть null в строках
        if (left.length() != right.length()) {
            return false;
        }

        char[] leftCharacter = left.toCharArray();
        char[] rightCharacter = right.toCharArray();

        Arrays.sort(leftCharacter);
        Arrays.sort(rightCharacter);
        return Arrays.equals(leftCharacter, rightCharacter);
    }

    public static boolean isPermutationTwo(String left, String right) {
        // учесть null в строках
        if (left.length() != right.length()) {
            return false;
        }

        char[] leftCharacter = left.toCharArray();
        char[] rightCharacter = right.toCharArray();

        HashMap<Character, Integer> characterCount = new HashMap<>();
        for(char c: leftCharacter) {
            characterCount.put(c, characterCount.getOrDefault(c, 0) + 1);
        }
        for(char c: rightCharacter) {
            if(!characterCount.containsKey(c)) {
                return false;
            }
            characterCount.put(c, characterCount.getOrDefault(c, 0) - 1);
        }
        for(int count: characterCount.values()) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
}
