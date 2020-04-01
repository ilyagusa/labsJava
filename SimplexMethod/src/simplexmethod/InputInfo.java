/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplexmethod;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.in;
import java.util.Scanner;
import javafx.scene.control.TextField;

/**
 *
 * @author Ilya
 */
public class InputInfo {

    static SourceTask sTable;
    static FractionsList flEq;

    public static void readFile(int sizeEq, int sizeX) throws WrongNumException {  
        File in = new File("in.txt");
        flEq  = new FractionsList();
        try (Scanner sc = new Scanner(in)) {
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                String[] strParse = str.split(" ");
                Fractions frs = new Fractions();
                for (int i = 0; i < strParse.length; i++) {
                    Fraction B = Methods.check(strParse[i]);
                    frs.add(B);
                }
                flEq.add(frs);
            }
            Fractions target = flEq.getFractions(0);
            flEq.remove(0);
            sTable = new SourceTask(target, flEq);
        } catch (FileNotFoundException ex) {
            System.out.println("Нет файла");
        } catch (WrongNumException ex) {
            System.out.println("Неправильный формат числа");
        } catch (NumberFormatException ex) {
            System.out.println("Неправильный формат ввода");
        }
    }

    public static void readInput(TextField[][] a, int sizeEq, int sizeX) throws IOException, WrongNumException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("in.txt")));
        for (int j = 0; j < sizeX; j++) {
            writer.write(a[0][j].getText());
            writer.write(" ");
        }
        for (int i = 1; i < sizeEq + 1; i++) {
            writer.write("\r\n");
            for (int j = 0; j < sizeX + 1; j++) {
                writer.write((a[i][j]).getText());
                writer.write(" ");
            }
        }
        writer.flush();
        readFile(sizeEq, sizeX);
    }

}
