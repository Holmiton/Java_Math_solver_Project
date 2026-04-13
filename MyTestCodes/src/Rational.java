import java.math.BigInteger;
public class Rational {
    int a;
    int b;

    public Rational() {
        a = 0;
        b = 1;
    }

    public Rational(int a) {
        this.a= a;
        b = 1;
    }

    public Rational(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public Rational(int a, int b) {
        this.a = new BigInterger("" + a);
        this.b = new BigInterger("" + b);
    }

    public mul(Rational x, Rational y) {
        return new Rational(x.a * y.a, x.b * y.b);
    }

    public String toString() {
        return String.format("%s/%s", a, b)
    }
}
