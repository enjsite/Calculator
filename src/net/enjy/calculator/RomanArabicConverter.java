package net.enjy.calculator;

import java.util.HashMap;
import java.util.List;

public class RomanArabicConverter {

    private static HashMap<String,Integer> romeMap = new HashMap<>();

    public static HashMap<String,Integer> RomeToArabic() {
        romeMap.put("I",1);
        romeMap.put("II",2);
        romeMap.put("III",3);
        romeMap.put("IV",4);
        romeMap.put("V",5);
        romeMap.put("VI",6);
        romeMap.put("VII",7);
        romeMap.put("VIII",8);
        romeMap.put("IX",9);
        romeMap.put("X",10);
        return romeMap;
    }

    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " числа не соответствуют условиям");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }
        return sb.toString();
    }
}
