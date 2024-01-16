package com.assigment;

import java.util.HashMap;
import java.util.Locale;

public class Striing {
    public static String getFirstNonRepeatingCharacter(String input) {
        HashMap<Character, Integer> countMap = new HashMap<>();
        input = input.toLowerCase(Locale.ROOT);
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < input.length(); i++) {
            if (countMap.get(input.charAt(i)) == 1) {
                return String.valueOf(input.charAt(i));
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getFirstNonRepeatingCharacter("Successfully")); // должно вернуть "e"
        System.out.println(getFirstNonRepeatingCharacter("Initially"));    // должно вернуть "n"
    }

}
