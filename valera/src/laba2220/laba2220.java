
package laba2220;

import exceptions.BadIndexException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class laba2220 {

    public static void main(String[] args) throws FileNotFoundException, NegativeArraySizeException, BadIndexException {
        try {
            File inFile = new File("in.txt");

            Scanner in = new Scanner(inFile);

            int sizematrix = in.nextInt();
            if (sizematrix <= 1) {
                throw new NegativeArraySizeException();
            }

            System.out.println("sizematrix=" + sizematrix);

            //выделяем память и заполняем массив из файла чтобы далльше передать этот массив в констуруктор нашего класса!
            double massiv[][] = new double[sizematrix][sizematrix];
            for (int i = 0; i < sizematrix; i++) {
                for (int j = 0; j < sizematrix; j++) {
                    massiv[i][j] = in.nextDouble();
                }
            }
            Matrix N1 = new Matrix(massiv, sizematrix);

            
            System.out.println(N1);

            double determinant = N1.Det();

            System.out.println("Определитель равен :" + determinant + "\n");

            String squarematr = new String();
            for (int i = 0; i < sizematrix; i++) {
                for (int j = 0; j < sizematrix; j++) {
                    squarematr += N1.square()[i][j] + " | ";
                }
                squarematr += "\n";
            }
            System.out.println("\nКвадрат матрицы \n" + squarematr);

            String cubematr = new String();
            for (int i = 0; i < sizematrix; i++) {
                for (int j = 0; j < sizematrix; j++) {
                    cubematr += N1.cube()[i][j] + " | ";
                }
                cubematr += "\n";
            }
            System.out.println("\nКуб матрицы \n" + cubematr);

            String souzmatr = new String();
            for (int i = 0; i < sizematrix; i++) {
                for (int j = 0; j < sizematrix; j++) {
                    if (N1.Det() != 0) {
                        souzmatr += N1.souz()[i][j] * 1 / N1.Det() + "  | ";
                    } else {
                        souzmatr = "Отсутсвует";
                    }
                }
                souzmatr += "\n";
            }
            System.out.println("\nОбратная матрица \n" + souzmatr);

            System.out.println("\n Хэшкод:" + N1.hashCode());

        } 
        catch (NoSuchElementException e) {
            e.getMessage();
            System.err.println(e + "\nНе хватает элементов для создания объекта или введены буквы/символы!");
        } 
        catch (NegativeArraySizeException e) {
            e.getMessage();
            System.err.println(e + "\nНеправильный размер массива!");
        } 
        catch (FileNotFoundException | BadIndexException e) {
            e.getMessage();
            System.err.println(e);
        }
        
        
    }

}
