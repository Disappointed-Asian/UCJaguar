import java.util.*;

public class Aben_FinalChall2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Cities
        String[] city = {
            "City of Baguio",
            "City of Justin",
            "City of Kyro"
        };
        //Days of the Week
        String[] day = {
            "Sunday",
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday"
        };
        //Temperature of each city in a week
        float[][] temperature = new float[3][7];

        //Average temperature of each city
        float[] averageTemp = new float[3];

        System.out.println("==========================================================");
        System.out.println("\t\tWEATHER DATA TRACKER");
        System.out.println("==========================================================");

        float userInput = 0;

        //Records temperature data for each city
        for (int cityIndex = 0; cityIndex < city.length; cityIndex++) {
            System.out.println("Recording Temperature in: " + city[cityIndex]);
            for (int tempIndex = 0; tempIndex < temperature[cityIndex].length; tempIndex++) {

                //Checks if user inputs correctly
                try{
                System.out.print(day[tempIndex] + ": \t");
                userInput = sc.nextFloat();}
                catch(InputMismatchException e){
                    System.out.println("Please type a number.");
                    tempIndex--;
                    sc.next();
                    continue;
                }

                //Stores temperature data on current city
                temperature[cityIndex][tempIndex] = userInput;
            }
            System.out.println("---------------------------------------------------------");
        }

        float calculatedTemp = 0;
        int index = 0;

        //Calculate temperature
        for (float cityNum[] : temperature) {
            for (float tempNum : cityNum) {
                calculatedTemp += tempNum;
            }
            averageTemp[index] = calculatedTemp / 7;
            index += 1;
            calculatedTemp = 0;
        }

        //Prints the results
        for (int cityIndex = 0; cityIndex < 3; cityIndex++) {
            System.out.println("\n==========================================================");
            System.out.println("\t\t" + city[cityIndex]);
            System.out.println("==========================================================");

            for (int tempIndex = 0; tempIndex < temperature[cityIndex].length; tempIndex++) {
                System.out.println(day[tempIndex] + ": \t" + temperature[cityIndex][tempIndex]);
            };

            float[] temps = temperature[cityIndex].clone();
            Arrays.sort(temps);

            System.out.println("Average Temperature: " + averageTemp[cityIndex]);
            System.out.println("Highest Temperature: " + temps[temps.length - 1]);
            System.out.println("Lowest Temperature: " + temps[0]);
        }
        System.out.println("\n---------------------------------------------------------");
    }
}
