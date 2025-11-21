
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
        double[][] temperature = new double[3][7];
        //Average temperature of each city
        double[] averageTemp = new double[3];

        System.out.println("==========================================================");
        System.out.println("\t\tWEATHER DATA TRACKER");
        System.out.println("==========================================================");

        for (int a = 0; a < 3; a++) {
            System.out.println("Recording Temperature in: " + city[a]);
            for (int b = 0; b < 7; b++) {
                System.out.print(day[b] + ": \t");
                temperature[a][b] = sc.nextDouble();
            }
            System.out.println("---------------------------------------------------------");
        }

        double calculatedTemp = 0;
        int index = 0;

        //Calculate temperature
        for (double cityNum[] : temperature) {
            for (double tempNum : cityNum) {
                calculatedTemp += tempNum;
            }
            averageTemp[index] = calculatedTemp / 7;
            index += 1;
            calculatedTemp = 0;
        }

        for (int c = 0; c < 3; c++) {
            System.out.println("\n\n" + city[c]);
            for (int d = 0; d < 7; d++) {
                System.out.println(day[d] + ": " + temperature[c][d]);
            }
            System.out.println("Average Temoerature: " + averageTemp[c]);
            Arrays.sort(temperature[c]);
            System.out.println("Highest Temperature: " + temperature[c][6]);
        }
    }
}
