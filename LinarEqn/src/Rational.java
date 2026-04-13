
import java.math.BigInteger;

public class Rational {
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

	public Rational(int a){
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
		if(a.equals(BigInteger.ZERO)) {
			b = BigInteger.ONE;	
		}
	}
}
