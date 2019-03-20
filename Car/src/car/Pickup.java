
package car;


public class Pickup extends Car{
    
    public int weight;
    
    public Pickup(int weight){
        super(2 , 150);
        this.weight=weight;
    }
    
    @Override
    public void beep(){
        System.out.println("BEEP");
    }

@Override
public String toString(){
    return  "Ves="  + weight + getAge() + getSpeed();
}
    
}
