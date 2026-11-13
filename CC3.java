abstract class Animal{
    String species;
    
    public abstract void makeNoise(); //Abstract method

    public void eat(){ //Concrete method
        System.out.println("This animal is eating.");
    }
}

class Dog extends Animal{
    @Override
    public void makeNoise(){
        System.out.println("Bark!");
    }
}

public class CC3{
    
}

