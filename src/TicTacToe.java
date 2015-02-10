import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	String[][] board = new String[3][3];
	boolean[][] placeCheck = new boolean[3][3];
	boolean xTurn;
	boolean oTurn;
	boolean computerFirst;
	boolean flag = false;

	Random rand = new Random();
	Scanner scan = new Scanner(System.in);
	
	public void main() {
		resetBoard();
		chooseFirst();
		while (!finalWinCheck()) {
		    if (finalWinCheck())
            {
                return;
            }
		    chooser();
			printBoard();
		}
		System.out.println("Want to play again? yes/no");
        String answer = scan.next();
        if (answer.equalsIgnoreCase("yes")) {
            main();
        }
	}
	
	public void printCheck() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				System.out.print(placeCheck[row][col]);
			}
			System.out.println();
		}
	}
	
	public void resetBoard() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				board[row][col] = "?";
				placeCheck[row][col] = false;
			}
		}
	}

	public void printBoard() {
		System.out.println(" " + board[0][0] + " | " + board[0][1] + " | "
				+ board[0][2]);
		System.out.println("---+---+---");
		System.out.println(" " + board[1][0] + " | " + board[1][1] + " | "
				+ board[1][2]);
		System.out.println("---+---+---");
		System.out.println(" " + board[2][0] + " | " + board[2][1] + " | "
				+ board[2][2]);
	}

	/**
	 * Chooses who goes first
	 * 
	 * @return 1 if human (X) goes first and 2 if computer (O) goes firstF
	 */
	public int chooseFirst() {
		int chooser = rand.nextInt(2);
		boolean check = true;
		while (check) {
			if (chooser == 0) {
				System.out.println("Human goes first!");
				xTurn = true;
				return 1;
			} else
				System.out.println("Computer goes first!");
				oTurn = true;
				computerFirst = true;
				return 2;
		}
		return -1;
	}

	public void switcher() {
		oTurn = !oTurn;
		xTurn = !xTurn;
	}

	public void chooser() {
		if (oTurn) {
			computerTurn();
		} else {
			humanTurn();
		}
		this.switcher();
	}

	public void humanTurn() {
		boolean noobCheck = true;
		int coord1;
		int coord2;
		while (noobCheck) {
			System.out.println("Human's turn!");
			System.out.println("Choose a coordinate (x y), separated by a space!");
			coord1 = Integer.parseInt(scan.next());
			coord2 = Integer.parseInt(scan.next());
			if ((coord1 > 0 && coord1 < 4) && (coord2 > 0 && coord2 < 4)) {
				if (!placeCheck[coord2 - 1][coord1 - 1]) {
					board[coord2 - 1][coord1 - 1] = "X";
					placeCheck[coord2 - 1][coord1 - 1] = true;
					noobCheck = false;
				} else {
					System.out.println("You n00b, this spot is taken!");
					continue;
				}
			} else {
				System.out.println("Please insert coordinates that are between and include 1 and 3, you filthy casual...");
			}
		}
	}
	
	public void computerTurn() {
		boolean mlgCheck = true;
		int coord1;
		int coord2;
		System.out.println("Computer's turn!");
		while (mlgCheck) {
			if (computerFirst) {
				coord1 = 2;
				coord2 = 2;
				computerFirst = false;
			} else {
				coord1 = rand.nextInt(3) + 1;
				coord2 = rand.nextInt(3) + 1;
			}
			if (!placeCheck[coord2 - 1][coord1 - 1]) {
				placeCheck[coord2 - 1][coord1 - 1] = true;
				board[coord2 - 1][coord1 - 1] = "O";
				mlgCheck = false;
			} else {
				//System.out.print("Didn't find");
			}
		}
	}
	
	public boolean xWinCheck() {
		int checker = 0;
		for (int row = 0; row < 3; row ++) {
			for (int col = 0; col < 3; col++) {
				if (board[row][col].equals("X")) {
					checker++;
				}
			}
			if (checker == 3) {
				System.out.println("Human winner!");
				return true;
			} 
			checker = 0;
		}
		for (int col = 0; col < 3; col++) {
			for (int row = 0; row < 3; row ++) {
				if (board[row][col].equals("X")) {
					checker++;
				}
			}
			if (checker == 3) {
				System.out.println("Human winner!");
				return true;
			}
			checker = 0;
		}
		for (int i = 0; i < 3; i++) {
			if (board[i][i].equals("X")) {
				checker++;
			}
		}
		if (checker == 3) {
			System.out.println("Human winner!");
			return true;
		}
		checker = 0;
		for (int i = 0; i < 3; i++) {
			if(board[2 - 1][i].equals("X")) {
				checker++;
			}
		}
		if (checker == 3) {
			System.out.println("Human Winner!");
			return true;
		}
		checker = 0;
		return false;
	}
	
	public boolean oWinCheck() {
		int checker = 0;
		for (int row = 0; row < 3; row ++) {
			for (int col = 0; col < 3; col++) {
				if (board[row][col].equals("O")) {
					checker++;
				}
			}
			if (checker == 3) {
				System.out.println("Computer winner!");
				return true;
			} 
			checker = 0;
		}
		for (int col = 0; col < 3; col++) {
			for (int row = 0; row < 3; row ++) {
				if (board[row][col].equals("O")) {
					checker++;
				}
			}
			if (checker == 3) {
				System.out.println("Computer winner!");
				return true;
			}
			checker = 0;
		}
		for (int i = 0; i < 3; i++) {
			if (board[i][i].equals("O")) {
				checker++;
			}
		}
		if (checker == 3) {
			System.out.println("Computer winner!");
			return true;
		}
		checker = 0;
		for (int i = 0; i < 3; i++) {
			if(board[2 - 1][i].equals("O")) {
				checker++;
			}
		}
		if (checker == 3) {
			System.out.println("Computer Winner!");
			return true;
		}
		checker = 0;
		return false;
	}
	
	public boolean finalWinCheck() {
		if (this.xWinCheck() || this.oWinCheck()) {
				return true;
		}
		int placeAdd = 0;
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col ++) {
				if (placeCheck[row][col]) {
					placeAdd++;
				}
			}
		}
		if (placeAdd == 9) {
			System.out.println("Tie!");
				return true;
			}
		return false;
	}
}
