import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.lang.management.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import Board.Board;

public class main {
	public static final int GAME_RUNNING = 0;
	public static final int GAME_STOPPED = 1;

	public static void main(String args[]) throws FileNotFoundException{
		
		Board test = new Board(0);
		int gameStatus = GAME_RUNNING;
		
		Scanner scan = new Scanner(System.in);
		File selectedFile;
		
		while(gameStatus == GAME_RUNNING){

			
			System.out.println("Choose board file");
			JFrame parentFrame = new JFrame();
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("txt files", "txt");
			fileChooser.setFileFilter(filter);
			int result = fileChooser.showOpenDialog(parentFrame);

			if (result == JFileChooser.APPROVE_OPTION) {
				selectedFile = fileChooser.getSelectedFile();
				System.out.println("Selected file: " + selectedFile.getAbsolutePath());

				
					Scanner scanFile = new Scanner(selectedFile);
					if(scanFile.hasNext()){
						test = new Board(scanFile.nextInt());
					}
					while(scanFile.hasNext()){
						test.setNumber(scanFile.nextInt(), scanFile.nextInt(), scanFile.nextInt());
					}
			
			}

			boolean winStatus = false;
			test.printBoard();

			String play = "";
			
			while(!play.equals("play") && !play.equals("comp")){
				System.out.println("Type 'play' to play the game, 'comp' to have it solved for you, or 'quit' to exit game");
				play = scan.next();
			}
			
			
			//PLAYER SOLVES PUZZLE
			if(play.equals("play")){
				
				while(!winStatus){

					int x;
					int y;
					String location;


					System.out.println("Enter move in format 'row# column# lineside' with spaces in between. example: '1 1 T':");
					x = scan.nextInt();
					y = scan.nextInt();
					location = scan.next();

					//T for top, B for bottom, L for left, and R for right)

					if(location.equals("T") || location.equals("B") || location.equals("L") || location.equals("R")){
						test.setLine(y, x, location);
					} else{
						System.out.println("lineside must equal T, B, L, R");
					}

					test.printBoard();
					long startTime = System.nanoTime();
					if(test.checkLoop() && test.checkNumbers()){
						System.out.println(System.nanoTime() - startTime);
						System.out.println("Game Status: WINNING BOARD");
						winStatus = true;

						System.out.println("would you like to play again (Y or N)?");
						String playAgain;
						playAgain = scan.next();
						if(playAgain.equals("N") || playAgain.equals("n")){
							gameStatus = GAME_STOPPED;
						}

					} else{
						System.out.println("Game Status: Incorrect Board");
					}
				}
			}
			
			//COMPUTER SOLVES PUZZLE
			if(play.equals("comp")){
				ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
				long startTime = bean.getCurrentThreadCpuTime();
				
				System.out.println("Computer Solver");
				test.printUnusable();
				test.adjacentRules();
				test.cornerRules();
				test.printUnusable();
				
				test.solve();
				
				long endTime = bean.getCurrentThreadCpuTime();
				double seconds = ((double)(endTime - startTime))/1000000000;
				
				System.out.println("Executin in " + seconds + " seconds.");
				System.out.println("would you like to play again (Y or N)?");
				String playAgain;
				playAgain = scan.next();
				if(playAgain.equals("N") || playAgain.equals("n")){
					gameStatus = GAME_STOPPED;
				}
			}
			
			//QUITING GAME
			if(play.equals("quit")){
				gameStatus = GAME_STOPPED;
			}
			
		}
		System.out.println("Game Over");
	}
}