import java.util.*;
public class COMPLEXCALC {

    public double getSquareRoot(){
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter a number: ");
    double number = sc.nextInt();
    double sqrtn = Math.sqrt(number);
        if (sqrtn<0){
            sqrtn = 0;
    }
             return sqrtn;
}
    public int getFactorial(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = sc.nextInt();
        int result = 1;
        for (int i = 1; i <= number; i++){
            result *=i ;
        if (result<0){
            result = 0;
        }
        }
        return result;
    }

    public double getSum(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number1 and number 2 (SPACE INBETWEEN): ");
        int number1= sc.nextInt();
        int number2 = sc.nextInt();
        double sum = number1 + number2;
        return sum;
    }
    
    public  double getProduct(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number1 and number 2 (SPACE INBETWEEN): ");
        int number1= sc.nextInt();
        int number2 = sc.nextInt();
        double product = number1 * number2;
        return product;
    }
    
    public double getDifference(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number1 and number 2 (SPACE INBETWEEN): ");
        int number1= sc.nextInt();
        int number2 = sc.nextInt();
        double Difference = number1 - number2;
        return Difference;
    }
    
    public double getQuotient(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number1 and number 2 (SPACE INBETWEEN): ");
        int number1= sc.nextInt();
        int number2 = sc.nextInt();
        double Quotient = number1/number2;
        if (number1==0 || number2==0) {
            Quotient = 0;

        }
        return Quotient;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = 1;
        while (number != 0){
        System.out.println("Enter a number (1 for sqrt, 2 for factorial, 3 for addition, 4 for multiplication, 5 for substraction, 6 for Division, 0 for exit): ");
        int Number = sc.nextInt();

        if (Number==1){
        COMPLEXCALC sqrt = new COMPLEXCALC();
        double getSquareRoot = sqrt.getSquareRoot();
        System.out.println(getSquareRoot);
        }
        
        else if (Number ==2){
        COMPLEXCALC factorial = new COMPLEXCALC();
        int getFactorial = factorial.getFactorial();
        System.out.println(getFactorial);
        }
        
        else if(Number ==3){
        COMPLEXCALC sum = new COMPLEXCALC();
        double getSum = sum.getSum();
        System.out.println(getSum);
        }
        
        else if (Number ==4 ){
        COMPLEXCALC product = new COMPLEXCALC();
        double getProduct = product.getProduct();
        System.out.println(getProduct);
        }
        
        else if (Number==5){
        COMPLEXCALC DIFFERENCE = new COMPLEXCALC();
        double getDifference = DIFFERENCE.getDifference();
        System.out.println(getDifference);
        }
        
        else if (Number==6){
            COMPLEXCALC QUOTIENT = new COMPLEXCALC();
            double getQuotient = QUOTIENT.getQuotient();
            System.out.println(getQuotient);
        }
        else if (Number==0){
            System.exit(0);
        
        }
        }
    }
}

