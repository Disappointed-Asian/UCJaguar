import java.util.*;

public class ArrayAddressCalculator{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        try{
            System.out.println("--------Address Calculator--------");
            System.out.print("Enter the number of Dimensions: ");
            int dimensions = sc.nextInt();

            System.out.print("Enter base or alpha: ");
            int base = sc.nextInt();

            System.out.print("Enter element size: ");
            int elementSize = sc.nextInt();

            System.out.println("\n--------Upper Bounds--------");
            int[] upperBounds = new int[dimensions];
            for(int i = 0; i < dimensions; i++){
                System.out.print("Enter upper bounds at index " + i + ": ");
                upperBounds[i] = sc.nextInt();
            }

            System.out.println("\n--------Target Element--------");
            int[] targetElement = new int[dimensions];
            for(int i = 0; i < dimensions; i++){
                System.out.print("Enter target element at index " + i + ": ");
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
        } catch(InputMismatchException e){
            System.out.println();
        }
    }

}