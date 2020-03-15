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
public class Fractions {

    ArrayList<Fraction> data;

    public Fractions() {
        data = new ArrayList<>();
    }

    public void add(Fraction el) {
        data.add(el);
    }

    public void remove(int index) {
        data.remove(index);
    }

    public int size() {
        return data.size();
    }

    public Fraction getFraction(int index) {
        return data.get(index);
    }

    @Override

    public String toString() {
        return data.toString() + "";
    }

}
