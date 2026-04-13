
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.math.BigInteger;

public class Test {


	public static void main(String[] args) throws FileNotFoundException {
		Rational[][] rationals = new Rational[15][15];
		Rational[] right = new Rational[15];
		File file = new File("D:\\download\\QQ download\\eqn.txt");
		Scanner input = new Scanner(file);

		for(int i = 0; i< 15; i++) {
			for(int j = 0; j< 16; j++) {
				String numline = input.nextLine();
				BigInteger BIr = new BigInteger(numline);
				Rational r = new Rational(BIr);
				if(j == 15) {
					right[i] = r;
				}
				else{
					rationals[i][j] = r;
				}
			}
		}

		Matrix RationalMatrix = new Matrix(rationals);
		Eqn linarEqn = new Eqn(rationals, right);
		Rational[] res = new Rational[15];
		res = linarEqn.solve();

		for(int i =0; i < 15; i++) {
			System.out.println(res[i]);
		}
		return;
	}	
}



