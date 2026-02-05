package labactivity.infixconverter;

import java.util.*;

public class InfixConverter {

    // ---------------- precedence ----------------
    static int precedence(char c) {
        if (c == '~') return 4; // unary minus
        if (c == '^') return 3;
        if (c == '*' || c == '/' || c == '%') return 2;
        if (c == '+' || c == '-') return 1;
        if (c == '#') return -2;
        return 0;
    }

    static String stackToString(Stack<Character> stack) {
        StringBuilder sb = new StringBuilder();
        for (char c : stack) sb.append(c);
        return sb.toString();
    }

    // ---------------- handle unary minus ----------------
    static String handleUnaryOperators(String infix) {
        StringBuilder sb = new StringBuilder();
        char prev = '#';
        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);
            if (c == '-' && (i == 0 || prev == '(' || prev == '+' || prev == '-' || prev == '*' || prev == '/' || prev == '%')) {
                sb.append('~'); // unary minus
            } else {
                sb.append(c);
            }
            if (c != ' ') prev = c;
        }
        return sb.toString();
    }

    // ---------------- check if infix is valid ----------------
    static boolean isValidInfix(String infix) {
        int balance = 0;
        char prev = '#';
        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);
            if (c == '(') balance++;
            else if (c == ')') balance--;
            if (balance < 0) return false; // unmatched )
            if ("+-*/%^".indexOf(c) != -1 && "+-*/%^".indexOf(prev) != -1) return false; // consecutive operators
            if (!Character.isLetterOrDigit(c) && "+-*/%^()".indexOf(c) == -1) return false; // invalid char
            prev = c;
        }
        return balance == 0;
    }

    // ---------------- INFIX TO PREFIX ----------------
    static String infixToPrefix(String infix) {
        System.out.println("\nINFIX TO PREFIX");
        System.out.println("-----------------------------------------------------------");
        System.out.printf("%-12s %-12s %-12s\n", "ELEMENTS", "OPERANDS", "VALUES");

        infix = handleUnaryOperators(infix);

        String reversed = new StringBuilder(infix).reverse().toString();
        String swapped = "";
        for (char c : reversed.toCharArray()) {
            if (c == '(') swapped += ')';
            else if (c == ')') swapped += '(';
            else swapped += c;
        }
        swapped += "#";

        Stack<Character> stack = new Stack<>();
        StringBuilder output = new StringBuilder();

        for (char c : swapped.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                output.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.append(stack.pop());
                }
                if (!stack.isEmpty()) stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(c) < precedence(stack.peek())) {
                    output.append(stack.pop());
                }
                stack.push(c);
            }

            String stackDisplay = stackToString(stack);
            if (c == ')') stackDisplay = stackDisplay.replace("(", "");
            System.out.printf("%-12s %-12s %-12s\n", c, stackDisplay, output);
        }

        return output.reverse().toString();
    }

    // ---------------- INFIX TO POSTFIX ----------------
    static String infixToPostfix(String infix) {
        System.out.println("\nINFIX TO POSTFIX");
        System.out.println("-----------------------------------------------------------");
        System.out.printf("%-14s %-16s %-16s\n", "ELEMENTS", "OPERANDS", "VALUES");

        infix = handleUnaryOperators(infix);
        infix += "#";

        Stack<Character> stack = new Stack<>();
        StringBuilder output = new StringBuilder();

        for (char c : infix.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                output.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.append(stack.pop());
                }
                if (!stack.isEmpty()) stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    if (stack.peek() != '(') output.append(stack.pop());
                    else break;
                }
                stack.push(c);
            }
            System.out.printf("%-14s %-16s %-16s\n", c, stackToString(stack), output);
        }

        return output.toString();
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String option = "";

        while (!option.equals("2")) {
            System.out.println("\n--- INFIX CONVERTER ---");
            System.out.println("1. Enter an infix expression");
            System.out.println("2. Exit");
            System.out.print("Select an option: ");
            option = sc.nextLine().trim();

            switch (option) {
                case "1":
                    String infix = "";
                    while (true) {
                        System.out.print("ENTER A INFIX: ");
                        infix = sc.nextLine().replaceAll("\\s+", "");
                        if (isValidInfix(infix)) break;
                        System.out.println("Invalid infix expression! Please try again.");
                    }

                    String prefix = infixToPrefix(infix);
                    String postfix = infixToPostfix(infix);

                    System.out.println("-----------------------------------------------------------");
                    System.out.println("FINAL ANSWER");
                    System.out.println("INFIX   : " + infix);
                    System.out.println("PREFIX  : " + prefix);   // unary minus as ~
                    System.out.println("POSTFIX : " + postfix); // unary minus as ~
                    System.out.println("-----------------------------------------------------------");
                    break;

                case "2":
                    System.out.println("Program exited.");
                    break;

                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }

        sc.close();
    }
}