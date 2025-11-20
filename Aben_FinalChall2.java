import java.util.*;

public class Aben_FinalChall2{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] city = {
            "City of Baguio",
            "City of Justin",
            "City of Kyro"
        };
        String[] day = {
            "Sunday",
            "Monday",
            "Tueday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday"
        };
        double[][] temperature = new double[3][7];
        double[] averageTemp = new double[3];

        for(int a = 0; a < 3; a++){
            System.out.println("Temperature on "+city[a]);
            for(int b = 0; b<7;b++){
                System.out.println(day[b]);
                temperature[a][b] = sc.nextDouble();
            }
        }

        double calculatedTemp = 0;
        int index = 0;

        //Calculate temperature
        for(double cityNum[]:temperature){
            for(double tempNum:cityNum){
                calculatedTemp += tempNum;
            }
            averageTemp[index]=calculatedTemp/7;
            Arrays.sort(temperature[index]);
            index+=1;
            calculatedTemp = 0;
        }
    }
}
