package myClass;
import myException.MyMatrException;

public class MainClass {

    public static void main(String[] args) 
    {
                                        
        try
        {
         Mass matrix= new Mass();
         matrix.Scann();
         matrix.ocnovnuslovie();
         matrix.Print();
        }
        catch(MyMatrException e)
        {
            System.out.println(e.getMessage());                         
        }
      

    }
    
}