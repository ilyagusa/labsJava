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
public class EmptyValueException extends Exception {

    public String s = "Этот параметр не может быть пустым";

    @Override
    public String toString() {
        return s;
    }

}
