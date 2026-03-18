class Animal{
    protected double height;
    protected double weight;
    
    public Animal(double height, double weight){
        this.height = height;
        this.weight = weight;
    }
}

class Mammal extends Animal{
    protected String hair_color;
    protected int no_of_legs;
    protected boolean can_swim;
    
    public Mammal(double height, double weight, String hair_color, int no_of_legs, boolean can_swim){
        super(height, weight);
        this.hair_color = hair_color;
        this.no_of_legs = no_of_legs;
        this.can_swim = can_swim;
    }
}

class Carnivore extends Mammal{
    protected String genus;
    
    public Carnivore(double height, double weight, String hair_color, int no_of_legs, boolean can_swim, String genus){
        super(height, weight, hair_color, no_of_legs, can_swim);
        this.genus = genus;
    }
}
