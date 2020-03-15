/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplexmethod;

/**
 *
 * @author Ilya
 */
public class Fraction  {

    private int num;
    private int denom;

    public Fraction(int num, int denom) throws WrongNumException {
        if (num < 0 && denom < 0) {
            num = -1 * num;
            denom = -1 * denom;
        }
        if (denom < 0 && num >= 0) {
            denom = -1 * denom;
            num = -1 * num;
        }
        if (denom == 0) {
            throw new WrongNumException();
        }
        int gcd = Methods.gcdThing(num, denom);
        this.num = num / gcd;
        this.denom = denom / gcd;
    }

    public int getNum() {
        return num;
    }

    public int getDenom() {
        return denom;
    }

    //Сложение
    public Fraction add(Fraction a) throws WrongNumException{
        return new Fraction(this.num * a.denom + a.num * this.denom, this.denom * a.denom);
    }

    //Вычитание
    public Fraction sub(Fraction a) throws WrongNumException {
        return new Fraction(this.num * a.denom - a.num * this.denom, this.denom * a.denom);
    }

    //Умножение
    public Fraction multiply(Fraction a) throws WrongNumException {
        return new Fraction(this.num * a.num, this.denom * a.denom);
    }

    //Деление
    public Fraction divide(Fraction a) throws WrongNumException {
        return new Fraction(this.num * a.denom, this.denom * a.num);
    }

    @Override
    public String toString() {
        return "fraction = " + this.num + "/" + this.denom;
    }

}
