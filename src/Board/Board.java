package Board;

public class Board {

	private Tile[][] board;
	
	public Board(int size){
		board = new Tile[size * 2 + 1][size * 2 + 1];
		
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				if((i+1)%2 == 0 && (j+1)%2 == 0){
					board[i][j] = new Square();
				} else if((i+1)%2 == 1 && (j+1)%2 == 1){
					// dot spaces are null
				} else{
					board[i][j] = new Line();
				}
			}
		}
		
	}
	
	public int getSize(){
		return board.length;
	}
	
	
	public void setNumber(int xPos, int yPos, int number){
		if(yPos <= board.length/2 && xPos <= board[0].length/2 && yPos > 0 && xPos > 0){
			int yPosition = yPos * 2 - 1;
			int xPosition = xPos * 2 - 1;
			
			((Square) board[xPosition][yPosition]).setNumber(number);
		} else{
			System.out.println("position not on board");
		}
	}
	
	public boolean checkNumbers(){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				if((i+1)%2 == 0 && (j+1)%2 == 0){
					if(((Square) board[i][j]).getNumber() != -1){
						int count = ((Square) board[i][j]).getNumber();
						if( ((Line) board[i+1][j]).isLine() == true ){
							count--;
						}
						if( ((Line) board[i][j+1]).isLine() == true ){
							count--;
						}
						if( ((Line) board[i-1][j]).isLine() == true ){
							count--;
						}
						if( ((Line) board[i][j-1]).isLine() == true ){
							count--;
						}
						if(count != 0){
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	public boolean checkLoop(){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				if((i+1)%2 == 0 && (j+1)%2 == 0){
					//Not a line
				} else if((i+1)%2 == 1 && (j+1)%2 == 1){
					//Not a line
				} else{
					// Start of loop
					if(i%2 == 0){
						// horizontal starting line
						if(((Line) board[i][j]).isLine()){
							return checkLooping(i, j, board, "right");
						}
					} else{
						// vertical starting line
						if(((Line) board[i][j]).isLine()){
							return checkLooping(i, j, board, "down");
						}
					}
					
				}
			}
		}
		return true;
	}
	
	private boolean checkLooping(int startingX, int startingY, Tile[][] gameBoard, String direction){
		int xPos = startingX;
		int yPos = startingY;
		boolean firstMove = true;
		String dir = direction;
		String nextDir = dir;
		
		while(true){
			if(firstMove == false && xPos == startingX && yPos == startingY){
				if(searchUnvisited()){
					setUnvisited();
					return true;
				} else{
					return false;
				}
			}
			
			if(firstMove == true){
				firstMove = false;
			}
			
			int count = 0;

			if(dir.equals("right")){
				yPos++;
			} else if(dir.equals("down")){
				xPos++;
			} else if(dir.equals("left")){
				yPos--;
			} else if(dir.equals("up")){
				xPos--;
			} else{
				System.out.println("Error in direction");
			}
			
			
			if(xPos - 1 >= 0 && (dir.equals("right") || dir.equals("left") || dir.equals("up")) ){
				if(((Line) board[xPos-1][yPos]).isLine()){
					((Line) board[xPos-1][yPos]).setVisited(true);
					nextDir = "up";
					count++;

				}
			}
			if(yPos + 1 < gameBoard.length && (dir.equals("right") || dir.equals("down") || dir.equals("up")) ){
				if(((Line) board[xPos][yPos+1]).isLine()){
					((Line) board[xPos][yPos+1]).setVisited(true);
					nextDir = "right";
					count++;
				}
			}
			if(xPos + 1 < gameBoard[0].length && (dir.equals("right") || dir.equals("down") || dir.equals("left")) ){
				if(((Line) board[xPos+1][yPos]).isLine()){
					((Line) board[xPos+1][yPos]).setVisited(true);
					nextDir = "down";
					count++;
				}
			}
			if(yPos - 1 >= 0 && (dir.equals("down") || dir.equals("left") || dir.equals("up")) ){
				if(((Line) board[xPos][yPos-1]).isLine()){
					((Line) board[xPos][yPos-1]).setVisited(true);
					nextDir = "left";
					count++;
				}
			}
			dir = nextDir;
			
			if(count == 1){
				if(dir.equals("right")){
					yPos++;
				} else if(dir.equals("down")){
					xPos++;
				} else if(dir.equals("left")){
					yPos--;
				} else if(dir.equals("up")){
					xPos--;
				} else{
					System.out.println("Error in direction");
				}
			} else{
				setUnvisited();
				return false;
			}		
		}
	}
	
	private boolean searchUnvisited(){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				if((i+1)%2 == 0 && (j+1)%2 == 0){
					//Not a line
				} else if((i+1)%2 == 1 && (j+1)%2 == 1){
					//Not a line
				} else{
					if(((Line) board[i][j]).isLine() && !((Line) board[i][j]).isVisited()){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	private void setUnvisited(){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				if((i+1)%2 == 0 && (j+1)%2 == 0){
					//Not a line
				} else if((i+1)%2 == 1 && (j+1)%2 == 1){
					//Not a line
				} else{
					((Line) board[i][j]).setVisited(false);
				}
			}
		}
	}

	public void setLine(int xPos, int yPos, String location){
		if(xPos <= board.length/2 && yPos <= board[0].length/2 && xPos > 0 && yPos > 0){
			int xPosition = xPos * 2 - 1;
			int yPosition = yPos * 2 - 1;
			if(location.equals("T")){
				yPosition--;
			} else if(location.equals("R")){
				xPosition++;
			} else if(location.equals("L")){
				xPosition--;
			} else if(location.equals("B")){
				yPosition++;
			}
			((Line) board[yPosition][xPosition]).toggle();
		} else{
			System.out.println("position not on board");
		}
	}

	//printBoard Method: prints to the console a text representation of the game board
	public void printBoard(){
		System.out.println("GAME BOARD:");
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				if((i+1)%2 == 0 && (j+1)%2 == 0){
					if(((Square) board[i][j]).getNumber() == -1){
						System.out.print("      ");
					} else{
						System.out.print("   " + ((Square) board[i][j]).getNumber() + "  " );
					}
				} else if((i+1)%2 == 1 && (j+1)%2 == 1){
					System.out.print("  +  ");
				} else{
					if(i%2 == 0){
						if(((Line) board[i][j]).isLine()){
							System.out.print("---");
						} else{
							System.out.print("   ");
						}
					} else{
						if(((Line) board[i][j]).isLine()){
							System.out.print(" |");
						} else{
							System.out.print("  ");
						}
					}
				}
			}
			System.out.println();
			if(i%2 == 0){
				System.out.print(" ");
			}
		}
		System.out.println("\n\n\n");
	}

	
}
