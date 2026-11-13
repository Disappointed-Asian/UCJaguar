import java.util.*;

public class UnitConverterAben{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Converter converter = new Converter();

        while(true){
            System.out.println("================================");
            System.out.println("1 kilometers --> miles");
            System.out.println("2 miles --> kilometers");
            System.out.println("3 celsius --> fahrenheit");
            System.out.println("4 fahrenheit --> celsius");
            System.out.println("5 dollars --> pesos");
            System.out.println("6 pesos --> dollars");
            System.out.println("================================");

            System.out.println("Choose conversion option");
            String userInput = sc.nextLine();
            boolean isValidInput = true;

            //Checks user input validity
            String[] inputValue = {"1", "2", "3", "4", "5", "6"};
            for(String value: inputValue){
                if(userInput.equalsIgnoreCase(value)){
                    isValidInput = true;
                    break;
                }
                isValidInput = false;
            }

            if(!isValidInput){
                continue;
            }

            //Converter
            System.out.println("Enter value:");
            double value = sc.nextDouble();
            switch(userInput){
                case "1":
                    System.out.println("> " + value + " kilometeres");
                    converter.ConvertKilometersToMiles(value);
                    System.out.printf("= %.2f miles%n", converter.getMiles());
                    break;
                case "2":
                    System.out.println("> " + value + " miles");
                    converter.ConvertMilesToKilometers(value);
                    System.out.printf("= %.2f kilometers%n", converter.getKilometers());
                    break;
                case "3":
                    System.out.println("> " + value + " celsius");
                    converter.ConvertCelsiusToFahrenheit(value);
                    System.out.printf("= %.2f fahrenheit%n", converter.getFahrenheit());
                    break;
                case "4":
                    System.out.println("> " + value + " fahrenheit");
                    converter.ConvertFahrenheitToCelsius(value);
                    System.out.printf("=  %.2f celcius%n", converter.getCelsius());
                    break;
                case "5":
                    System.out.println("> " + value + " US dollars");
                    converter.ConvertUSDollarsToPhilippinePesos(value);
                    System.out.printf("=  %.2f pesos%n", converter.getPesos());
                    break;
                case "6":
                    System.out.println("> " + value + " Philippine pesos");
                    converter.ConvertPhilippinePesosToUSDollars(value);
                    System.out.printf("=  %.2f dollars%n", converter.getDollars());
                    break;
                default:
                    System.out.println("Not an option.");
                    break;
            }

            //Asks user if they want to continue or end the program
            String confirm = "";
            sc.nextLine();
            while(true){
                System.out.println("\nEnter another input? (Y/N): ");
                confirm = sc.nextLine();
                if(confirm.equalsIgnoreCase("Y")||confirm.equalsIgnoreCase("N")){
                    break;
                }
            }
            
            if(confirm.equalsIgnoreCase("N")){
                System.out.println("--------PROGRAM EXITED--------");
                break;
            }
        }
    }
}

class Converter{
    double kilometers;
    double miles;
    double celsius;
    double fahrenheit;
    double usDollars;
    double philippinePesos;

    void setKilometers(double kilometers){
        this.kilometers = kilometers;
    }

    void setMiles(double miles){
        this.miles = miles;
    }

    void setCelsius(double celsius){
        this.celsius = celsius;
    }

    void setFahrenheit(double fahrenheit){
        this.fahrenheit = fahrenheit;
    }

    void setDollars(double dollars){
        this.usDollars = dollars;
    }

    void setPesos(double pesos){
        this.philippinePesos = pesos;
    }

    double getKilometers(){
        return this.kilometers;
    }

    double getMiles(){
        return this.miles;
    }

    double getCelsius(){
        return this.celsius;
    }

    double getFahrenheit(){
        return this.fahrenheit;
    }

    double getDollars(){
        return this.usDollars;
    }
    
    double getPesos(){
        return this.philippinePesos;
    }

    void ConvertKilometersToMiles(double kilometers){
        setMiles(kilometers * 0.621371);
    }

    void ConvertMilesToKilometers(double miles){
        setKilometers(miles * 1.60934);
    }

    void ConvertCelsiusToFahrenheit(double celsius){
        setFahrenheit((celsius * 9 / 5) + 32);
    }

    void ConvertFahrenheitToCelsius(double fahrenheit){
        setCelsius((fahrenheit - 32) * 5 / 9);
    }

    void ConvertUSDollarsToPhilippinePesos(double dollars){
        setPesos(dollars * 56.21);
    }

    void ConvertPhilippinePesosToUSDollars(double pesos){
        setDollars(pesos / 56.21);
    }
}

