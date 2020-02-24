package gauss;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Gauss {

    public static void main(String[] args) throws FileNotFoundException {
        File inFile = new File("in.txt");
        Scanner in = new Scanner(inFile);
        int sizematrix = in.nextInt();
        double massivEq[][] = new double[sizematrix][sizematrix];
        double massivVal[] = new double[sizematrix];
        double mass[] = new double[sizematrix];
        if (sizematrix <= 1) {
            throw new NegativeArraySizeException();
        }
        for (int i = 0; i < sizematrix; i++) {
            for (int j = 0; j < sizematrix; j++) {
                massivEq[i][j] = in.nextDouble();
            }
            massivVal[i] = in.nextDouble();
        }
        Matrix N1 = new Matrix(massivEq, massivVal, sizematrix);
        System.out.println("Исходная система уравнений: \n" + N1);
        N1.Forward();
        System.out.println("Система уравнений приведенная к треугольной форме: \n" + N1);
        System.out.println("Ответ:\n" + N1.getResult());
    }

}
