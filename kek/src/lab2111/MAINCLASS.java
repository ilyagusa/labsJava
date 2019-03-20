package lab2111;

import java.io.BufferedReader;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MAINCLASS {

    public static void main(String[] args) throws FileNotFoundException, IOException, MyException {

        File inFile = new File("in.txt");

        Scanner in = new Scanner(inFile);

        BufferedReader reader = new BufferedReader(new FileReader("in.txt"));
        int lines = 0;
        while (reader.readLine() != null) {
            lines++;
        }
        System.out.println("Number of equations: " + lines);
        reader.close();
        String str;
        Straight mass[] = new Straight[lines];
        for (int i = 0; i < lines; i++) {
            try {
                str = in.nextLine();
                String s[] = str.split(" ");
                try {
                    mass[i] = new Straight(Double.parseDouble(s[0]), Double.parseDouble(s[1]), Double.parseDouble(s[2]));
                } catch (MyException e) {
                    System.out.println(e+" " + (i+1));
                    return;
                }
                System.out.println("Уравнение " + (i + 1) + " " + "Прямой " + mass[i]);

            } catch (ArrayIndexOutOfBoundsException e) {

                e.getMessage();
                System.err.println(e + " Не хватает коэффициентов данных для построения уравнения ");
                return;

            } catch (NumberFormatException e) {
                e.getMessage();
                System.err.println(e + "\nНеправильный формат вводимых коэффициентов  ");
                return;
            } catch (NoSuchElementException e) {
                e.getMessage();
                System.err.println(e + "\nЕсли в файле русские буквы то будет выдаваться вот это исключение!");
                return;
            }
        }

        Arrays.sort(mass, new ComparatorStraight());        //сортировка массива прямых с помощью компаратора
        LinkedList<Straight> parallel = new LinkedList<>();// создание списка для дальнешейго заполнения группами паралленльных прямых
        LinkedList<Straight> massiv;//список для создания отредактированного массива прямых
        massiv = new LinkedList<>();

        for (int i = 0; i < lines; i++) {
            massiv.add(mass[i]);
        }
        for (int i = 0; i < massiv.size(); i++) {//удаление не прямых
            if (massiv.get(i).getA() == 0 && massiv.get(i).getB() == 0) {
                massiv.remove(i);
            }
        }

        for (int i = 0; i < massiv.size() - 1; i++) {//удаление равных прямых
            if (massiv.get(i).coef1() == massiv.get(i + 1).coef1() && massiv.get(i).coef2() == massiv.get(i + 1).coef2()) {
                massiv.remove(i);
            }
        }
        for (int i = 0; i < massiv.size(); i++) {//удаление не прямых повторно
            if (massiv.get(i).getA() == 0 && massiv.get(i).getB() == 0) {
                massiv.remove(i);
            }
        }
        if (massiv.get(massiv.size() - 1).getA() == 0 && massiv.get(massiv.size() - 1).getB() == 0) {
            massiv.remove(massiv.size() - 1);

        }

        System.out.println("-----------------------------------------------");
        System.out.println("Отсортированный Массив Уравнений");

        System.out.println("-----------------------------------------------");
        for (int i = 0; i < massiv.size(); i++) {//вывод уравнений без равных прямых и "не прямых"
            System.out.println("Уравнение " + (i + 1) + " Прямой " + massiv.get(i));
        }
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");

        //выводим пары перпендикулярных прямых
        for (int j = 0; j < massiv.size(); j++) {
            for (int i = j + 1; i < massiv.size(); i++) {
                if (massiv.get(j).positionOfStraigthPerpedikular(massiv.get(i))) {
                    System.out.println("Прямая " + massiv.get(j) + " перпендикулярна прямой " + massiv.get(i) + "\n");
                }
            }
        }
        for (int j = 1; j < massiv.size(); j++) {
            {
                if (massiv.get(j - 1).positionOfStraigthParallel(massiv.get(j))) {//формирование спика из параллельных прямых 
                    parallel.add(massiv.get(j - 1));
                    parallel.add(massiv.get(j));
                }

            }

        }
        for (int i = 0; i < parallel.size() - 1; i++) {//удаление равных прямых
            if (parallel.get(i).coef1() == parallel.get(i + 1).coef1() && parallel.get(i).coef2() == parallel.get(i + 1).coef2()) {
                parallel.remove(i);
            }
        }
        System.out.println("-----------------------------------------------");

        System.out.println("Группа параллельных прямых");
        System.out.println(parallel.get(0));
        for (int i = 1; i < parallel.size(); i++) {
            if (parallel.get(i).coef1() != parallel.get(i - 1).coef1()) {
                System.out.println("Группа параллельных прямых");
            }
            System.out.println(parallel.get(i));

        }

        System.out.println("-----------------------------------------------");
        double x;
        double y;
        System.out.println("Введите координаты x и y");
        Scanner kek = new Scanner(System.in);
        try {
            x = kek.nextDouble();
            y = kek.nextDouble();
        } catch (InputMismatchException e) {
            System.err.println("Недопустимый символ");
            return;
        }
        int flag = 0;
        for (int i = 0; i < massiv.size(); i++) {
            if (massiv.get(i).point(x, y))//функция определяющая принадлежность точки прямой 
            {
                System.out.println("Точка " + "(" + x + "," + y + ")" + "принадлежит прямой " + massiv.get(i));
                flag = 1;
            }

        }
        if (flag == 0) {
            System.out.println("Нет прямых совпадающих с данной точкой");
        }
    }
}
