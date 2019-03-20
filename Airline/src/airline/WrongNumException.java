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
public class WrongNumException extends Exception {

    public String s = "Этот параметр не может быть отрциательным ";

    @Override
    public String toString() {
        return s;
    }

}
