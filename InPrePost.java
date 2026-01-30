import java.util.*;

public class InPrePost {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Infix: ");
        
        String infix = sc.nextLine();
        String[] elem = infix.split(" ");
        
        ArrayList<String> reverseExpression = new ArrayList<>();
        ArrayList<String> nonReverseExpression = new ArrayList<>();
        ArrayList<String> symbols = new ArrayList<>();

        String number = "";
        //Reverse the array for prefix
        String[] reverseElem = new String[elem.length];
        int elemPointer = 0;
        for(int i = elem.length-1; i >= 0; i--){
            reverseElem[i] = elem[elemPointer];
            elemPointer++;
        }

        elemPointer = 0;

        //prefix
        for(String token: reverseElem){
            if(token.equals("+")
            || token.equals("-")){
                reverseExpression.add(number);

                for(int i = symbols.size()-1; i >= 0; i--){
                    if(symbols.get(i).equals("*")
                    || symbols.get(i).equals("/")
                    || symbols.get(i).equals("%")
                    || symbols.get(i).equals("^")
                    || symbols.get(i).equals("log")){
                        reverseExpression.add(symbols.get(i));
                        symbols.remove(i);
                    } else{
                        break;
                    }
                }

                symbols.add(token);
                number = "";

            } else if (token.equals("*")
                    || token.equals("/")
                    || token.equals("%")) {

                for(int i = symbols.size()-1; i >= 0; i--){
                    if(symbols.get(i).equals("^")
                    || symbols.get(i).equals("log")){
                        reverseExpression.add(symbols.get(i));
                        symbols.remove(i);
                    } else{
                        break;
                    }
                }
                
                reverseExpression.add(number);
                symbols.add(token);
                number = "";
                
            } else if (token.equals("^")
                    || token.equals("log")) {

                reverseExpression.add(number);
                symbols.add(token);
                number = "";

           } else if (token.equals(")")) {
                // Acts like "(" in reversed infix
                if (!number.isEmpty()) {
                    reverseExpression.add(number);
                    number = "";
                }
                symbols.add(token);

            } else if (token.equals("(")) {
                // Acts like ")" in reversed infix
                if (!number.isEmpty()) {
                    reverseExpression.add(number);
                    number = "";
                }

                while (!symbols.isEmpty()) {
                    String top = symbols.remove(symbols.size() - 1);
                    if (top.equals(")")) {
                        break; // discard matching parenthesis
                    }
                    reverseExpression.add(top);
                }
            }else{
                number = token;
            }
        }
        reverseExpression.add(number);

        //add the remaining symbols
        for(int i = symbols.size()-1; i >= 0; i--){
            reverseExpression.add(symbols.get(i));
        }

        //reverse the array again
        String[] finalExpression = new String[reverseExpression.size()];

        for(int i = reverseExpression.size()-1; i >= 0; i--){
            finalExpression[elemPointer] = reverseExpression.get(i);
            elemPointer++;
        }

        //Print the result
        System.out.print("Prefix: ");
        for(String token: finalExpression){
            System.out.print(token + " ");
        }

        System.out.println();
        symbols.clear();

        //POSTFIX
        for(String token: elem){
            if(token.equals("+")
            || token.equals("-")){
                nonReverseExpression.add(number);

                for(int i = symbols.size()-1; i >= 0; i--){
                    if(symbols.get(i).equals("+")
                    || symbols.get(i).equals("-")
                    || symbols.get(i).equals("*")
                    || symbols.get(i).equals("/")
                    || symbols.get(i).equals("%")
                    || symbols.get(i).equals("^")
                    || symbols.get(i).equals("log")){
                        nonReverseExpression.add(symbols.get(i));
                        symbols.remove(i);
                    } else{
                        break;
                    }
                }

                symbols.add(token);
                number = "";

            } else if (token.equals("*")
                    || token.equals("/")
                    || token.equals("%")) {

                for(int i = symbols.size()-1; i >= 0; i--){
                    if(symbols.get(i).equals("*")
                    || symbols.get(i).equals("/")
                    || symbols.get(i).equals("^")
                    || symbols.get(i).equals("log")){
                        nonReverseExpression.add(symbols.get(i));
                        symbols.remove(i);
                    } else{
                        break;
                    }
                }
                
                nonReverseExpression.add(number);
                symbols.add(token);
                number = "";
                
            } else if (token.equals("^")
                    || token.equals("log")) {

                for(int i = symbols.size()-1; i >= 0; i--){
                    if(symbols.get(i).equals("^")
                    || symbols.get(i).equals("log")){
                        nonReverseExpression.add(symbols.get(i));
                        symbols.remove(i);
                    } else{
                        break;
                    }
                }

                nonReverseExpression.add(number);
                symbols.add(token);
                number = "";

           } else if (token.equals(")")) {
                // Acts like "(" in reversed infix
                if (!number.isEmpty()) {
                    nonReverseExpression.add(number);
                    number = "";
                }
                symbols.add(token);

            } else if (token.equals("(")) {
                // Acts like ")" in reversed infix
                if (!number.isEmpty()) {
                    nonReverseExpression.add(number);
                    number = "";
                }

                while (!symbols.isEmpty()) {
                    String top = symbols.remove(symbols.size() - 1);
                    if (top.equals(")")) {
                        break; // discard matching parenthesis
                    }
                    nonReverseExpression.add(top);
                }
            }else{
                number = token;
            }
        }
        nonReverseExpression.add(number);
        for(int i = symbols.size()-1; i >= 0; i--){
            nonReverseExpression.add(symbols.get(i));
        }

        //Print the result
        System.out.print("Postfix: ");
        for(String token: nonReverseExpression){
            System.out.print(token + " ");
        }
    }
}
