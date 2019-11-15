/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdb;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ilyag
 */
public class Phone {

    private SimpleStringProperty type;
    private SimpleStringProperty number;
    private SimpleIntegerProperty id;

    public Phone(int id, String type_number, String number) {
        this.id = new SimpleIntegerProperty(id);
        this.type = new SimpleStringProperty(type_number);
        this.number = new SimpleStringProperty(number);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNumber() {
        return number.get();
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    @Override

    public String toString() {
        return "" + this.type.get() + this.number.get();
    }

}
