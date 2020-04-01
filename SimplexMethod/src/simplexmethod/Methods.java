/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplexmethod;

import java.math.BigInteger;

/**
 *
 * @author Ilya
 */
public class Methods {

    public static void me() {
        System.out.println(InputInfo.sTable);
    }

    public static int gcdThing(int a, int b) {
        BigInteger b1 = BigInteger.valueOf(a);
        BigInteger b2 = BigInteger.valueOf(b);
        BigInteger gcd = b1.gcd(b2);
        return gcd.intValue();
    }

    public static Fraction check(String str) throws WrongNumException {
        String strArray[];
        {
            if (str.contains("/")) {
                strArray = str.split("/");
                return new Fraction(Integer.parseInt(strArray[0]), Integer.parseInt(strArray[1]));
            } else {
                return new Fraction(Integer.parseInt(str), 1);
            }
        }

    }

    public static boolean checkInput() {
        return true;
    }

//    public static Fraction add(Fraction a, Fraction b) {
//        return new Fraction(a.getNum + b.getNum, a.getDenom + b.getDenom);
//    }
//
//    public static Fraction sub(Fraction a, Fraction b) {
//        return new Fraction(a.getNum - b.getNum, a.getDenom - b.getDenom);
//    }
//
//    public static Fraction multiply(Fraction a, Fraction b) {
//        return new Fraction(a.getNum * b.getNum, a.getDenom * b.getDenom);
//    }
//
//    public static Fraction divide(Fraction a, Fraction b) {
//        return new Fraction(a.getNum * b.getDenom, a.getDenom * b.getNum);
//    }
}
