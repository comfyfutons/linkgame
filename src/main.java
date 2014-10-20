import java.util.Scanner;

import Board.Board;

public class main {
	public static final int GAME_RUNNING = 0;
	public static final int GAME_STOPPED = 1;

	public static void main(String args[]){
		
		int gameStatus = GAME_RUNNING;
		
		Scanner scan = new Scanner(System.in);
		
		Board test = new Board(5);
		
		test.printBoard();
		
		while(gameStatus == GAME_RUNNING){
			int x;
			int y;
			String location;
		
			System.out.println("enter X Position to place a line (1 - " + test.getSize() / 2 + ")");
			x = scan.nextInt();
			
			System.out.println("enter Y Position to place a line (1 - " + test.getSize() / 2 + ")");
			y = scan.nextInt();
			
			System.out.println("enter top, bottom, right, or left to place the line");
			location = scan.next();
			
			test.setLine(y, x, location);
			
			test.printBoard();
		}
		
		System.out.println("Game Over");
	}
}