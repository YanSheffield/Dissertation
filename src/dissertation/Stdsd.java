package dissertation;

public class Stdsd {

	public static void main(String[] args) {
		System.out.println(getBinomial(9, 1d/3d));

	}
	
	public static int getBinomial(int n, double p) {
		  int x = 0;
		  for(int i = 0; i < n; i++) {
		    if(Math.random() < p)
		      x++;
		  }
		  return x;
		}
}
