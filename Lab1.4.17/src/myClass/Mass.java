package myClass;

import java.util.Arrays;
import myException.MyMatrException;
import java.util.Scanner;

public class Mass {

    private int n;
    private int[][] matriza;

    Mass(int n) throws MyMatrException {
        if (n <= 0) {
            throw new MyMatrException(n);
        }
        this.n = n;
        this.matriza = new int[this.n][this.n];
    }

    Mass() {
        this.n = 5;
        this.matriza = new int[this.n][this.n];
    }

    Mass(Mass a) {
        this.n = a.n;
        for (int i = 0; i < this.n; i++) {
            System.arraycopy(a.matriza[i], 0, matriza[i], 0, this.n);
        }

    }

    public void Print() {
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                System.out.print(" " + this.matriza[i][j]);
            }
            System.out.println("");
        }
    }

    public void smenastolbca(int j1, int j2)//функция смены
    {
        int smen;

        for (int i = 0; i < this.n; i++) {
            smen = this.matriza[i][j1];
            this.matriza[i][j1] = this.matriza[i][j2];
            this.matriza[i][j2] = smen;
        }
    }

    public void Scann() throws MyMatrException {
        Scanner scan = new Scanner(System.in);

        System.out.println("Print your long of matrix:");
        if ((this.n = scan.nextInt()) <= 0) {
            throw new MyMatrException(this.n);
        }

        System.out.println("Print your values:");
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                this.matriza[i][j] = scan.nextInt();
            }
        }
    }

    public void ocnovnuslovie()//основная функция 
    {

        int[] masxarakt = new int[this.n];

        for (int k = 0; k < this.n; k++) {
            int xarakstolbca = 0;
            for (int i = 0; i < this.n; i++) {
                if ((this.matriza[i][k] < 0 && this.matriza[i][k] % 2 != 0)) {
                    xarakstolbca += this.matriza[i][k];
                }
            }
            System.out.print("\ncolumn feature" + k);
            System.out.print(" " + xarakstolbca * (-1));//вывод характеристики по модулю
            masxarakt[k] = xarakstolbca;//заполнение массива для дальнейшей смены столбцов по возрастанию характеристики

        }
        System.out.println(".");
        for (int i = 1; i < this.n; i++)//смена столбцов
        {
            if (masxarakt[i - 1] < masxarakt[i]) {
                int a = masxarakt[i - 1];
                masxarakt[i - 1] = masxarakt[i];
                masxarakt[i] = a;
                this.smenastolbca(i - 1, i);
                i = 0;
            }
        }
    }
    
    @Override
    public boolean equals(Object o)
    {
        if( this == o )
            return true;
        if( o == null )
            return false;
        if (!(o instanceof Mass))
            return false;
        Mass a = (Mass)o;
        return Arrays.equals(matriza, a.matriza) && this.n == a.n;
    }
    
    @Override
    public int hashCode()
    {
        return Arrays.hashCode(matriza) + n;
    }
}

