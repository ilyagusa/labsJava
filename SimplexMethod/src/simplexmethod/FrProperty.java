/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplexmethod;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Ilya
 */
public class FrProperty {

    private String x[];

    public FrProperty(String x[]) {
        this.x = x;

    }

    public String getElm(int i) {
        return x[i];
    }

    public void setType(String str, int i) {
        this.x[i] = str;
    }

    public String toString(int i) {
        return "" + this.x[i];
    }

}
