/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airline;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author НИколай Соболев
 */
public class InputInfo {
    
    static StatementData SD = new StatementData();
    static FlightData FD = new FlightData();
    
    public static void readFile() {
        File in = new File("in.txt");
        File in1 = new File("in1.txt");
        Scanner readFile = null;
        Scanner readFile1 = null;
        try {
            readFile = new Scanner(in);
            readFile1 = new Scanner(in1);
            Command.addS(SD, readFile);
            Command.addF(FD, readFile1);
            
        } catch (FileNotFoundException ex) {
            System.out.println("Нет файла");
        } finally {
            if (readFile != null && readFile1 != null) {
                readFile.close();
                readFile1.close();
            }
        }
        Command.printFD(FD);
        System.out.println("--------------------------------");
        Command.printSD(SD);
    }
    
    public static void Commands() {
        String command;
        
        System.out.println("Выберите команду");
        while (true) {
            try {
                System.out.print("> ");
                Scanner sc = new Scanner(System.in);
                if (!sc.hasNextLine()) {
                    break;
                }
                
                command = sc.nextLine();
                
                switch (command) {
                    case "addF":
                        Command.addFlight(sc, "Добавление рейса", FD);
                        break;
                    case "addS":
                        Command.addStatemenet(sc, "Добавление заявки", SD);
                        break;
                    case "removeF":
                        Command.removeF(FD, sc);
                        break;
                    case "PrFD":
                        Command.printFD(FD);
                        break;
                    case "PrSD":
                        Command.printSD(SD);
                        break;
                    case "RBD":
                        Command.removeByDate(SD, sc);
                        break;
                    case "PrSDBYDT":
                        Command.printSDDateandTime(sc, SD);
                        break;
                    case "PrFDW":
                        Command.printFDWay(sc, FD);
                        break;
                    case "Search":
                        Command.Search(SD, FD);
                        break;
                    case "exit":
                        return;
                    default:
                        System.out.format("Команда %s не распознана,повторите попытку\n", command);
                        break;
                }
            } catch (EmptyValueException e) {
                System.out.println(e);
                return;
            }
            
        }
    }
    
    public static void helpCommands() {
        System.out.println("--------------------------------");
        System.out.println("Команда addF - добавить рейс");
        System.out.println("Команда addS - добавить заявку");
        System.out.println("Команда removeF - удалить рейс");
        System.out.println("Команда PrFD (Print FD) - распечатать список рейсов");
        System.out.println("Команда PrSD (Print SD) - распечатать список заявок0");
        System.out.println("Команда RBD (remove by date) - удалить все заявки по дате");
        System.out.println("Команда PrSDBYDT (Print SD by date and time) - распечатать список заявок по дате и времени");
        System.out.println("Команда PrFDW (Print FD by way) - распечатать список рейсов по введеному пути");
        System.out.println("Команда Search - поиск рейсов для всех заявок");
        System.out.println("Команда exit - завершить работу программы");
        
    }
    
}
