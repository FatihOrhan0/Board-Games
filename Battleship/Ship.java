import java.util.*;

public class Ship {
	public int length;
	public int[] position;
	public int hp;
	public String name;

	public Ship(int length, String name, Board b) {
		super();
		this.length = length;
		position = new int[length];
		hp = length;
		this.name = name;
		insertShip(b);
	}

	public void insertShip(Board b) {
		Scanner s = new Scanner(System.in);
		int row, col, dir;
		boolean v = false;
		while (!v) {
			do {
				System.out.print("Enter the starting point row: ");
				row = s.nextInt();
			} while (row < 1 || row > 10);
			do {
				System.out.print("Enter the starting point column: ");
				col = s.nextInt();
			} while (col < 1 || col > 10);
			do {

				System.out.print("1: leftward 2: rightward 3: upward 4: downward: ");
				dir = s.nextInt();
			} while (dir < 1 || dir > 4);
			// System.out.print(row+"x"+col+ " "+dir);
			v = validate(row, col, dir, b);
		}
		// s.close();
	}

	public boolean validate(int r, int c, int d, Board b) {
		int[] ava = new int[length];
		/*
		 * System.out.println(r); System.out.println(c); System.out.println(d);
		 */
		boolean cnt = true;
		do {
			ava[0] = 10 * (r - 1) + c;
			if (d == 1) {
				for (int i = 1; i < length; i++) {
					c -= 1;
					if (c < 1) {
						cnt = false;
						break;
					} else {
						ava[i] = 10 * (r - 1) + c;
					}
				}
				break;
			} else if (d == 2) {
				// System.out.println("nnnn"+c);
				for (int i = 1; i < length; i++) {
					c += 1;
					if (c > 10) {
						cnt = false;
						break;
					} else {
						ava[i] = 10 * (r - 1) + c;
					}
				}
				for (int x : ava) {
					// System.out.println(x + "xx");
				}
				break;
			}

			else if (d == 3) {
				for (int i = 1; i < length; i++) {
					r -= 1;
					if (r < 1) {
						cnt = false;
						break;
					} else {
						ava[i] = 10 * (r - 1) + c;
					}
				}
				break;
			} else if (d == 4) {
				for (int i = 1; i < length; i++) {
					r += 1;
					if (r > 10) {
						cnt = false;
						break;
					} else {
						ava[i] = 10 * (r - 1) + c;
					}
				}
				break;
			}
		} while (cnt);
		if (cnt) {
			for (int i = 0; i < length; i++) {
				int x = ava[i] - 1;
				int ar = x / 10;
				int ac = x % 10;
				if (b.b[ar][ac] != 0)
					cnt = false;
				// System.out.print(ar + "x " + ac);
			}
		}
		if (cnt) {
			for (int i = 0; i < length; i++) {
				int x = ava[i] - 1;
				int ar = x / 10;
				int ac = x % 10;
				b.b[ar][ac] = 1;
				// System.out.println(ar + "jksdf " + ac);

			}
			position = ava;
		}
		return cnt;
	}

	public static void main(String[] args) {

	}

}
