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
public class Way {

    private String pointF;
    private String pointL;

    public Way(String pointF, String pointL) throws EmptyValueException {
        if (pointF == null || pointL == null) {
            throw new EmptyValueException();
        }
        this.pointF = pointF;
        this.pointL = pointL;

    }

    public String getF() {
        return pointF;
    }

    public String getL() {
        return pointL;
    }

    @Override
    public String toString() {
        return pointF + "->" + pointL;

    }

}
