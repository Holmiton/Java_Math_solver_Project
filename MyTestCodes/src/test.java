import java.util.Scanner;

public class test {
    public static void main(String[] arg){
        int[][] a = {{1, 3}, {3, 1}};
        int[] b = {3, 7};

        Rational x = new Rational(1, 2);
        Rational y = new Rational(1, 3);
        Rational z = x.mul(y);
        System.out.print(z);
    }
}
