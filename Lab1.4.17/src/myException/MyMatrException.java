
package myException;

public class MyMatrException extends Exception
{
    public int n;
    public String s = "wrong numba for matrix: ";
    
    public MyMatrException(int n)
    {
        this.n = n;
    }
    @Override
    public String toString()
    {
        return s + n;
    }
    @Override
    public String getMessage()
    {
        return this.toString();
    }
}
