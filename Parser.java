import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static List<String> parseExpression(String expression) {
        expression = expression.replace(" ", "");
        ArrayList<String> partsList = new ArrayList<>();
        StringBuilder numberBuilder = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);

            if (Character.isDigit(currentChar)) {
                numberBuilder.append(currentChar);
                if (i == expression.length() - 1) {
                    partsList.add(numberBuilder.toString());
                }
            } else {
                if (numberBuilder.length() > 0) {
                    partsList.add(numberBuilder.toString());
                    numberBuilder.setLength(0);
                }
                partsList.add(Character.toString(currentChar));
            }
        }

        return partsList;
    }
}