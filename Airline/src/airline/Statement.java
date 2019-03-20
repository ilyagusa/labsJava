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
public class Statement {

    private Date date;
    private People people;
    private Way way;

    public Statement(Way way, Date date, People people) {
        this.people = people;
        this.date = date;
        this.way = way;
    }

    public Way getWay() {
        return way;
    }

    public Date getDate() {
        return date;
    }

    public People getPeople() {
        return people;
    }

    @Override
    public String toString() {
        return "\nПуть:" + way + "\n" + date + "\n" + "Фамилия и Имя:" + people + "\n";
    }
}
