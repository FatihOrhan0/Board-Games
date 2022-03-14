import java.util.*;

public class Player {

	Board pb;
	Ship[] ships;
	boolean[] sunks;
	// Carrier (5 spaces), a Battleship (4), a Cruiser (3) a sub (3) and a
	// Destroyer(2).
	String[] sn = { "Destroyer", "Cruiser", "Sub", "Battleship", "Carrier" };
	int[] sl = { 2, 3, 3, 4, 5 };

	public Player() {
		super();
		sunks = new boolean[5];
		pb = new Board();
		ships = new Ship[5];
		for (int i = 0; i < 5; i++) {
			System.out.println("Start placing " + sn[i] + " of length " + sl[i]);
			ships[i] = new Ship(sl[i], sn[i], pb);
		}

	}

	/*
	 * public static final int EMPTY = 0; public static final int SHIPNH = 1; public
	 * static final int EMPTYHIT = 2; public static final int SHIPH = 3; #: not yet
	 * hit X: missed O: hit ship
	 */

	public String display() {
		String s = "";
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (pb.b[i][j] == Board.EMPTY || pb.b[i][j] == Board.SHIPNH)
					s += "# ";
				else if (pb.b[i][j] == Board.EMPTYHIT)
					s += "X ";
				else
					s += "O ";
			}
			s += "\n";

		}
		return s;
	}

	public void myTurn(Player p) {
		Scanner s = new Scanner(System.in);
		int row, col;
		System.out.println("Your board:");
		System.out.println(pb);
		System.out.println("Your opponent's board:");
		System.out.println(p.display());
		do {

			do {
				System.out.print("Enter the row of target: ");
				row = s.nextInt();
			} while (row < 1 || row > 10);
			do {
				System.out.print("Enter the column of target: ");
				col = s.nextInt();
			} while (col < 1 || col > 10);
		} while (p.pb.b[row - 1][col - 1] == Board.EMPTYHIT || p.pb.b[row - 1][col - 1] == Board.SHIPH);
		if (p.pb.b[row - 1][col - 1] == Board.EMPTY) {
			System.out.println("You missed.");
			p.pb.b[row - 1][col - 1] = Board.EMPTYHIT;
		} else {
			System.out.println("You hit.");
			p.pb.b[row - 1][col - 1] = Board.SHIPH;
			p.upS(row - 1, col - 1);
			if (gameOver(p))
				System.out.print("");
			else
				myTurn(p);
		}
	}

	public void upS(int r, int c) {
		int pos = 10 * (r) + c + 1;
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].position.length; j++) {
				if (ships[i].position[j] == pos) {
					ships[i].hp--;
					if (ships[i].hp == 0) {
						sunks[i] = true;
						System.out.println(sn[i] + " is sunk.");
					}
				}
			}
		}
	}

	public boolean gameOver(Player p) {
		for (int i = 0; i < 5; i++) {
			if (!p.sunks[i])
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		boolean go = false;
		System.out.println("Player 1 is placing the ships.");
		Player p1 = new Player();
		System.out.print(p1.pb);
		System.out.println("Player 2 is placing the ships.");
		Player p2 = new Player();
		System.out.print(p2.pb);
		while (!go) {
			p1.myTurn(p2);
			if (p1.gameOver(p2)) {
				System.out.println("p1 won.");
				break;
			}
			p2.myTurn(p1);
			if (p2.gameOver(p1)) {
				System.out.println("p2 won.");
				break;
			}
			}
		}
}
