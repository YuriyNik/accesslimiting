package com.assigment;

import java.util.HashMap;

public class StringProblem {
    public static String getFirstNonRepeatingCharacter(String input) {
        HashMap<Character, Integer> charCountMap = new HashMap<>();
        input=input.toLowerCase();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            charCountMap.put(ch, charCountMap.getOrDefault(ch,0)+1);
        }
        for (int i = 0; i < input.length(); i++) {
            if (charCountMap.get(input.charAt(i))==1){
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
