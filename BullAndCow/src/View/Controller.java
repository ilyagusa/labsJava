/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import javafx.stage.Stage;

/**
 *
 * @author Илья
 */
public class Controller {

    static View view;

    public static void SetScene(Stage primaryStage) {
        int mass[] = {0, 0, 0, 0};
        int mass1[] = {0, 0, 0, 0};
        int val[] = {0, 0, 0, 0};
        Number a = new Number(mass);
        Number b = new Number(mass1);
        Number tmp = new Number(val);
        view = new View(a, b, tmp, primaryStage);
    }

    public static boolean checkNumber(Number a) {
        boolean chek = false;
        if (a.getFirst() != a.getSecond()
                && (a.getFirst() != a.getThird()) && (a.getFirst() != a.getFourth())
                && (a.getSecond() != a.getThird()) && (a.getSecond() != a.getFourth()) && (a.getThird() != a.getFourth()) && a.getFirst() != 0) {
            chek = true;
        }
        return chek;
    }

    public static void resetBulAndCow(Number a, Number b) {
        a.reset();
        b.reset();
    }

    public static void rand(Number random) {
        int mem;
        LinkedList<Integer> num = new LinkedList();
        num.add(1);
        num.add(2);
        num.add(3);
        num.add(4);
        num.add(5);
        num.add(6);
        num.add(7);
        num.add(8);
        num.add(9);
        mem = (int) (Math.random() * 8);
        random.setFirst(num.get(mem));
        num.remove(mem);
        num.add(0);
        mem = (int) (Math.random() * 8);
        random.setSecond(num.get(mem));
        num.remove(mem);
        mem = (int) (Math.random() * 8);
        random.setThird(num.get(mem));
        num.remove(mem);
        mem = (int) (Math.random() * 8);
        random.setFourth(num.get(mem));
    }

}
