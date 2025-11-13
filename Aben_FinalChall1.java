import java.util.*;

public class Aben_FinalChall1 {
   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Array that stores the products
        String[] product = {
            "555 SARDINES AND TOMATO SAUCE",
            "ARGENTINA CORNED BEEF 260G",
            "BOSSING EXTRA CHEESEDOG REG 1KG",
            "HANSUNG LUNCHEON MEAT 3X340G SV",
            "KNORR SINIGANG MIX ISDA 11G",
            "KOMEYA 3Q BIHON 454G GOODFOR5",
            "LASAP SWEET CHILI SAUCE 330G",
            "PAPA BANANA KETCHUP BOTTLED",
            "PANCIT CANTON CHILIMANSI 80G",
            "VALLEY BREAD PULLMAN LOAF 500G"
        };
        
        //Array that stores the prices of products
        double[] price = new double[10];
        
        //Array that will store the quantity
        int[] quantity = new int[10];
        
        //Array that will store the total value of the remaining products
        double[] totalValue = new double[10];

        int currentQuantity;
        double currentPrice;

        System.out.println("\t\tWELCOME TO STOCK MANAGER\n");

        //Asks user to input quantity of each product
        for (int i = 0; i < product.length; i++) {
            System.out.println("Enter quantity for: " + product[i]);

            //Checks if user inputs a number
            try{
            currentQuantity = sc.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("Please type a number.\n");
                i--;
                sc.next();
                continue;
            }

            //Checks if user input a number equal to or more than 0
            if (currentQuantity<0) {
                System.out.println("Please enter a valid number.\n");
                i--;
                continue;
            }
            
            quantity[i] = currentQuantity;
            
            System.out.println("Enter price for: " + product[i]);
            //Checks if user inputs a number
            try{
            currentPrice = sc.nextDouble();
            }
            catch(InputMismatchException e){
                System.out.println("Please type a number.\n");
                i--;
                sc.next();
                continue;
            }

            //Checks if user input a number equal to or more than 0
            if (currentPrice<0) {
                System.out.println("Please enter a valid number.\n");
                i--;
                continue;
            }

            price[i] = currentPrice;
            //Calculates the total value and stores them
            totalValue[i] = quantity[i] * price[i];
        }

        //Prints results
        System.out.println("=====================================================================================");
        System.out.println("\tProduct\t\t\t\tPrice\t\tQuantity\tTotal Value");

        //Prints product information one by one
        for(int i = 0; i < product.length; i++){
            System.out.println(product[i]+"\t\tPHP "+price[i]+"\t"+quantity[i]+"\t\tPHP "+totalValue[i]);
        }

        System.out.println("=====================================================================================");
    }
}
