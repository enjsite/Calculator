package net.enjy.calculator;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    private String operation = "";
    private Argument a1;
    private Argument a2;
    private boolean isRomanians = false;

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        String calcStr = scanner.nextLine();

        Calculator calculator = new Calculator();
        calculator.parsingStr(calcStr);

        System.out.println("Результат: " + calculator.calculation());
        scanner.close();
    }


    public void parsingStr(String calcStr) throws Exception {

        Pattern operationPattern = Pattern.compile("[+-/*]");

        Matcher matcher = operationPattern.matcher(calcStr);

        if (matcher.find()) {
            operation = calcStr.substring(matcher.start(), matcher.end());
        } else {
            throw new Exception("Не найдено действие.");
        }

        // получение аргументов
        String[] nums = operationPattern.split(calcStr,2);

        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i].trim();
        }

        a1 = new Argument(nums[0]);
        a2 = new Argument(nums[1]);
    }

    public String calculation() throws Exception {

        int arg1;
        int arg2;
        Integer result;
        String resultStr;

        if (a1.isArabic()) {
            arg1 = a1.getNumFromArStr();
            arg2 = a2.getNumFromArStr();
        } else if (a1.isRomanian()) {
            isRomanians = true;
            arg1 = a1.getNumFromRomStr();
            arg2 = a2.getNumFromRomStr();
        } else {
            throw new Exception("Числа не соответствуют условию.");
        }

        switch (operation) {
            case "+":
                result = arg1 + arg2;
                break;
            case "-":
                result = arg1 - arg2;
                break;
            case "/":
                result = arg1 / arg2;
                break;
            case "*":
                result = arg1 * arg2;
                break;
            default:
                throw new Exception("Не найдено действие.");
        }

        if (isRomanians){
            resultStr = RomanArabicConverter.arabicToRoman(result);
        } else {
            resultStr = result.toString();
        }
        return resultStr;
    }

}
