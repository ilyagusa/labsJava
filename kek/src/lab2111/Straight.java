/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2111;

/**
 *
 * @author НИколай Соболев
 */
public class Straight {

    public double[] mass;

     public Straight(double a, double b, double c) throws MyException
     {
        mass = new double[3];
        mass[0] = a;
        mass[1] = b;
        mass[2] = c;
        if (a==0 && b==0){
            throw new MyException();
        }
    }

    public void setA(double a) {
        mass[0] = a;
    }

    public void setB(double b) {
        mass[1] = b;
    }

    public void setC(double c) {
        mass[2] = c;
    }

    public double getA() {
        return mass[0];
    }

    public double getB() {
        return mass[1];
    }
// коэф для определения параллельности и перепендикулярности (если coef1=-1/coef1*  - перепендик // если coef1=coef1*- параллельны) 

    public double coef1() {
        return (-1) * mass[0] / mass[1];
    }

    public double coef2() {
        return (-1) * mass[2] / mass[1];
    }

    //метод для определения перепендик 
    public boolean positionOfStraigthPerpedikular(Straight a) {

        return (coef1() == (-1 / a.coef1()));

    }

    public boolean positionOfStraigthParallel(Straight a) {
        return coef1() == a.coef1() && coef2() != a.coef2();
    }

    @Override
    public String toString() {
        String str;

        if (mass[1] != 0) {
            if (coef2() < 0) {
                str = "y=" + coef1() + "*x" + "" + +coef2();
            } else if (coef2() > 0) {
                str = "y=" + coef1() + "*x" + "+" + coef2();
            } else {
                str = "y=" + coef1() + "*x";
            }
        } else {
            str = "x=" + (-1) * mass[2] / mass[0];
        }
        if ((mass[0] == 0 && mass[1] == 0) || (mass[0] == 0 && mass[1] == 0 && mass[2] == 0)) {
            str = "Это не прямая";
        }

        return str;

    }

    public boolean point(double x, double y) {
        return mass[0] * x + mass[1] * y + mass[2] == 0;
    }

}
