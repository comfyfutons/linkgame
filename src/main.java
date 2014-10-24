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

	public static void main(String args[]){
		
		Board test = new Board(0);
		int gameStatus = GAME_RUNNING;
		
		Scanner scan = new Scanner(System.in);
		File selectedFile;
		
		JFrame parentFrame = new JFrame();
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("txt files", "txt");
		fileChooser.setFileFilter(filter);
		int result = fileChooser.showOpenDialog(parentFrame);
		if (result == JFileChooser.APPROVE_OPTION) {
		    selectedFile = fileChooser.getSelectedFile();
		    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		   
		    try {
		    	Scanner scanFile = new Scanner(selectedFile);
		    	if(scanFile.hasNext()){
		    		test = new Board(scanFile.nextInt());
		    	}
		    	while(scanFile.hasNext()){
		    		test.setNumber(scanFile.nextInt(), scanFile.nextInt(), scanFile.nextInt());
		    	}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		}
		
		test.printBoard();
		
		while(gameStatus == GAME_RUNNING){
			int x;
			int y;
			String location;
			

			System.out.println("Enter move in format 'row# column# lineside' with spaces in between. example: '1 1 top':");
			x = scan.nextInt();
			y = scan.nextInt();
			location = scan.next();

			if(location.equals("right") || location.equals("bottom") || location.equals("left") || location.equals("top")){
				test.setLine(y, x, location);
			} else{
				System.out.println("lineside must equal right, bottom, left, or top");
			}

			test.printBoard();
			if(test.checkLoop() && test.checkNumbers()){
				System.out.println("Game Status: WINNING BOARD");
			} else{
				System.out.println("Game Status: Incorrect Board");
			}
		}
		
		System.out.println("Game Over");
	}
}