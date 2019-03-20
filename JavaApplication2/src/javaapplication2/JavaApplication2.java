package javaapplication2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@FunctionalInterface
interface Calc{
    
    int test (int N1, int N2);
}

public class JavaApplication2 {

   
    public static void main(String[] args) {
        

        Calc CountDivide = (int a, int b)->{
            int count=0;
            while (a>1){
                if(a%b==0){
                count ++;
                a=a/b;
                }
                else break;
            }
            return count;
        } ;
    
        
        
        Calc square = (int a, int b)->{
            return a*b;
        };
        
        
        Calc NOD = (int a, int b)->{
          while (a != 0 && b != 0){
              if (a>b)
               a %= b;
              else 
                  b %= a;
            }  
          return (a+b);
        };
        
        System.out.println("Square> " + square.test(5, 8));
        System.out.println("CountDivide> " + CountDivide.test(20, 2));
        System.out.println("NOD> " + NOD.test(30, 18));
        
        ArrayList<Integer> array = new ArrayList();
        
        array.add(26);
        array.add(4);
        array.add(6);
        array.add(12);
        array.add(13);
        array.add(7);
         
        System.out.print(array.stream().filter(p ->(p%13)==0).collect(Collectors.toList()));
        System.out.println("");
        
        String data = "lolz adwi kandfokwhfo kjnz ";
        Stream<String> streamStr = Arrays.stream(data.split("[, ]+"));
        System.out.print("Count word ends on 'Z' = ");
        long r = streamStr.filter(s -> s.endsWith("z")).count();
        System.out.println(r);
    }
    
}
