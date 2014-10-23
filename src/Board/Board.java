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
		if(xPos <= board.length/2 && yPos <= board[0].length/2 && xPos > 0 && yPos > 0){
			int xPosition = xPos * 2 - 1;
			int yPosition = yPos * 2 - 1;
			
			((Square) board[yPosition][xPosition]).setNumber(number);
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

				
		return true;
	}

	public void setLine(int xPos, int yPos, String location){
		if(xPos <= board.length/2 && yPos <= board[0].length/2 && xPos > 0 && yPos > 0){
			int xPosition = xPos * 2 - 1;
			int yPosition = yPos * 2 - 1;
			if(location.equals("top")){
				yPosition--;
			} else if(location.equals("right")){
				xPosition++;
			} else if(location.equals("left")){
				xPosition--;
			} else if(location.equals("bottom")){
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
