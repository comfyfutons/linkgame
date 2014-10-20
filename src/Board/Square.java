package Board;

public class Square extends Tile {
	int number;
	
	public Square() {
		super("square");
		number = -1;
	}
	
	public int getNumber(){
		return number;
	}
	
	public void setNumber(int number){
		if(number >= 0 && number <= 3){
			this.number = number;
		} else{
			System.out.println("number not in range 0 to 3");
		}
	}

}
