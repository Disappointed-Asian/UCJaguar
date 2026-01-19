import java.util.*;

public class ArrayAddressCalculator{
    private static final Scanner sc = new Scanner(System.in);
    
    public static int getValue(String categ){
        int categNum;
        
        while(true){
            System.out.print("Enter " + categ);
            categNum = sc.nextInt();
            
            if(categNum <= 0){
                System.out.println("Input a number more than 0.");
            } else {
                return categNum;
            }
        }
    }
    
    public static void main(String[] args){
            System.out.println("--------Address Calculator--------");
            
            int dimensions = getValue("the number of Dimensions: ");
            
            System.out.print("Enter the Base: ");
            int base = sc.nextInt();
            
            int elementSize = getValue("Element Size: ");

            System.out.println("\n--------Upper Bounds--------");
            int[] upperBounds = new int[dimensions];
            for(int i = 0; i < dimensions; i++){
                System.out.print("Enter upper bounds at dimension " + i + ": ");
                upperBounds[i] = sc.nextInt();
            }

            System.out.println("\n--------Target Element--------");
            int[] targetElement = new int[dimensions];
            for(int i = 0; i < dimensions; i++){
                System.out.print("Enter target element at dimension " + i + ": ");
                targetElement[i] = sc.nextInt();
            }

            int totalResult = 0;

            for (int i = 0; i <= targetElement.length - 1; i++) {
                int upperBoundsProduct = 1;
                for(int j = i + 1; j <= upperBounds.length - 1; j++){
                    upperBoundsProduct *= upperBounds[j];
                }
                totalResult += targetElement[i] * upperBoundsProduct;
            }

            int output = base + totalResult * elementSize;

            System.out.println("\nAddress is: " + output);
    }

}
