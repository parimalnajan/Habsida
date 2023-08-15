import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Calculator {
   
    public int calculate(String expression) {
        List<String> partsList = Parser.parseExpression(expression);
        String[] parts = partsList.toArray(new String[0]);

        int result;
        if(parts.length == 3) {
            int a = validateNumber(parts[0]);
            String op = validateOperator(parts[1]);
            int b = validateNumber(parts[2]);
            result = execute(a, b, op);
        } else if(parts.length == 5) {
            int a = validateNumber(parts[0]);
            String op1 = validateOperator(parts[1]);
            int b = validateNumber(parts[2]);
            String op2 = validateOperator(parts[3]);
            int c = validateNumber(parts[4]);
            result = execute(execute(a, b, op1), c, op2);
        } else {
            throw new IllegalArgumentException("Error: Invalid expression");
        }
        return result;
    }
    
     private int validateNumber(String numStr) {
        int num = Integer.parseInt(numStr);
        if(num < 1 || num > 10) {
            throw new IllegalArgumentException("Error: Numbers must be between 1 and 10");
        }
        return num;
    }

    private static final List<String> VALID_OPERATORS = Arrays.asList("+", "-", "*", "/");
    private String validateOperator(String opStr) {
        if(opStr.length() != 1 || !VALID_OPERATORS.contains(opStr)) {
            throw new IllegalArgumentException("Invalid operator.");
        }
        return opStr;
    }

    private int execute(int a, int b, String op) {
        switch(op) {
            case "+": return add(a, b);
            case "-": return subtract(a, b);
            case "*": return multiply(a, b);
            case "/": return divide(a, b);
            default: throw new IllegalArgumentException("Error: Invalid operator");
        }
    }

    private int add(int a, int b) {
        return a + b;
    }

    private int subtract(int a, int b) {
        return a - b;
    }

    private int multiply(int a, int b) {
        return a * b;
    }

    private int divide(int a, int b) {
        return a / b;
    }

   
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Enter an expression in the format 'a+b+c' or 'a+b', using operators '+ , - , * , /', or 'exit' to stop the program");
            String expression = scanner.nextLine();
            if(expression.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }
           try {
                System.out.println("Result: " + calc.calculate(expression));
            } catch(Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
    }
}


