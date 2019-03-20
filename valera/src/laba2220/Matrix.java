
package laba2220;

import exceptions.BadIndexException;

public class Matrix {

    private double[][] matrix;

    private double[][] square;
    
    private double[][] cube;
    
    private double[][] souz;

    public int sizematr;

    public Matrix(double[][] double_array, int size) throws NegativeArraySizeException {
        if(size <= 1)
            throw new NegativeArraySizeException();

        this.matrix = new double[size][size];

        this.matrix = double_array;

        this.sizematr = size;

    }
//конструктор копирования 

    public Matrix(Matrix A) {
        this.sizematr = A.sizematr;
        this.matrix = A.matrix;
        square = A.square;
        cube = A.cube;
        souz = A.souz;
    }

    public double getFirst() {
        return matrix[0][0];
    }

    private double[][] getMinor(double matrix[][], int row, int column) throws BadIndexException {
        if(row < 0 )
            throw new BadIndexException(row);
        if(column < 0)
            throw new BadIndexException(column);
        int minorLength = matrix.length - 1;
        double[][] minor = new double[minorLength][minorLength];
        int dI = 0;//эти переменные для того, чтобы "пропускать" ненужные нам строку и столбец
        int dJ;
        for (int i = 0; i <= minorLength; i++) {
            dJ = 0;
            for (int j = 0; j <= minorLength; j++) {
                if (i == row) {
                    dI = 1;
                } else {
                    if (j == column) {
                        dJ = 1;
                    } else {
                        minor[i - dI][j - dJ] = matrix[i][j];
                    }
                }
            }
        }

        return minor;

    }

    //функция находящая определитель которая раскладывает матрицу по первой строке каждый раз пока не дойдет до матрицы 2х2 чтобы получить определитель с помощью правила крест на крест
    // функция приватная чтобы она могла применяться только для нашего класса Матрикс
    private double CalculateMatrix(double[][] matrix) throws BadIndexException {
        double calcResult = 0.0;

        if (matrix.length == 2) {
            calcResult = matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];// правило крест на крест
        } else {
            int koeff;
            for (int i = 0; i < matrix.length; i++) {
                if (i % 2 == 1) {  //я решил не возводить в степень, а просто поставить условие - это быстрее. Т.к. я раскладываю всегда по первой (читай - "нулевой") строке, то фактически я проверяю на четность значение i+0.
                    koeff = -1;
                } else {
                    koeff = 1;
                }
                //собственно разложение:                
                calcResult += koeff * matrix[0][i] * this.CalculateMatrix(this.getMinor(matrix, 0, i));
            }
        }

        //возвращаем ответ
        return calcResult;
    }

    //Функция вызывающая CalculateMatrix чтобы передать туда матрицу из нашего конструктора
    public double Det() throws BadIndexException {
        double Determinant;
        Determinant = CalculateMatrix(matrix);
        return Determinant;
    }

    //квадрат матрицы
    public double[][] square() {

        double[][] res = new double[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                for (int k = 0; k < matrix.length; k++) {
                    res[i][j] += matrix[i][k] * matrix[k][j];
                }
            }
        }

        return res;

    }

    //куб матрицы
    public double[][] cube() {

        double[][] res1 = new double[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                for (int k = 0; k < matrix.length; k++) {
                    res1[i][j] += square()[i][k] * matrix[k][j];
                }
            }
        }

        return res1;

    }

    //метод вычисляющий алгебраическое дополнение для заданного строки столбца()
    private double algDop(int row, int col) throws BadIndexException {
        if(row < 0 )
            throw new BadIndexException(row);
        if(col < 0)
            throw new BadIndexException(col);
        int koeff;
        double calcResult = 0.0;
        if (matrix.length == 2) {
            calcResult = matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
        }

        if ((row + col) % 2 == 0) {
            koeff = 1;
        } else {
            koeff = -1;
        }
        calcResult += koeff * this.CalculateMatrix(this.getMinor(matrix, row, col));

        return calcResult;
    }

    public double[][] souz() throws BadIndexException {

        double[][] souzmatr = new double[matrix.length][matrix.length];
//получение союзной  матрицы с помощью вычисления алгебраических дополнений
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix.length > 2) {
                    souzmatr[i][j] = algDop(i, j);
                } else if (matrix.length == 2) {
                    souzmatr[0][0] = matrix[1][1];
                    souzmatr[0][1] = matrix[1][0] * -1;
                    souzmatr[1][0] = matrix[0][1] * -1;
                    souzmatr[1][1] = matrix[0][0];
                } else if (matrix.length == 1) {
                    souzmatr[0][0] = matrix[0][0];
                }
            }
        }

        //транспонирование матрицы
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                double temp = souzmatr[i][j];
                souzmatr[i][j] = souzmatr[j][i];
                souzmatr[j][i] = temp;
            }
        }

        return souzmatr;
    }

    @Override

    public String toString() {
        {
            String str = new String();
            for (int i = 0; i < sizematr; i++) {
                for (int j = 0; j < sizematr; j++) {
                    str += matrix[i][j] + " | ";
                }
                str += "\n";
            }

            return "Собственно сама матрица \n" + str;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Matrix)) {
            return false;
        }
        Matrix a = (Matrix) o;
        if (sizematr != a.sizematr) {
            return false;
        }
        for (int i = 0; i < sizematr; i++) {
            for (int j = 0; j < sizematr; j++) {
                if (matrix[i][j] != a.matrix[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int code = 0;
        for (int i = 0; i < sizematr; i++) {
            for (int j = 0; j < sizematr; j++) {
                code += matrix[i][j];
            }
        }
        code += sizematr;
        return code;
    }

}
