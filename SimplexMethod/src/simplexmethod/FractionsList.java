/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplexmethod;

import java.util.ArrayList;

/**
 *
 * @author Ilya
 */
public class FractionsList {

    ArrayList<Fractions> list;

    public FractionsList() {
        list = new ArrayList<>();
    }

    public void add(Fractions el) {
        list.add(el);
    }

    public void remove(int index) {
        list.remove(index);
    }

    public int size() {
        return list.size();
    }

    public Fractions getFractions(int index) {
        return list.get(index);
    }

    @Override

    public String toString() {
        return list.toString() + "";
    }
}
