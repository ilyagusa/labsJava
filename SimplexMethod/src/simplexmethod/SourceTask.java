/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplexmethod;

import java.lang.reflect.Array;

/**
 *
 * @author Ilya
 */
public class SourceTask {

    private Fractions target;
    private FractionsList matrixEq;
    private int sizeX, sizeEq;
    private int x0[];

    public SourceTask(Fractions targetFunc, FractionsList matrixEq, int sizeEq, int sizeX) {
        this.target = targetFunc;
        this.matrixEq = matrixEq;
        this.sizeEq = sizeEq;
        this.sizeX = sizeX;
        this.x0 = new int[sizeX - 1];
        for (int i = 0; i < sizeX - 1; i++) {
            x0[i] = 0;
        }
    }

    public void setX0(int pos[]) {
        this.x0 = pos;
    }

    @Override
    public String toString() {
        return "Количество ограничений:" + this.sizeEq + "Количество переменныx:" + sizeX
                + "\ntargerfunc: " + this.target + "\nsimplex table: " + this.matrixEq + "\nОпорная точка" + this.x0[0];
    }
}
