package net.enjy.calculator;

public class Argument {

    private static String arNum = "^\\d+$";
    private static String romNum = "^[IVXivx]+$";

    private String argument;

    public Argument(String argument) {
        this.argument = argument;
    }

    public boolean isArabic() {
        return argument.matches(arNum);
    }

    public boolean isRomanian() {
        return argument.matches(romNum);
    }

    public Integer getNumFromArStr() throws Exception {

        Integer iNum = null;
        try {
            iNum = Integer.parseInt(argument);

            if (!isValid(iNum)) {
                throw new Exception("Число не соответствует условию.");
            }
        } catch (Exception e) {
            throw new Exception("Число не преобразуется в Integer или не соответствует условию.");
        }

        return iNum;
    }

    public Integer getNumFromRomStr() throws Exception {

        argument = argument.toUpperCase();
        Integer iNum = RomanArabicConverter.RomeToArabic().get(argument);

        if (iNum == null || !isValid(iNum)) {
            throw new Exception("Число не соответствует условию.");
        }
        return iNum;
    }

    public static boolean isValid(Integer num) {
        return (num >= 1 && num <= 10);
    }


}
