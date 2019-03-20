/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airline;

import java.util.LinkedList;
import java.util.Scanner;

@FunctionalInterface

interface need<R> {
    R test(Scanner in, String comment);
}

@FunctionalInterface

interface equals {

    boolean eq(Statement SDB, Flight FDB);
}

public class Command {

    int countFD = 0;
    int countSD = 0;

    private static need<Integer> needInt = (Scanner in, String comment) -> {
        System.out.println(comment);
        int input = 0;
        while (true) {
            try {
                input = Integer.valueOf(in.nextLine());

                if (input < 0) {
                    throw new WrongNumException();
                }
                break;
            } catch (WrongNumException e) {
                e.getMessage();
                System.out.println(e + "\nповторите попытку");
            } catch (NumberFormatException e) {
                e.getMessage();
                System.out.println(e + "\nНеправильный формат ввода,повторите попытку");
            }
        }

        return input;
    };

    private static need<String> needString = (Scanner in, String comment) -> {
        System.out.println(comment);
        String input = null;
        while (true) {
            try {
                input = in.nextLine();
                if (input.isEmpty()) {
                    throw new EmptyValueException();
                }
                break;
            } catch (EmptyValueException e) {
                e.getMessage();
                System.out.println(e + "\nповторите попытку");
            } catch (NumberFormatException e) {
                e.getMessage();
                System.out.println(e + "\nНеправильный формат вводa, повторите попытку");
            }
        }

        return input;
    };

    private static need<Way> needWay = (Scanner in, String comment) -> {
        System.out.println(comment);
        Way a = null;
        try {
            String start = needString.test(in, "Введите пункт отправки");
            String finish = needString.test(in, "Введите пункт назначения");
            a = new Way(start, finish);
        } catch (EmptyValueException e) {
            e.getMessage();
            System.out.println(e);
        } catch (NumberFormatException e) {
            e.getMessage();
            System.out.println(e + "\n Неправильный формат ввода");
        }
        return a;

    };

    private static need<Date> needDate = (Scanner in, String comment) -> {
        System.out.println(comment);
        Date a = null;
        while (true) {
            try {
                int year = needInt.test(in, "Введите год\n");
                int month = needInt.test(in, "Введите месяц\n");
                int day = needInt.test(in, "Введите день\n");
                int hour = needInt.test(in, "Введите час\n");
                int minute = needInt.test(in, "Введите минуты\n");
                if (hour > 24 || minute > 60 || day > 31 || month > 12) {
                    throw new WrongNumException();
                }

                a = new Date(hour, minute, day, month, year);
                break;
            } catch (WrongNumException e) {

                System.out.println("Один из параметров не может быть больше чем задумано человечеством" + "\nповторите попытку");
            } catch (NumberFormatException e) {
                e.getMessage();
                System.out.println(e + "\nповотрите попытку");
            }
        }
        return a;
    };

    private static need<People> needPeople = (Scanner in, String comment) -> {
        System.out.println(comment);
        People a = null;
        try {
            String name = needString.test(in, "Введите имя");
            String surname = needString.test(in, "Введите фамилию");

            a = new People(name, surname);
        } catch (EmptyValueException | NumberFormatException e) {
            System.out.println(e);
        }
        return a;
    };

    public static void addStatemenet(Scanner in, String comment, StatementData SDB) {
        System.out.println(comment);
        Way way = needWay.test(in, "");
        People people = needPeople.test(in, "");
        Date date = needDate.test(in, "");
        SDB.add(new Statement(way, date, people));
    }

    public static void addFlight(Scanner in, String comment, FlightData FDB) throws EmptyValueException {
        System.out.println(comment);
        Way way = needWay.test(in, "");
        int price = needInt.test(in, "Введите цену за билет");
        int number = needInt.test(in, "Введите номер рейса");
        String type = needString.test(in, "Введите тип самолёта");
        Date date = needDate.test(in, "");
        FDB.add(new Flight(way, date, number, price, type));
    }

    public static void printFD(FlightData FDB) {
        int i;
        for (i = 0; i < FDB.size(); i++) {
            System.out.println("Рейс №" + i + "\n");
            System.out.println(FDB.getEl(i));
        }
    }

    public static void printSD(StatementData SDB) {
        int i;
        for (i = 0; i < SDB.size(); i++) {
            System.out.println("Заявка №" + i + "\n");
            System.out.println(SDB.getEl(i).toString());
        }
    }

    public static void removeByDate(StatementData SDB, Scanner in) {
        System.out.println("Введите дату по которой удалятся все заявки");
        int year;
        int month;
        int day;
        while (true) {
            try {
                day = needInt.test(in, "Введите день");
                month = needInt.test(in, "Введите месяц");
                year = needInt.test(in, "Введите год");
                if (day > 31 || month > 12 || year < 1940) {
                    throw new WrongNumException();
                }
                break;
            } catch (WrongNumException e) {
                System.out.println("Один из параметров не может быть больше чем задумано человечеством" + "Повторите попытку");
            }
        }
        for (int i = 0; i < SDB.size(); i++) {
            if (SDB.getEl(i).getDate().getDay() == day && SDB.getEl(i).getDate().getYear() == year && SDB.getEl(i).getDate().getMonth() == month) {
                SDB.remove(i);
            }
        }
    }

    public static void printSDDateandTime(Scanner in, StatementData SDB) {
        Date date = needDate.test(in, "Введите дату по которой выведутся заявки");
        for (int i = 0; i < SDB.size(); i++) {
            if (SDB.getEl(i).getDate().getYear() == date.getYear() && SDB.getEl(i).getDate().getMonth() == date.getMonth() && SDB.getEl(i).getDate().getDay() == date.getDay() && SDB.getEl(i).getDate().getHour() == date.getHour() && SDB.getEl(i).getDate().getMinute() == date.getMinute()) {
                System.out.println(SDB.getEl(i));
            }
        }
    }

    public static void printFDWay(Scanner in, FlightData FDB) {
        System.out.println("Введите путь по которому выведутся рейсы\n");
        Way way = needWay.test(in, "");
        for (int i = 0; i < FDB.size(); i++) {
            if (FDB.getEl(i).getWay().getF().equals(way.getF()) && FDB.getEl(i).getWay().getL().equals(way.getL())) {
                System.out.println(FDB.getEl(i));
            }
        }
    }

    public static void removeF(FlightData FDB, Scanner in) throws IndexOutOfBoundsException {
        int index;
        while (true) {
            try {
                index = needInt.test(in, "Введите индекс рейса которую хотите удалить");
                if (index > (FDB.size() - 1)) {
                    throw new IndexOutOfBoundsException();
                }
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e + "\nПовторите попытку");
            }

        }
        FDB.remove(index);
        System.out.println("Рейс №" + index + " удалён");
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static equals eqDate = (Statement SD, Flight FD) -> {
        boolean a;

        a = SD.getDate().getYear() == FD.getDate().getYear() && SD.getDate().getMonth() == FD.getDate().getMonth() && SD.getDate().getDay() == FD.getDate().getDay() && SD.getDate().getHour() == FD.getDate().getHour() && SD.getDate().getMinute() == FD.getDate().getMinute();

        return a;

    };

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static equals eqWay = (Statement SD, Flight FD) -> {
        boolean a;

        a = SD.getWay().getF().equals(FD.getWay().getF()) && SD.getWay().getL().equals(FD.getWay().getL());

        return a;

    };

    public static void Search(StatementData SDB, FlightData FDB) {
        System.out.println("---------------------------------------");
        for (int i = 0; i < SDB.size(); i++) {
            System.out.println("Этой заявке");
            System.out.println(SDB.getEl(i));
            System.out.println("Соответсвуют эти рейсы");
            for (int j = 0; j < FDB.size(); j++) {
                if (eqDate.eq(SDB.getEl(i), FDB.getEl(j)) && eqDate.eq(SDB.getEl(i), FDB.getEl(j))) {
                    System.out.println(FDB.getEl(j));
                    System.out.println("---------------------------------------");
                }
            }

        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void addS(StatementData SDB, Scanner in) {
        LinkedList<Way> way = new LinkedList();
        LinkedList<People> people = new LinkedList();
        LinkedList<Date> date = new LinkedList();
        int i = 0;
        while (in.hasNextLine()) {
            try {

                String str = in.nextLine();
                String s[] = str.split(" ");
                way.add(new Way(s[0], s[1]));
                date.add(new Date(Integer.parseInt(s[2]), Integer.parseInt(s[3]), Integer.parseInt(s[4]), Integer.parseInt(s[5]), Integer.parseInt(s[6])));
                people.add(new People(s[7], s[8]));
                SDB.add(new Statement(way.get(i), date.get(i), people.get(i)));
            } catch (WrongNumException | EmptyValueException e) {
                System.out.println(e);
            } catch (NumberFormatException e) {
                System.out.println(e + "\n Неправильный формат ввода");
            }

            i++;
        }
    }

    public static void addF(FlightData FDB, Scanner in1) {
        LinkedList<Way> way = new LinkedList();
        LinkedList<Date> date = new LinkedList();
        int i = 0;
        int number;
        int price;
        String type;
        while (in1.hasNextLine()) {
            try {
                String str = in1.nextLine();
                String s[] = str.split(" ");
                way.add(new Way(s[0], s[1]));
                date.add(new Date(Integer.parseInt(s[2]), Integer.parseInt(s[3]), Integer.parseInt(s[4]), Integer.parseInt(s[5]), Integer.parseInt(s[6])));
                number = Integer.parseInt(s[7]);
                price = Integer.parseInt(s[8]);
                type = s[9];
                FDB.add(new Flight(way.get(i), date.get(i), number, price, type));
            } catch (WrongNumException | EmptyValueException e) {
                System.out.println(e);
                return;
            } catch (NumberFormatException e) {
                System.out.println(e + "\n Неправильный формат ввода");
                return;
            }
            i++;
        }

    }

}
