package Board;

import java.util.Stack;
import java.util.Vector;

public class Board {

	private Tile[][] board;
	private int starti;
	private int startj;
	private long moves = 0;
	Stack<Integer> solutionRow = new Stack();
	Stack<Integer> solutionCol = new Stack();
	
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
	
	//adjacentRules Method: Marks line spots as unusable based on adjacent numbers
	public void adjacentRules(){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				if((i+1)%2 == 0 && (j+1)%2 == 0){
					//Number
					
					//RULE OF ZERO
					if(((Square) board[i][j]).getNumber() == 0){
						((Line) board[i+1][j]).setUsable(false);
						((Line) board[i-1][j]).setUsable(false);
						((Line) board[i][j+1]).setUsable(false);
						((Line) board[i][j-1]).setUsable(false);
					}
					
					//RULE OF 3 ADJACENT TO 0
					if(((Square) board[i][j]).getNumber() == 3){
						//0 below 3
						if(i + 2 < board.length){
							if(((Square) board[i + 2][j]).getNumber() == 0){
								if(i - 2 >= 0 && j - 1 >= 0){
									((Line) board[i - 2][j - 1]).setUsable(false);
								}
								if(i - 2 >= 0 && j + 1 < board[0].length){
									((Line) board[i - 2][j + 1]).setUsable(false);
								}
								if(i - 1 >= 0 && j - 2 >= 0){
									((Line) board[i - 1][j - 2]).setUsable(false);
								}
								if(i - 1 >= 0 && j + 2 < board[0].length){
									((Line) board[i - 1][j + 2]).setUsable(false);
								}
							}
						}
						//0 above 3
						if(i - 2 >= 0){
							if(((Square) board[i - 2][j]).getNumber() == 0){
								if(i + 2 < board.length && j - 1 >= 0){
									((Line) board[i + 2][j - 1]).setUsable(false);
								}
								if(i + 2 < board.length && j + 1 < board[0].length){
									((Line) board[i + 2][j + 1]).setUsable(false);
								}
								if(i + 1 < board.length && j - 2 >= 0){
									((Line) board[i + 1][j - 2]).setUsable(false);
								}
								if(i + 1 < board.length && j + 2 < board[0].length){
									((Line) board[i + 1][j + 2]).setUsable(false);
								}
							}
						}
						//0 to right of 3
						if(j + 2 < board[0].length){
							if(((Square) board[i][j + 2]).getNumber() == 0){
								if(i + 1 < board.length && j - 2 >= 0){
									((Line) board[i + 1][j - 2]).setUsable(false);
								}
								if(i - 1 >= 0 && j - 2 >= 0){
									((Line) board[i - 1][j - 2]).setUsable(false);
								}
								if(i + 2 < board.length && j - 1 >= 0){
									((Line) board[i + 2][j - 1]).setUsable(false);
								}
								if(i - 2 >= 0 && j - 1 >= 0){
									((Line) board[i - 2][j - 1]).setUsable(false);
								}
							}
						}
						//0 to left of 3
						if(j - 2 >= 0){
							if(((Square) board[i][j - 2]).getNumber() == 0){
								if(i + 1 < board.length && j + 2 < board[0].length){
									((Line) board[i + 1][j + 2]).setUsable(false);
								}
								if(i - 1 >= 0 &&  j + 2 < board[0].length){
									((Line) board[i - 1][j + 2]).setUsable(false);
								}
								if(i + 2 < board.length &&  j + 1 < board[0].length){
									((Line) board[i + 2][j + 1]).setUsable(false);
								}
								if(i - 2 >= 0 &&  j + 1 < board[0].length){
									((Line) board[i - 2][j + 1]).setUsable(false);
								}
							}
						}
		
					}
					
					//RULE OF 3 ADJACENT TO 3
					if(((Square) board[i][j]).getNumber() == 3){
						//Vertical
						if(i + 2 < board.length){
							if(((Square) board[i + 2][j]).getNumber() == 3){
								if(j - 2 >= 0){
									((Line) board[i + 1][j - 2]).setUsable(false);
								}
								if(j + 2 < board[0].length){
									((Line) board[i + 1][j + 2]).setUsable(false);
								}
							}
						}
						//Horizontal
						if(j + 2 < board[0].length){
							if(((Square) board[i][j + 2]).getNumber() == 3){
								if(i - 2 >= 0){
									((Line) board[i - 2][j + 1]).setUsable(false);
								}
								if(i + 2 < board.length){
									((Line) board[i + 2][j + 1]).setUsable(false);
								}
							}
						}
					}
					
					//RULE 3 DIAGNAL OF 3
					if(((Square) board[i][j]).getNumber() == 3){
						//DIAGNAL THIS WAY "\"
						if(i + 2 < board.length && j + 2 < board[0].length){
							if(((Square) board[i + 2][j + 2]).getNumber() == 3){
								if(i - 2 >= 0 && j - 1 >= 0){
									((Line) board[i - 2][j - 1]).setUsable(false);
								}
								if(i - 1 >= 0 && j - 2 >= 0){
									((Line) board[i - 1][j - 2]).setUsable(false);
								}
								if(i + 4 < board.length && j + 3 < board[0].length){
									((Line) board[i + 4][j + 3]).setUsable(false);
								}
								if(i + 3 < board.length && j + 4 < board[0].length){
									((Line) board[i + 3][j + 4]).setUsable(false);
								}
							}
						}
						//DIAGNAL THIS WAY "/"
						if(i + 2 < board.length && j - 2 >= 0){
							if(((Square) board[i + 2][j - 2]).getNumber() == 3){
								if(i - 2 >= 0 && j + 1 < board[0].length){
									((Line) board[i - 2][j + 1]).setUsable(false);
								}
								if(i - 1 >= 0 && j + 2  < board[0].length){
									((Line) board[i - 1][j + 2]).setUsable(false);
								}
								if(i + 4 < board.length && j - 3 >= 0){
									((Line) board[i + 4][j - 3]).setUsable(false);
								}
								if(i + 3 < board.length && j - 4 >= 0){
									((Line) board[i + 3][j - 4]).setUsable(false);
								}
							}
						}
					}		
				}
			}
		}
	}
	
	//cornerRules Method: Marks line spots as unusable based on number in corners
	public void cornerRules(){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				if((i+1)%2 == 0 && (j+1)%2 == 0){
					//CORNER 1 RULE
					if(((Square) board[i][j]).getNumber() == 1){
						//FLAGS FOR UNUSABLE SPACES AROUND THE 1
						boolean topLeftBlocked = false;
						boolean topRightBlocked = false;
						boolean rightTopBlocked = false;
						boolean rightBottomBlocked = false;
						boolean bottomRightBlocked = false;
						boolean bottomLeftBlocked = false;
						boolean leftBottomBlocked = false;
						boolean leftTopBlocked = false;
						
						//CHECKING OUT OF BOUNDS CORNER BLOCKS
						if(i - 2 < 0){
							topLeftBlocked = true;
							topRightBlocked = true;
						} 
						if(j - 2 < 0){
							leftTopBlocked = true;
							leftBottomBlocked = true;
						}
						if(i + 2 >= board.length){
							bottomLeftBlocked = true;
							bottomRightBlocked = true;
						}
						if(j + 2 >= board[0].length){
							rightTopBlocked = true;
							rightBottomBlocked = true;
						}
						
						
						//CHECKING UNUSABLE LINE BLOCKS
						//Top Left Corner
						if(!topLeftBlocked && i - 2 >= 0 && j - 1 >= 0){
							if(!((Line) board[i - 2][j - 1]).isUsable()){
								topLeftBlocked = true;
							}
						}
						if(!leftTopBlocked && i - 1 >= 0 && j - 2 >= 0){
							if(!((Line) board[i - 1][j - 2]).isUsable()){
								leftTopBlocked = true;
							}
						}
						//Top Right Corner
						if(!topRightBlocked && i - 2 >= 0 && j + 1 < board[0].length){
							if(!((Line) board[i - 2][j + 1]).isUsable()){
								topRightBlocked = true;
							}
						}
						if(!rightTopBlocked && i - 1 >= 0 && j + 2 < board[0].length){
							if(!((Line) board[i - 1][j + 2]).isUsable()){
								rightTopBlocked = true;
							}
						}
						//Bottom Left Corner
						if(!bottomLeftBlocked && i + 2 < board.length && j - 1 >= 0){
							if(!((Line) board[i + 2][j - 1]).isUsable()){
								bottomLeftBlocked = true;
							}
						}
						if(!leftBottomBlocked && i + 1 < board.length && j - 2 >= 0){
							if(!((Line) board[i + 1][j - 2]).isUsable()){
								leftBottomBlocked = true;
							}
						}
						//Bottom Right Corner
						if(!bottomRightBlocked && i + 2 < board.length && j + 1 < board[0].length){
							if(!((Line) board[i + 2][j + 1]).isUsable()){
								bottomRightBlocked = true;
							}
						}
						if(!rightBottomBlocked && i + 1 < board.length && j + 2 < board[0].length){
							if(!((Line) board[i + 1][j + 2]).isUsable()){
								rightBottomBlocked = true;
							}
						}
						

						//MARK NEW UNUSABLE LINES
						if(topLeftBlocked && leftTopBlocked){
							((Line) board[i - 1][j]).setUsable(false);
							((Line) board[i][j - 1]).setUsable(false);
						}else if(topRightBlocked && rightTopBlocked){
							((Line) board[i - 1][j]).setUsable(false);
							((Line) board[i][j + 1]).setUsable(false);
						}else if(rightBottomBlocked && bottomRightBlocked){
							((Line) board[i + 1][j]).setUsable(false);
							((Line) board[i][j + 1]).setUsable(false);
						}else if(bottomLeftBlocked && leftBottomBlocked){
							((Line) board[i + 1][j]).setUsable(false);
							((Line) board[i][j - 1]).setUsable(false);
						}
					}
					
					//0 CORNER RULE
					if(((Square) board[i][j]).getNumber() == 0){
						//top left
						if(j - 2 >= 0 && (i - 2 < 0 || !((Line) board[i - 2][j - 1]).isUsable())){
							((Line) board[i - 1][j - 2]).setUsable(false);
						}
						
						if(i - 2 >= 0 && (j - 2 < 0 || !((Line) board[i - 1][j - 2]).isUsable())){
							((Line) board[i - 2][j - 1]).setUsable(false);
						}

						//top right
						if(j + 2 < board[0].length && (i - 2 < 0 || !((Line) board[i - 2][j + 1]).isUsable())){
							((Line) board[i - 1][j + 2]).setUsable(false);
						}
						
						if(i - 2 >= 0 && (j + 2 >= board[0].length || !((Line) board[i - 1][j + 2]).isUsable())){
							((Line) board[i - 2][j + 1]).setUsable(false);
						}
							
						
						//bottom left
						if(j - 2 >= 0 && (i + 2 >= board.length || !((Line) board[i + 2][j - 1]).isUsable())){
							((Line) board[i + 1][j - 2]).setUsable(false);
						}
						
						if(i + 2 < board.length && (j - 2 < 0 || !((Line) board[i + 1][j - 2]).isUsable())){
							((Line) board[i + 2][j - 1]).setUsable(false);
						}

						//bottom right
						if(j + 2 < board[0].length && (i + 2 >= board.length || !((Line) board[i + 2][j + 1]).isUsable())){
							((Line) board[i + 1][j + 2]).setUsable(false);
						}
						
						if(i + 2 < board.length && (j + 2 >= board[0].length || !((Line) board[i + 1][j + 2]).isUsable())){
							((Line) board[i + 2][j + 1]).setUsable(false);
						}
						
						
					}
					
				}
			}
		}
		
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

	public void solve(){
		int number = 3;
		boolean solution = false;
		
		while(number > 0 && !solution){
			for(int i = 0; i < board.length; i++){
				for(int j = 0; j < board[0].length; j++){
					if((i+1)%2 == 0 && (j+1)%2 == 0 && !solution){
						if(((Square) board[i][j]).getNumber() == number){
							starti = i - 1;
							startj = j - 1;
							solution = solveRecursively(i-1, j-1);
							if(!solution){
								starti = i + 1;
								startj = j + 1;
								solution = solveRecursively(i+1, j+1);
							}
						}
					} 
				}
			}
			number--;
		}
		
		if(solution){
			System.out.println("Solution Moves List (Row, Column, Side):");
			
			Stack<Integer> tempRows = new Stack();
			Stack<Integer> tempCols = new Stack();
			
			while(!solutionRow.isEmpty() && !solutionCol.isEmpty()){
				tempRows.push(solutionRow.pop());
				tempCols.push(solutionCol.pop());
			}
			
			while(!tempRows.isEmpty() && !tempCols.isEmpty()){
				int rowNum = tempRows.pop();
				int colNum = tempCols.pop();
				String dir = "";
				
				if(colNum % 2 == 0 && rowNum % 2 == 1){
					dir = "Right";
					colNum--;
				}
				
				if(colNum % 2 == 1 && rowNum % 2 == 0){
					dir = "Top";
				}
				
				if(colNum < 1){
					dir = "Left";
				}
				
				rowNum = (rowNum / 2) + 1;
				colNum = (colNum / 2) + 1;

				if(rowNum > board.length / 2){
					dir = "Bottom";
					rowNum--;
				}
				
				System.out.println(rowNum + ", " + colNum + ", " + dir);
			}
			System.out.println();
			printBoard();
		}
		
	}
	
	private boolean solveRecursively(int i, int j){

		boolean done = false;
		
		/*
		//SLEEPPYTIME
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//SLEEPYTIME
		*/

		//move right
		if(!done){
			if(j + 1 < board[0].length){
				if(((Line) board[i][j + 1]).isUsable() && !((Line) board[i][j + 1]).isLine() && !checkCollision(i, j + 2) && checkNeighbors(i, j + 1) && checkRound3(i, j, 'R')){
					if(i == starti && j + 2 == startj){
						((Line) board[i][j + 1]).toggle();
						solutionRow.push(i);
						solutionCol.push(j + 1);
						moves++;
						if(checkLoop() && checkNumbers()){
							return true;
						} else{
							((Line) board[i][j + 1]).toggle();
							solutionRow.pop();
							solutionCol.pop();
							return false;
						}
					}
					((Line) board[i][j + 1]).toggle();
					solutionRow.push(i);
					solutionCol.push(j + 1);
					moves++;
					//printBoardAndUnusable();
					done = solveRecursively(i, j + 2);
					if(!done){ 
						((Line) board[i][j + 1]).toggle();
						solutionRow.pop();
						solutionCol.pop();
					}
				}
			}
		}

		//move up
		if(!done){
			if(i - 1 >= 0){
				if(((Line) board[i - 1][j]).isUsable() && !((Line) board[i - 1][j]).isLine() && !checkCollision(i - 2, j) && checkNeighbors(i - 1, j) && checkRound3(i, j, 'U')){
					if(i - 2 == starti && j == startj){
						((Line) board[i - 1][j]).toggle();
						solutionRow.push(i - 1);
						solutionCol.push(j);
						moves++;
						if(checkLoop() && checkNumbers()){
							return true;
						} else{
							((Line) board[i - 1][j]).toggle();
							solutionRow.pop();
							solutionCol.pop();
							return false;
						}
					}
					((Line) board[i - 1][j]).toggle();
					solutionRow.push(i - 1);
					solutionCol.push(j);
					moves++;
					//printBoardAndUnusable();
					done = solveRecursively(i - 2, j);
					if(!done){ 
						((Line) board[i - 1][j]).toggle();
						solutionRow.pop();
						solutionCol.pop();
					}
				}
			}
		}

		//move left
		if(!done){
			if(j - 1 >= 0){
				if(((Line) board[i][j - 1]).isUsable() && !((Line) board[i][j - 1]).isLine() && !checkCollision(i, j - 2) && checkNeighbors(i, j - 1) && checkRound3(i, j, 'L')){
					if(i == starti && j - 2 == startj){
						((Line) board[i][j - 1]).toggle();
						solutionRow.push(i);
						solutionCol.push(j - 1);
						moves++;
						if(checkLoop() && checkNumbers()){
							return true;
						} else{
							((Line) board[i][j - 1]).toggle();
							solutionRow.pop();
							solutionCol.pop();
							return false;
						}
					}
					((Line) board[i][j - 1]).toggle();
					solutionRow.push(i);
					solutionCol.push(j - 1);
					moves++;
					//printBoardAndUnusable();
					done = solveRecursively(i, j - 2);
					if(!done){ 
						((Line) board[i][j - 1]).toggle();
						solutionRow.pop();
						solutionCol.pop();
					}
				}
			}
		}

		//move down
		if(!done){
			if(i + 1 < board.length){
				if(((Line) board[i + 1][j]).isUsable() && !((Line) board[i + 1][j]).isLine() && !checkCollision(i + 2, j) && checkNeighbors(i + 1, j) && checkRound3(i, j, 'D')){
					if(i + 2 == starti && j == startj){
						((Line) board[i + 1][j]).toggle();
						solutionRow.push(i + 1);
						solutionCol.push(j);
						moves++;
						if(checkLoop() && checkNumbers()){
							return true;
						} else{
							((Line) board[i + 1][j]).toggle();
							solutionRow.pop();
							solutionCol.pop();
							return false;
						}
					}
					((Line) board[i + 1][j]).toggle();
					solutionRow.push(i + 1);
					solutionCol.push(j);
					moves++;
					//printBoardAndUnusable();
					done = solveRecursively(i + 2, j);
					if(!done){ 
						((Line) board[i + 1][j]).toggle();
						solutionRow.pop();
						solutionCol.pop();
					}
				}
			}
		}
		return done;
	}

	private boolean checkRound3(int i, int j, char dir){
		
		if(dir == 'R'){
			if(j - 1 >= 0 && i + 1 < board.length && i - 1 >= 0 && ((((Square) board[i + 1][j - 1]).getNumber() == 3 && !check3Num(i + 1, j - 1)) || (((Square) board[i - 1][j - 1]).getNumber() == 3 ) && !check3Num(i - 1, j - 1))){
				return false;
			}
		}
		
		if(dir == 'D'){
			if(i - 1 >= 0 && j + 1 < board[0].length && j - 1 >= 0 && ((((Square) board[i - 1][j + 1]).getNumber() == 3 && !check3Num(i - 1, j + 1)) || (((Square) board[i - 1][j - 1]).getNumber() == 3 && !check3Num(i - 1, j - 1)))){
				return false;
			}
		}
		
		if(dir == 'L'){
			if(j + 1 < board[0].length && i + 1 < board.length && i - 1 >= 0 && ((((Square) board[i + 1][j + 1]).getNumber() == 3 && !check3Num(i + 1, j + 1)) || (((Square) board[i - 1][j + 1]).getNumber() == 3 && !check3Num(i - 1, j + 1)))){
				return false;
			}
		}
		
		if(dir == 'U'){
			if(i + 1 < board.length && j + 1 < board[0].length && j - 1 >= 0 && ((((Square) board[i + 1][j + 1]).getNumber() == 3 && !check3Num(i + 1, j + 1)) || (((Square) board[i + 1][j - 1]).getNumber() == 3 && !check3Num(i + 1, j - 1)))){
				return false;
			}
		}
		
		
		
		return true;
	}
	
	private boolean check3Num(int i, int j){
		
		if(((Square) board[i][j]).getNumber() != 3){
			return true;
		}
		
		int count = 0;
		if(((Line) board[i - 1][j]).isLine()){
			count++;
		}
		if(((Line) board[i + 1][j]).isLine()){
			count++;
		}
		if(((Line) board[i][j + 1]).isLine()){
			count++;
		}
		if(((Line) board[i][j - 1]).isLine()){
			count++;
		}
		
		if(count == 3){
			return true;
		} else{
			return false;
		}
	}
	
	private boolean checkNeighbors(int i, int j){
		
		if(i % 2 == 0){
			//Horizontal
			if(i + 1 < board.length){
				int count = ((Square) board[i + 1][j]).getNumber();
				if(((Line) board[i + 2][j]).isLine()){
					count--;
				}
				if(((Line) board[i + 1][j - 1]).isLine()){
					count--;
				}
				if(((Line) board[i + 1][j + 1]).isLine()){
					count--;
				}
				if(count == 0){
					return false;
				}
			}
			if(i - 1 >= 0){
				int count = ((Square) board[i - 1][j]).getNumber();
				if(((Line) board[i - 2][j]).isLine()){
					count--;
				}
				if(((Line) board[i - 1][j - 1]).isLine()){
					count--;
				}
				if(((Line) board[i - 1][j + 1]).isLine()){
					count--;
				}
				if(count == 0){
					return false;
				}
			}
			
		} else{
			//Vertical
			if(j + 1 < board[0].length){
				int count = ((Square) board[i][j + 1]).getNumber();
				if(((Line) board[i][j + 2]).isLine()){
					count--;
				}
				if(((Line) board[i - 1][j + 1]).isLine()){
					count--;
				}
				if(((Line) board[i + 1][j + 1]).isLine()){
					count--;
				}
				if(count == 0){
					return false;
				}
			}
			
			if(j - 1 >= 0){
				int count = ((Square) board[i][j - 1]).getNumber();
				if(((Line) board[i][j - 2]).isLine()){
					count--;
				}
				if(((Line) board[i - 1][j - 1]).isLine()){
					count--;
				}
				if(((Line) board[i + 1][j - 1]).isLine()){
					count--;
				}
				if(count == 0){
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean checkCollision(int i, int j){
		if(i != starti || j != startj){
			if(i - 1 >= 0){
				if( ((Line) board[i - 1][j]).isLine() ){
					//System.out.println("true collision" + ", i:" + i + ", j:" + j);
					return true;
				}	
			}
			if(j + 1 < board[0].length){
				if( ((Line) board[i][j + 1]).isLine() ){
					//System.out.println("true collision" + ", i:" + i + ", j:" + j);
					return true;
				}	
			}
			if(i + 1 < board.length){
				if( ((Line) board[i + 1][j]).isLine() ){
					//System.out.println("true collision" + ", i:" + i + ", j:" + j);
					return true;
				}	
			}
			if(j - 1 >= 0){
				if( ((Line) board[i][j - 1]).isLine() ){
					//System.out.println("true collision" + ", i:" + i + ", j:" + j);
					return true;
				}	
			} 
		}
		//System.out.println("false collision" + ", i:" + i + ", j:" + j);
		return false;
	}

	public long getMoves(){
		long temp = moves;
		moves = 0;
		return temp;
	}
	
	//printBoard Method: prints to the console a text representation of the game board
	public void printBoard(){
		System.out.println("GAME BOARD:");
		for(int i = 0; i < board.length; i++){
			//PRINT COLUMN NUMBERS
			if(i == 0){
				for(int k = 1; k <= board[0].length / 2; k++){
					if(k == 1){
						System.out.print("  ");
					}
					System.out.print("    " + k + "   ");
				}
				System.out.println("\n\n");
			}
			//END PRINT COLUMN NUMBERS
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
					//PRINT ROW NUMBERS
					if(j == board[0].length - 1){
						System.out.print("        " + ((i / 2) + 1));
					}
					//END PRINT ROW NUMBERS
				}
			}
			System.out.println();
			if(i%2 == 0){
				System.out.print(" ");
			}
		}
		System.out.println("\n\n\n");
	}

	//printUnusable Method: prints to the console a text representation of the game board with only unusable spaces marked
	public void printUnusable(){
		System.out.println("GAME BOARD (Displaying Only Unusable Lines):");
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
						if(((Line) board[i][j]).isUsable() == false){
							System.out.print(" X ");
						} else{
							System.out.print("   ");
						}
					} else{
						if(((Line) board[i][j]).isUsable() == false){
							System.out.print(" X");
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

	//printBoardAndUnusable Method: prints to the console a text representation of the game board with unusable spaces and lines marked
	public void printBoardAndUnusable(){
		System.out.println("GAME BOARD (Displaying Unusable Lines and Lines):");
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
						} else if(!((Line) board[i][j]).isUsable()){
							System.out.print(" X ");
						} else{
							System.out.print("   ");
						}
					} else{
						if(((Line) board[i][j]).isLine()){
							System.out.print(" |");
						} else if(!((Line) board[i][j]).isUsable()){
							System.out.print(" X");
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
