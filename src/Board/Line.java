package Board;

public class Line extends Tile{
	private boolean visited;
	private boolean line;
	
	public Line() {
		super("line");
		visited = false;
		line = false;
	}
	
	public boolean isLine(){
		return line;
	}
	
	public void toggle(){
		if(line == false){
			line = true;
		} else{
			line = false;
		}
	}
}
