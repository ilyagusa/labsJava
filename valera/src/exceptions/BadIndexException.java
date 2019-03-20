
package exceptions;


public class BadIndexException extends Exception{
    String s = "Некорректное значение индекса: ";
    int val;

    public BadIndexException(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return s + val + "\n";
    }

    @Override
    public String getMessage() {
        return this.toString();
    }
}
