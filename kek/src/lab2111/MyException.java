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
public class MyException extends Exception{
    public int i;
    public String s="Ошибка в уравнении";
    public MyException(){
       
    }
        @Override
    public String toString(){
        return s;
    }
    @Override
    public String getMessage(){
        return this.toString();
    }
}
