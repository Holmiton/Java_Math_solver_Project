package goodnight;

public class Matrix {
	Rational[][] d;
	
	public Matrix() {
		
	}
	
	public Matrix(int[][] d) {
		this.d = new Rational[d.length][d[0].length];
		for(int i = 0; i < d.length; i++) {
			for(int j = 0; j < d[0].length; j++) {
				this.d[i][j] = new Rational(d[i][j]);
			}
		}
	}
	
	public Matrix(Rational[][] d) {
		
	}
	
	public void print() {
		for(int i = 0; i < d.length; i++) {
			for(int j = 0; j < d[0].length; j++) {
				System.out.printf("%s ", d[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void exchange(int i, int j) {
		for(int k = 0; k < d[0].length; k++) {
			Rational t = d[i][k];
			d[i][k] = d[j][k];
			d[j][k] = t;
		}
	}

	public void scale(int row, Rational coef) {
		for(int i = 0; i < d[0].length; i++) {
			d[row][i] = d[row][i].mul(coef);
		}
	}

	public void addTo(int src, Rational coef,
			int dst) {
		for(int i = 0; i < d[0].length; i++) {
			d[dst][i] = 
					d[src][i].mul(coef).add(d[dst][i]);
		}
	}
	
	private int cur_row, cur_col;
	private boolean findGoodPos() {
		for(int j = cur_col + 1; j < d[0].length; j++) {
			for(int i = cur_row + 1; i < d.length; i++) {
				if(!d[i][j]
						.equals(new Rational(0))) {
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
		
		for(int j = 0; j < d.length; j++) {
			boolean found = findGoodPos();
			
			if(!found)
				return;
			exchange(cur_row, j);
			cur_row = j;
			for(int i = cur_row + 1; i < d.length; i++) {
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
