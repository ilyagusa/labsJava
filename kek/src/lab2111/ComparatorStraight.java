/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2111;

import java.util.Comparator;

/**
 *
 * @author НИколай Соболев
 */
public class ComparatorStraight implements Comparator<Straight> {

    @Override
    public int compare(Straight a, Straight b) {

         double flag = a.coef1() - b.coef1();
        if (flag == 0) {
            flag = a.coef2() - b.coef2();
        }

        if (flag > 0) {
            return 1;
        }
        if (flag < 0) {
            return -1;
        }
        return 0;

    }
}
