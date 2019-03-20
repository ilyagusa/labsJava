/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airline;

/**
 *
 * @author НИколай Соболев
 */
public class People {

    private String name;
    private String surname;

    public People(String name, String surname) throws EmptyValueException {
        this.name = name;
        this.surname = surname;
        if (name == null) {
            throw new EmptyValueException();
        }
        if (surname == null) {
            throw new EmptyValueException();
        }
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
