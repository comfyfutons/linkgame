import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
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
		System.out.println("Game Over");
	}
}