/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airline;

/**
 *
 * @author НИколай Соболев
 */
public class Flight {

    private Date date;
    private Way way;
    private int price;
    private int number;
    private String type;

    public Flight(Way way, Date date, int number, int price, String type) throws EmptyValueException {
        this.price = price;
        this.number = number;
        this.date = date;
        this.way = way;
        this.type = type;
        if (type == null) {
            throw new EmptyValueException();
        }
    }

    public Way getWay() {
        return way;
    }

    public Date getDate() {
        return date;
    }

    public int getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "\nПуть:" + way + "\n" +  date + "\n" + "Номер рейса:" + number + "\n" + "Цена билета:" + price + "\n" + "Тип самолёта:" + type + "\n";
    }

}
