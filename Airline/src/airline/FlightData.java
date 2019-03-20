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
public class FlightData {

    private LinkedList<Flight> FlightDB;

    public FlightData() {
        FlightDB = new LinkedList<>();
    }

    public void add(Flight el) {
        FlightDB.add(el);
    }

    public Flight getEl(int index) {
        return FlightDB.get(index);
    }

    public void remove(int index) {
        FlightDB.remove(index);
    }

    public int size() {
        return FlightDB.size();
    }

    @Override
    public String toString() {
        return FlightDB.toString() + " ";

    }

}
