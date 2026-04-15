import java.util.Stack;

public class InfixConversion {

    // Function to check precedence
    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    // Infix to Postfix
    static String infixToPostfix(String exp) {
        String result = "";
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            // Operand
            if (Character.isLetterOrDigit(ch)) {
                result += ch;
            }

            // Left parenthesis
            else if (ch == '(') {
                stack.push(ch);
            }

            // Right parenthesis
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result += stack.pop();
                }
                stack.pop();
            }

            // Operator
            else {
                while (!stack.isEmpty() &&
                        precedence(stack.peek()) >= precedence(ch)) {
                    result += stack.pop();
                }
                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    // Reverse string
    static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    // Infix to Prefix
    static String infixToPrefix(String exp) {
        // Reverse infix
        exp = reverse(exp);

        // Replace brackets
        String temp = "";
        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) == '(')
                temp += ')';
            else if (exp.charAt(i) == ')')
                temp += '(';
            else
                temp += exp.charAt(i);
        }

        // Convert to postfix
        String postfix = infixToPostfix(temp);

        // Reverse postfix to get prefix
        return reverse(postfix);
    }

    public static void main(String[] args) {
        String infix = "A+B*C";

        System.out.println("Infix: " + infix);
        System.out.println("Postfix: " + infixToPostfix(infix));
        System.out.println("Prefix: " + infixToPrefix(infix));
    }
}
