public class Minesweeper {
    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        int k = Integer.parseInt(args[2]);
        int[][] field = new int[m + 2][n + 2];
        int mines = 0;
        while (mines < k) {
            int row = (int) (Math.random() * (m) + 1);
            int col = (int) (Math.random() * (n) + 1);
            if (field[row][col] != -1) {
                field[row][col] = -1;
                mines++;
            }
        }
        for (int i = 1; i < field.length - 1; i++) {
            for (int j = 1; j < field[i].length - 1; j++) {
                if (field[i][j] != -1) {
                    if (field[i - 1][j - 1] == -1)
                        field[i][j]++;
                    if (field[i - 1][j] == -1)
                        field[i][j]++;
                    if (field[i - 1][j + 1] == -1)
                        field[i][j]++;
                    if (field[i][j - 1] == -1)
                        field[i][j]++;
                    if (field[i][j + 1] == -1)
                        field[i][j]++;
                    if (field[i + 1][j - 1] == -1)
                        field[i][j]++;
                    if (field[i + 1][j] == -1)
                        field[i][j]++;
                    if (field[i + 1][j + 1] == -1)
                        field[i][j]++;
                }
            }
        }
        for (int i = 1; i < field.length - 1; i++) {
            for (int j = 1; j < field[i].length - 1; j++) {
                if (field[i][j] == -1)
                    System.out.print("*  ");
                else
                    System.out.print(field[i][j] + "  ");
            }
            System.out.println();
        }
    }
}