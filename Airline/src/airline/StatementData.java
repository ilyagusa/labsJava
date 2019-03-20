/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airline;

import java.util.LinkedList;

/**
 *
 * @author НИколай Соболев
 */
public class StatementData {

    private LinkedList<Statement> Data;

    public StatementData() {
        Data = new LinkedList<>();
    }

    public void add(Statement el) {
        Data.add(el);
    }

    public void remove(int index) {
        Data.remove(index);
    }

    public int size() {
        return Data.size();
    }

    public Statement getEl(int index) {
        return Data.get(index);
    }

    @Override
    public String toString() {
        return Data.toString() + "";

    }

}
