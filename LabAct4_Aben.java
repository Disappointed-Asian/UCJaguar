public class LabAct4_Aben {
    final static int MAX_SPEED = 100;

    public static void main(String[] args){
        System.out.println("\n================================");
        Car v1 = CarType.SEDAN.createCar();
        Car v2 = CarType.SUV.createCar();
        Car v3 = CarType.TRUCK.createCar();

        //Display all car details
        Car[] cars = {v1, v2, v3};
        for (Car car : cars) {
            car.displayCarDetails();
        }

        System.out.println("----------------------------------");
        //Changing speeds for each vehicle
        v1.changeSpeed(50, MAX_SPEED);
        v2.changeSpeed(100, MAX_SPEED);
        v3.changeSpeed(20, MAX_SPEED);

        //For speed less than 0
        v3.changeSpeed(-30, MAX_SPEED);

        //For speed over the max speed 
        v1.changeSpeed(50, MAX_SPEED);
        v1.changeSpeed(50, MAX_SPEED);
        v1.changeSpeed(-20, MAX_SPEED);
        System.out.println("----------------------------------");
    }
}

class Car{
    private String brand;
    private int speed;
    private String carType;

    Car(String brand, int speed, String carType){
        this.brand = brand;
        this.speed = speed;
        this.carType = carType;
    }

    String getBrand(){
        return this.brand;
    }
    int getSpeed(){
        return this.speed;
    }
    String getCarType(){
        return this.carType;
    }

    void setSpeed(int speed){
        this.speed = speed;
    }

    void increaseSpeed(int speed){
        this.speed += speed;
    }

    public void displayCarDetails(){
        System.out.println("Car Brand: " + this.getBrand());
        System.out.println("Current Speed: " + this.getSpeed());
    }

    protected void changeSpeed(int speedChange, int maxSpeed){
        System.out.println("SPPED CHANGE APPLIED TO " + this.getCarType() + "!");
        if(this.getSpeed() + speedChange > maxSpeed){
            System.out.println("Reached the speed limit!\n");
            return;
        } else if(this.speed + speedChange < 0){
            this.setSpeed(0);
            System.out.println("Speed set to 0km/hr. Vehicle Stopped!\n");
            return;
        }

        this.increaseSpeed(speedChange);
        System.out.println(this.getBrand() + " accelerated. New Speed: " + this.getSpeed() + "km/h\n");
    }
}

enum CarType{
    SEDAN("Toyota", 0),
    SUV("Honda", 0),
    TRUCK("Mitsubishi", 0);

    String brand;
    int speed;

    CarType(String brand, int speed){
        this.brand = brand;
        this.speed = speed;
    }

    public Car createCar(){
        return new Car(this.brand, this.speed, this.name());
    }
}