package calculator.parser;

public class Parser {

    public static String[] parse(String input) {
        Delimiter delimiter = DelimiterResolver.resolve(input);
        String numberPart = DelimiterResolver.extractNumberPart(input);
        System.out.println("numberPart: " + numberPart);
        return numberPart.split(delimiter.getRegex());
    }

}
