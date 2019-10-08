/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.lang.reflect.Array;

/**
 *
 * @author Илья
 */
public class Number {

    private int[] num;
    private int bull;
    private int cow;

    public Number(int num[]) {
        this.num = num;
        bull = 0;
        cow = 0;
    }

    public Number() {
        this.num = null;
    }

    public int getFirst() {
        return this.num[0];
    }

    public int getSecond() {
        return this.num[1];
    }

    public int getThird() {
        return this.num[2];
    }

    public int getFourth() {
        return this.num[3];
    }

    public int getSize() {
        return this.num.length;
    }

    public int getBull() {
        return this.bull;
    }

    public int getCow() {
        return this.cow;
    }

    public void setFirst(int num) {
        this.num[0] = num;
    }

    public void setSecond(int num) {
        this.num[1] = num;
    }

    public void setThird(int num) {
        this.num[2] = num;
    }

    public void setFourth(int num) {
        this.num[3] = num;
    }

    public void setBull(int num) {
        this.bull = num;
    }

    public void setCow(int num) {
        this.cow = num;
    }
    
    public void reset(){
        this.cow=0;
        this.bull=0;
    }

    public void countBullAndCow(Number a) {
        reset();
        if (this.getFirst() == a.getSecond() || this.getFirst() == a.getThird() || this.getFirst() == a.getFourth()) {
            this.cow++;
        }
        if (this.getSecond() == a.getFirst() || this.getSecond() == a.getThird() || this.getSecond() == a.getFourth()) {
            this.cow++;
        }
        if (this.getThird() == a.getFirst() || this.getThird() == a.getSecond() || this.getThird() == a.getFourth()) {
            this.cow++;
        }
        if (this.getFourth() == a.getFirst() || this.getFourth() == a.getSecond() || this.getFourth() == a.getThird()) {
            this.cow++;
        }

        if (this.getFourth() == a.getFourth()) {
            this.bull++;
        }
        if (this.getFirst() == a.getFirst()) {
            this.bull++;
        }
        if (this.getSecond() == a.getSecond()) {
            this.bull++;
        }
        if (this.getThird() == a.getThird()) {
            this.bull++;
        }
    }

    @Override
    public String toString() {
        return "Ваше число " + num[0] + "" + num[1] + "" + num[2] + "" + num[3];
    }

}
