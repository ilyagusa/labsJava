/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gauss;

import java.text.DecimalFormat;

/**
 *
 * @author Ilya
 */
public class Matrix {

    private double[][] matrixEquations;
    private double[] matrixValues;
    private double[] result;
    private int sizematr;

    public Matrix(double[][] double_array, double[] array, int size) throws NegativeArraySizeException {
        if (size <= 1) {
            throw new NegativeArraySizeException();
        }
        this.matrixEquations = new double[size][size];
        this.matrixValues = new double[size];
        this.result = new double[size];
        this.matrixEquations = double_array;
        this.matrixValues = array;
        this.sizematr = size;
    }

    //прямой ход приведения системы уравнений к треугольному виду
    public void Forward() {
        int k = 0;
        int index = 0;
        double max;
        while (k < sizematr) {
            index = k;
            max = Math.abs(this.matrixEquations[k][k]);
            //find max elm in k column
            for (int i = k + 1; i < sizematr; i++) {
                if (Math.abs(this.matrixEquations[i][k]) > max) {
                    max = Math.abs(this.matrixEquations[i][k]);
                    index = i;
                }
            }
            if(max<0.000001){
                System.out.println("Невозможно получить решение из-за 0 столбца");
                return;
            }
            //свап уравнений
            swapRow(index, k);
            //нормализация уравнений относительно
            normalization(k);
            k++;
        }
        reverse();
    }

    //Обратный ход , подстановка 
    private void reverse() {
        for (int i = sizematr - 1; i >= 0; i--) {
            result[i] = this.matrixValues[i];
            for (int j = 0; j < i; j++) {
                this.matrixValues[j] = this.matrixValues[j] - this.matrixEquations[j][i] * result[i];
            }
        }
    }

    private void swapRow(int index, int k) {
        double tmp;
        for (int j = 0; j < sizematr; j++) {
            tmp = this.matrixEquations[k][j];
            this.matrixEquations[k][j] = this.matrixEquations[index][j];
            this.matrixEquations[index][j] = tmp;
        }
        tmp = this.matrixValues[index];
        this.matrixValues[index] = this.matrixValues[k];
        this.matrixValues[k] = tmp;
    }

    private void normalization(int k) {
        double eps = 0.0000001;
        for (int i = k; i < sizematr; i++) {
            double divide = this.matrixEquations[i][k];
            if (Math.abs(divide) < eps) {
                continue; // для нулевого коэффициента пропустить
            }
            this.matrixValues[i] /= divide;
            for (int j = 0; j < sizematr; j++) {
                this.matrixEquations[i][j] /= divide;
            }
            if (i == k) {
                continue;
            }
            for (int j = 0; j < sizematr; j++) {
                this.matrixEquations[i][j] = this.matrixEquations[i][j] - this.matrixEquations[k][j];
            }
            this.matrixValues[i] = this.matrixValues[i] - this.matrixValues[k];
        }
    }

    public String getResult() {
        String res = "";
        for (int i = 0; i < sizematr; i++) {
            res += "x" + (i + 1) + " = " + (result[i]) + "\n";
        }
        return res;
    }

    @Override

    public String toString() {
        {
            String str = new String();
            for (int i = 0; i < sizematr; i++) {
                for (int j = 0; j < sizematr; j++) {
                    if (j != sizematr - 1) {
                        str += (this.matrixEquations[i][j]) + "*x" + (j + 1) + "+";
                    } else {
                        str += (this.matrixEquations[i][j]) + "*x" + (j + 1);
                    }
                }
                str += "=" + (this.matrixValues[i]) + "\n";
            }
            return str;
        }

    }

}
