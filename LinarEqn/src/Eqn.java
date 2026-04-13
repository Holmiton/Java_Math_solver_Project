

public class Eqn {
	Rational[][] a;
	Rational[] b;
	String name;

	Rational[] res = new Rational[15];

	public Eqn(Rational[][] a, Rational[] b) {
		this.a = a;
		this.b = b;
		name = "x";
	}

	private Rational solveOneRow(int i, Rational[] row, Rational a){
		Rational temp = new Rational();//0
		for(int j = i + 1; j < row.length; j++) {
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
		res[14] = a[14][14];
		for(int i = 13; i > -1; i--){
			res[i] = solveOneRow(i, a[i], b[i]);
		}
		return res;
	}
	
}
