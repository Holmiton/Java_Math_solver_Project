import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.math.BigInteger;

public class Test {

    public static void main(String[] args) throws FileNotFoundException {
        Rational[][] rationals = new Rational[15][15];
        Rational[] right = new Rational[15];
        File file = new File("D:\\download\\QQ download\\eqn.txt");
        Scanner input = new Scanner(file);

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 16; j++) {
                String numline = input.nextLine();
                BigInteger BIr = new BigInteger(numline);
                Rational r = new Rational(BIr);
                if (j == 15) {
                    right[i] = r;
                } else {
                    rationals[i][j] = r;
                }
            }
        }

        Matrix rationalMatrix = new Matrix(rationals);
        Eqn linearEqn = new Eqn(rationals, right);
        Rational[] res = linearEqn.solve();

        for (int i = 0; i < 15; i++) {
            System.out.println(res[i]);
        }
        return;
    }
}

class Eqn {
    Rational[][] a;
    Rational[] b;
    String name;

    Rational[] res = new Rational[15];

    public Eqn(Rational[][] a, Rational[] b) {
        this.a = a;
        this.b = b;
        name = "x";
    }

    private Rational solveOneRow(int i, Rational[] row, Rational a) {
        Rational temp = new Rational(); // 0
        for (int j = i + 1; j < row.length; j++) {
            temp = temp.add(row[j]);
        }

        temp = temp.neg();
        return a.add(temp);
    }

    public Rational[] solve() {
        Eqn eqn = new Eqn(a, b);
        Matrix matrix = new Matrix(a);
        matrix.normal();
        Rational[] res = new Rational[15];
        res[14] = b[14].div(a[14][14]); // Corrected here
        for (int i = 13; i > -1; i--) {
            Rational sum = new Rational();
            for (int j = i + 1; j < 15; j++) {
                sum = sum.add(a[i][j].mul(res[j]));
            }
            res[i] = b[i].sub(sum).div(a[i][i]);
        }
        return res;
    }
}

class Rational {
    BigInteger a;
    BigInteger b;

    public Rational() {
        a = BigInteger.ZERO;
        b = BigInteger.ONE;
    }

    public Rational(BigInteger a) {
        this.a = a;
        b = BigInteger.ONE;
    }

    public Rational(int a) {
        this.a = new BigInteger("" + a);
        this.b = BigInteger.ONE;
    }

    public Rational(int a, int b) {
        this.a = new BigInteger("" + a);
        this.b = new BigInteger("" + b);
    }

    public Rational(BigInteger a, BigInteger b) {
        this.a = a;
        this.b = b;
        simplify();
    }

    public Rational mul(Rational x) {
        return new Rational(
                a.multiply(x.a),
                b.multiply(x.b));
    }

    public Rational div(Rational x) {
        return new Rational(
                a.multiply(x.b),
                b.multiply(x.a));
    }

    public Rational neg() {
        return new Rational(a.negate(), b);
    }

    public Rational inv() {
        assert !a.equals(BigInteger.ZERO);
        return new Rational(b, a);
    }

    public Rational add(Rational x) {
        BigInteger t0 = a.multiply(x.b);
        BigInteger t1 = b.multiply(x.a);
        BigInteger aa = t0.add(t1);

        BigInteger bb = b.multiply(x.b);
        return new Rational(aa, bb);
    }

    public Rational sub(Rational x) {
        BigInteger t0 = a.multiply(x.b);
        BigInteger t1 = b.multiply(x.a);
        BigInteger aa = t0.subtract(t1);

        BigInteger bb = b.multiply(x.b);
        return new Rational(aa, bb);
    }

    public String toString() {
        simplify();
        if (b.equals(BigInteger.ONE))
            return String.format("%s", a);
        else
            return String.format("%s/%s", a, b);
    }

    public boolean equals(Rational r) {
        BigInteger x = this.a.multiply(r.b);
        BigInteger y = this.b.multiply(r.a);
        return x.equals(y);
    }

    private void simplify() {
        if (a.equals(BigInteger.ZERO)) {
            b = BigInteger.ONE;
        } else {
            BigInteger gcd = a.gcd(b);
            a = a.divide(gcd);
            b = b.divide(gcd);
        }
    }
}

class Matrix {
    Rational[][] d;

    public Matrix() {}

    public Matrix(int[][] d) {
        this.d = new Rational[d.length][d[0].length];
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[0].length; j++) {
                this.d[i][j] = new Rational(d[i][j]);
            }
        }
    }

    public Matrix(Rational[][] d) {
        this.d = new Rational[d.length][d[0].length];
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[0].length; j++) {
                this.d[i][j] = d[i][j];
            }
        }
    }

    public void print() {
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[0].length; j++) {
                System.out.printf("%s ", d[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void exchange(int i, int j) {
        for (int k = 0; k < d[0].length; k++) {
            Rational t = d[i][k];
            d[i][k] = d[j][k];
            d[j][k] = t;
        }
    }

    public void scale(int row, Rational coef) {
        for (int i = 0; i < d[0].length; i++) {
            d[row][i] = d[row][i].mul(coef);
        }
    }

    public void addTo(int src, Rational coef, int dst) {
        for (int i = 0; i < d[0].length; i++) {
            d[dst][i] = d[src][i].mul(coef).add(d[dst][i]);
        }
    }

    private int cur_row, cur_col;

    private boolean findGoodPos() {
        for (int j = cur_col + 1; j < d[0].length; j++) {
            for (int i = cur_row + 1; i < d.length; i++) {
                if (!d[i][j].equals(new Rational(0))) {
                    cur_row = i;
                    cur_col = j;
                    return true;
                }
            }
        }
        return false;
    }

    public void normal() {
        cur_row = -1;
        cur_col = -1;

        for (int j = 0; j < d.length; j++) {
            boolean found = findGoodPos();

            if (!found)
                return;
            exchange(cur_row, j);
            cur_row = j;
            for (int i = cur_row + 1; i < d.length; i++) {
                Rational t0 = d[i][cur_col];
                Rational t1 = d[cur_row][cur_col];
                Rational t2 = t0.div(t1);
                Rational t3 = t2.neg();
                addTo(cur_row, t3, i);
            }
            Rational t1 = d[cur_row][cur_col].inv();
            scale(cur_row, t1);
        }
    }
}



