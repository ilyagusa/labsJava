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
    private Fractions basis;
    //идексы базисных и свободных переменных
    private int position[];

    public SourceTask(Fractions targetFunc, FractionsList matrixEq) {
        this.target = targetFunc;
        this.matrixEq = matrixEq;
    }

    public FractionsList getMatrix() {
        return this.matrixEq;
    }

    public int sizeEq() {
        return matrixEq.size();
    }

    public int sizeX() {
        return target.size();
    }

    public void setX0(Fractions basis) {
        this.basis = basis;
        position = new int[this.basis.size()];
        int i = 0;
        for (int j = 0; j < this.basis.size(); j++) {
            if (this.basis.getEl(j).getNum() != 0) {
                position[i] = (j + 1);
                i++;
            }
        }
        for (int j = 0; j < this.basis.size(); j++) {
            if (this.basis.getEl(j).getNum() == 0) {
                position[i] = (j + 1);
                i++;
            }
        }
    }

    public int[] getPosition() {
        return this.position;
    }

    private String toStringX0() {
        String str = "(";
        for (int i = 0; i < this.basis.size(); i++) {
            str += "x" + (i + 1) + "=" + basis.getFraction(i) + " ";
        }
        return str + ")";
    }

    private String toStringPos() {
        String str = "[";
        for (int i = 0; i < this.basis.size(); i++) {
            str += this.position[i] + ",";
        }
        return str + "]";
    }

    public String[][] getSimplexTable() {
        int count = 0;
        int countCol = 1;
        int countRow = 1;
        String table[][] = new String[this.sizeEq() + 2][this.sizeX() - this.sizeEq() + 2];
        //0 0  элемент обозначающий итерацию симплекс метода!
        table[0][0] = "it=" + count;
        //Последний столбец 0 строки Бета
        table[0][this.sizeX() - this.sizeEq() + 1] = "B";
        count++;
        //строка с свободными иксами
        for (int j = this.sizeEq(); j < this.sizeX(); j++) {
            table[0][countCol] = "x" + this.position[j];
            table[this.sizeEq() + 1][countCol] = "x" + this.position[j];
            countCol++;
        }
        //столбец с базисными иксами
        for (int j = 0; j < this.sizeX() - this.sizeEq(); j++) {
            table[countRow][0] = "x" + this.position[j];
            countRow++;
        }
        countRow = 1;
        table[this.sizeEq() + 1][0] = "f0";
        //Заполнение значений при иксах
        for (int i = 0; i < this.sizeEq(); i++) {
            countCol = 1;
            for (int j = this.sizeEq(); j < this.sizeX(); j++) {

                table[countRow][countCol] = this.matrixEq.getFractions(i).getFraction(this.position[j] - 1).toString();
                countCol++;
            }
            countRow++;
        }
        //Заполнение Бет
        countRow = 1;
        for (int i = 0; i < this.sizeEq(); i++) {
            System.out.println(this.matrixEq.getFractions(i).getFraction(this.sizeX()).toString());
            table[i + 1][this.sizeX() - this.sizeEq() + 1] = this.matrixEq.getFractions(i).getFraction(this.sizeX()).toString();
        }
        return table;
    }

    @Override
    public String toString() {
        return "\ntargerfunc: " + this.target + "\nsimplex table: " + this.matrixEq + "\nОпорная точка" + toStringX0() + "\n pos = " + toStringPos();
    }
}
