package car;

public abstract class Car {
    
    public int agecar;
    
    private final int  valuewheel=4;   
    
   public int maxspeed;
   
     public abstract void beep();
   
   public Car(int agecar, int maxspeed){
       this.maxspeed=maxspeed;
       this.agecar=agecar;
   }
   
   public int getSpeed(){
       return this.maxspeed;
   }
   
      public int getAge(){
      return this.agecar;
   }
   
      
      


 
}

public class main {
     public static void main(String[] args){
       Pickup Nissan= new Pickup(3000);
       System.out.println(Nissan);
    }
    
}
