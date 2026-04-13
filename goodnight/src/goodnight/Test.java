package goodnight;
import java.util.Scanner;
import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		int[][] a = {
				{0, 3, 0}, 
				{1, 1, 0},
				{1, 5, 1}};
		int[] b = {3, 7};
		
		Matrix m = new Matrix(a);
		m.print();
		m.normal();
		m.print();
	}	
}



