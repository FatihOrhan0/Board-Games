
public class Board {

	public static final int EMPTY = 0;
	public static final int SHIPNH = 1;
	public static final int EMPTYHIT = 2;
	public static final int SHIPH = 3;

	public int[][] b = new int[10][10];

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				s += b[i][j] + " ";
			}
			s += "\n";

		}
		return s;
	}

	public static void main(String[] args) {

	}

}
